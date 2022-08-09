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
        // String pathToPostsFolder = System.getProperty("user.dir") + "/posts";
        String pathToPostsFolder = System.getProperty("user.dir") + "/zackage/posts";

        try {

            File postsFolder = new File(pathToPostsFolder);
            File[] listOfFiles = postsFolder.listFiles();
            // System.out.println(listOfFiles);

            for (int i = 0; i < listOfFiles.length; i++) {

                // get name of file ( should be {PostID}.bin )
                String filename = listOfFiles[i].getName();
                System.out.println(filename);

                // read that Post into an object using Post.readPost()
                Post p = Post.readPost(filename);

                // if Post was read without any errors, add it to the ArrayList
                if (p != null) {
                    Post.allPosts.add(p);
                }
            }

            return true;

        } catch (Exception e) {

            return false;
        }
    }

/*
    loadUsers()
    - Loads users from /users folder (stored in .bin files) into memory
    * @params: none
    * w@return: boolean
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

                // get name of file ( should be {PostID}.bin )
                String filename = listOfFiles[i].getName();
                System.out.println(filename);

                // read that Post into an object using Post.readPost()
                User u = User.readUser(filename);

                // if Post was read without any errors, add it to the ArrayList
                if (u != null) {
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



/* *********************************************** 
                MAIN() FUNCTION
************************************************** */
    public static void main(String[] args) throws InterruptedException{
    

        // start by loading in Users and Posts from /users and /posts folders

        boolean postsLoaded = loadPosts();
        // boolean usersLoaded = loadUsers();

        System.out.println(postsLoaded);


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

            else if(userChoice.equals("6")) {


            }


        }

        // once user quits, print a message...
        System.out.println("Thanks for using <insert name>!");

        // close the scanner...
        input.close();
        
        // and save Posts and Users in memory to their respective folders
        // if both were successfull, exit with status code 0 (normal)
        if (savePosts() && saveUsers()) {
            System.exit(0);
        }

        // if one or both were unsuccessfull, exit with status code 1 (abnormal)
        // after printing error messages

        else {
            Thread.sleep(3000);
            System.exit(1);
        }
        

        
    }
}
