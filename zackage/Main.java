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
        String pathToPostsFolder = System.getProperty("user.dir") + "/posts";

        try {

            File postsFolder = new File(pathToPostsFolder);
            File[] listOfFiles = postsFolder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                // get name of file ( should be {PostID}.bin )
                String filename = listOfFiles[i].getName();

                // read that Post into an object using Post.readPost()
                Post p = Post.readPost(pathToPostsFolder + "/" + filename);

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
        String pathToUsersFolder = System.getProperty("user.dir") + "/users";

        try {

            File usersFolder = new File(pathToUsersFolder);
            File[] listOfFiles = usersFolder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                // get name of file ( should be {PostID}.bin )
                String filename = listOfFiles[i].getName();

                // read that Post into an object using Post.readPost()
                User u = User.readUser(pathToUsersFolder + "/" + filename);

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
    
        /*
        TESTING
        User zack = new Admin("zack", "zack");
        User ericsson = new Moderator("ericsson", "password");
        User user = new User("username", "password");

        zack.printUsername();
        ericsson.printUsername();
        user.printUsername();
        */

        // start by loading in Users and Posts from /users and /posts folders

        Scanner input = new Scanner(System.in);
        UI.homepage();

        String userChoice = input.next();

        User user = new User();

        if(userChoice.equalsIgnoreCase("R")){

           String strLst[] = UI.register();
           user = new User(strLst[0],strLst[1]);
        }
        else if(userChoice.equalsIgnoreCase( "L")){

            String strLst[] = UI.register();
            user = new User(strLst[0],strLst[1]);
        }

        // set static user property to the logged in / registered user
        UI.user = user;
        // set static kb property to the created Scanner
        UI.kb = input;


        while (!userChoice.equalsIgnoreCase("Q")) {

            UI.menu();
            userChoice = input.next();

            if(userChoice.equals("1")){

                String text = UI.createPost();
                user.createPost(text);

            }
            else if(userChoice.equals("2")){
                int id = UI.deletePost();
                user.deletePost(id);
            }
            else if(userChoice.equals("3")){
                
            }
        }

        // once user quits, save Posts and Users in memory to their respective folders

        // if both were successfull, exit with status code 0 (normal)
        if (savePosts() && saveUsers()) {
            System.exit(0);
        }

        // if one or both were unsuccessfull, exit with status code 1 (abnormal)
        // after printing error messages

        else {
            Thread.sleep(5000);
            System.exit(1);
        }
        

        
    }
}
