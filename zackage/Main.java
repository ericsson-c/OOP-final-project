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


// ******* OPTIONAL WAY TO IMPLEMENT POST SAVE - alternative is to save on Post creation in Post constructor ********
/*
    savePosts()
    - Saves posts in memory to the /posts folder as.bin files
    - Run on (intentional) program exit
    * @params: none
    * @return: an ArrayList<Post> of all Posts that were read into memory
*/

    // ON PROGRAM EXIT

    public static boolean savePosts() throws FileNotFoundException, IOException {

        return true;
    }


    public static boolean saveUsers() throws FileNotFoundException, IOException {

        return true;
    }



/* *********************************************** 
                MAIN() FUNCTION
************************************************** */
    public static void main(String[] args) {
    
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
        
        while (!userChoice.equalsIgnoreCase("Q")) {
            UI.menu(user);
            userChoice = input.next();
            if(userChoice.equals("1")){
                String text = UI.createPost(user);
                user.createPost(text);

            }
            else if(userChoice.equals("2")){
                int id = UI.deletePost(user);
                user.deletePost(id, user);
            }
            else if(userChoice.equals("3")){
                
            }


        }

        



    }
}
