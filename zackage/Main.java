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
    * @return: an ArrayList<Post> of all Posts that were read into memory
*/

    public static ArrayList<Post> loadPosts() {

        // initialize an ArrayList to store all Posts
        ArrayList<Post> readPosts = new ArrayList<Post>();
        // path to the folder containing .bin files for all Posts
        String pathToPostsFolder = "/Users/ericssoncolborn/Documents/NYU/Summer_22/OOP-final-project/zackage/posts";

        File postsFolder = new File(pathToPostsFolder);
        File[] listOfFiles = postsFolder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            // get name of file ( should be {PostID}.bin )
            String filename = listOfFiles[i].getName();

            // read that Post into an object using Post.readPost()
            Post p = Post.readPost(pathToPostsFolder + "/" + filename);

            // if Post was read without any errors, add it to the ArrayList
            if (p != null) {
                readPosts.add(p);
            }
        }

        // return ArrayList of Posts
        return readPosts;
    }


// ******* OPTIONAL WAY TO IMPLEMENT POST SAVE - alternative is to save on Post creation in Post constructor ********
/*
    savePosts()
    - Saves posts in memory to the /posts folder as.bin files
    - Run on (intentional) program exit
    * @params: none
    * @return: an ArrayList<Post> of all Posts that were read into memory
*/
    public static boolean savePosts() throws FileNotFoundException, IOException {

        return true;
    }





/* *********************************************** 
                MAIN() FUNCTION
************************************************** */
    public static void main(String[] args) {
        
        // Post p = new Post();

        /*
        try {
            Main.loadPosts();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        */

        User zack = new Admin("zack", "zack");
        User ericsson = new Moderator("ericsson", "password");
        User user = new User("username", "password");

        zack.printUsername();
        ericsson.printUsername();
        user.printUsername();
    }
}
