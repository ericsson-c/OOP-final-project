package zackage;
import java.util.*;
import java.io.*;


/* ***************************************************************************************
                    MAIN.JS - DRIVER PROGRAM FOR (INSERT PROJECT NAME)
****************************************************************************************** */
public class Main {


/*
    loadPosts()
    - Loads posts from /post folder (stored in .bin files) into memory
    * @params: none
    * @return: boolean
        - true if successful
        - false if an error occured
*/

    // ON PROGRAM START

    public static boolean loadPosts() {

        // path to the folder containing .bin files for all Posts
        String pathToPostsFolder = System.getProperty("user.dir") + "/zackage/posts";

        try {

            File postsFolder = new File(pathToPostsFolder);
            File[] listOfFiles = postsFolder.listFiles();

            int highestPostID = 0;
            for (int i = 0; i < listOfFiles.length; i++) {

                // get name of file ( should be {PostID}.bin )
                String filename = listOfFiles[i].getName();

                // read that Post into an object using Post.readPost()
                Post p = Post.readPost(filename);

                // if Post was read without any errors, add it to the ArrayList
                if (p != null) {
                    if (highestPostID < p.getID()) {
                        highestPostID = p.getID();
                    }
                    Post.allPosts.add(p);
                }
            }

            Post.currentID = highestPostID + 1;
            return true;

        } catch (Exception e) {

            return false;
        }
    }

/*
    loadUsers()
    - Loads users from /users folder (stored in .bin files) into memory
    * @params: none
    * @return: boolean
        - true if successful
        - false if an error occured
*/
    public static boolean loadUsers() {


        // path to the folder containing .bin files for all Users
        String pathToUsersFolder = System.getProperty("user.dir") + "/zackage/users";

        try {

            File usersFolder = new File(pathToUsersFolder);
            File[] listOfFiles = usersFolder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                // get name of file ( should be {username}.bin )
                String filename = listOfFiles[i].getName();

                // first try reading as an Admin, then Moderator, then User
                Admin a = Admin.readAdmin(filename);
                Moderator m = Moderator.readModerator(filename);
                User u = User.readUser(filename);

                // if User t was read without any errors, add it to the ArrayList
                if (a != null) {
                    User.allUsers.add(a);

                } else if (m != null) {
                    User.allUsers.add(m);

                } else if (u != null) {
                    User.allUsers.add(u);
                }
            }

            return true;


        } catch (Exception e) {

            return false;
        }
    }


/*
    savePosts()
    - Saves posts in memory to the /posts folder as.bin files
    - Run on (intentional) program exit
    * @params: none
    * @return: boolean
        - true if successfull
        - false if an error occured
*/

    // ON PROGRAM EXIT

    public static boolean savePosts() {

        // first, clean out the Posts directory
        try {

            String pathToPostsFolder = System.getProperty("user.dir") + "/zackage/posts";
            File postsFolder = new File(pathToPostsFolder);

            for(File file: postsFolder.listFiles()) {
                file.delete();
            }

        } catch (Exception e) {
            System.err.println("Error clearing posts directory before saving posts.");
            return false;
        }

        boolean allSuccessful = true;
        for (int i = 0; i < Post.allPosts.size(); i++) {

            boolean savedSuccessfully = Post.allPosts.get(i).save();

            if (!savedSuccessfully) {
                System.err.println("Error saving Post " + Post.allPosts.get(i).getID());
                allSuccessful = false;
            }
        }

        return allSuccessful;
    }

/*
    saveUsers()
    - Saves users in memory to the /users folder as.bin files
    - Run on (intentional) program exit
    * @params: none
    * @return: boolean
        - true if successfull
        - false if an error occured
*/

    // ON PROGRAM EXIT

    public static boolean saveUsers() {

        boolean allSuccessful = true;
        for (int i = 0; i < User.allUsers.size(); i++) {

            boolean savedSuccessfully  = User.allUsers.get(i).save();

            if (!savedSuccessfully) {
                System.err.println("Error saving User " + User.allUsers.get(i).username);
                allSuccessful = false;
            }
        }
        
        return allSuccessful;
    }



/* ************************************************************************************** 
                                    MAIN() FUNCTION
***************************************************************************************** */

    public static void main(String[] args) throws InterruptedException{

    //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // EVERYONE - CREATE YOU ACCOUNTS HERE
        
        // Admin zach = new Admin("zach", "crunched");
        // zach.save();

        // Admin joey = new Admin("joey", "<your password>");
        // joey.save();

        // Moderator ericsson = new Moderator("ericsson", "colborn");
        // ericsson.save();

        // System.exit(0);
    //  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!   



        // start by loading in Users and Posts from /users and /posts folders

        boolean postsLoaded = loadPosts();
        boolean usersLoaded = loadUsers();

        if (!postsLoaded || !usersLoaded) {
            System.out.println("There was an error starting the program. Please restart and try again.");
            Thread.sleep(2000);
            System.exit(1);
        }

        // System.out.println(postsLoaded);
        // System.out.println(usersLoaded);


        Scanner input = new Scanner(System.in);
        UI.homepage();

        String userChoice = input.next();

        User user = new User();

        if(userChoice.equalsIgnoreCase("R")){

        // return null if error
           user = UI.register(input);
        }
        else if(userChoice.equalsIgnoreCase( "L")){

        // return null if error
            user = UI.login(input);
        }


        if (user == null) {
            System.out.println("Error logging in or registering. Exiting program...");
            System.exit(1);
        }

        // set static user property to the logged in / registered user
        UI.user = user;
        // set static kb property to the created Scanner
        UI.kb = input;

        String eatNewLine = "";


        while (!userChoice.equalsIgnoreCase("Q")) {

            UI.menu();
            userChoice = input.next();
            eatNewLine = input.nextLine();

            if(userChoice.equals("1")){

                UI.createPost();
            }

            else if(userChoice.equals("2")){

                UI.deletePost();
            }
            
            else if(userChoice.equals("3")){
                
                UI.showUserPosts();
            }

            else if(userChoice.equals("4")) {

                UI.showAllPosts();
            }

            else if(userChoice.equals("5") && user instanceof Admin) {

                UI.setPrivileges();
            }

            else if (!userChoice.equalsIgnoreCase("Q")) {

                UI.badChoice();
            }


        }

        
        // once user quits, close the scanner...
        input.close();
        
        // and save Posts and Users in memory to their respective folders
        // if both were successfull, exit with status code 0 (normal)
        if (savePosts() && saveUsers()) {
            System.out.println("Thanks for using CrunchIt!");
            System.exit(0);
        }

        // if one or both were unsuccessfull, exit with status code 1 (abnormal)
        // after printing error messages

        else {
            System.out.println("An error occured while trying to exit the program.");
            Thread.sleep(3000);
            System.exit(1);
        }
        

        
    }
}
