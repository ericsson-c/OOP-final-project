package zackage;
import java.util.*;
import java.io.*;

/* ************************************************
                    "POST" CLASS

    * Represents an individual Post.
    * Keeps a static array of all Posts in memory.
    * Static methods for reading and writing posts
        from /posts folder.

*************************************************** */

public class Post implements Serializable {

// ------------------- IGNORE ----------------------------- \\
    /* serialVersionUID: needed for Serializable Interface
    --> implementing Serializable allows us to read/write Posts to .bin files */
    // private static final long serialVersionUID = 4L;
// --------------------------------------------------------- \\


/* *********************************************** 
                    ATTRIBUTES
************************************************** */

//  --------- STATIC ATTRIBUTES ------------ \\

    /* 
        static ArrayList<Post> allPosts: all posts currently in memory.
        -> at runtime, all stored posts (located in /posts)
            are read into memory and into this array
    */
    static ArrayList<Post> allPosts = new ArrayList<Post>();

    /*
        static int currentID:
            - To make sure no two Posts have
                the same id, keep track of the highest id.
            - When a new Post is constructed, assign it's id such
                that newPost.id = Post.currentID + 1
    */
    static int currentID = 0;


//  --------- NON-STATIC ATTRIBUTES ------------ \\

    /* unique id to identify the post
        - when written to file, filaname is "{Post.id}.bin */
    private int id;

    // username of the user who posted this Post
    private String user;

    // timestamp of when the user posted this Post
    private Date postedAt;

    // text content of the Post
    private String text;
    
    // # of likes this Post has
    private int likes;

    // an ArrayList of users who've liked this Post
    private ArrayList<String> likedBy;


/* *********************************************** 
                    CONSTRUCTORS
************************************************** */

    // default constructor
    public Post() {

    }


/*
    Main constructor
    * @params: text content of the post, user who posted it
    * @return: void
*/
    public Post(String text, String user) {

        this.postedAt = new Date(System.currentTimeMillis());
        this.likes = 0;
        this.text = text;
        this.user = user;
        this.id = Post.currentID + 1;
        this.likedBy = new ArrayList<String>();
        Post.allPosts.add(this);

        // save to a .bin file...
        
    }


/* ******************************************** 
                GETTERS / SETTERS
*********************************************** */

    public int getID() {
        return this.id;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }


/* *********************************************
        METHODS FOR READING AND WRITING POSTS
************************************************ */

/*
    static Post.readPost()
    * @params: 
        - String filename: name of the file to read.

    * @return Post:
        - if successful, return the post that was read
        - if unsuccessful, return an empty post
*/
    public static Post readPost(String filename) throws FileNotFoundException {

        try {

            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object readObject = ois.readObject();
            Post readPost = (Post) readObject;

            ois.close();

            return readPost;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new Post();
    }

    
/*
    writePost()
    * @params: 
        - Post post: Post to be written. 
        - String filename: username of the user who posted it
    * @return boolean:
        - true if file write was successful
        - false if an error occured
*/
    public static boolean writePost(Post post, String filename) throws FileNotFoundException {

        try {
            
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            ois.writeObject(post);

            ois.flush();
            ois.close();

            return true;

        } catch (FileNotFoundException e) {

            // e.printStackTrace();
            System.err.println("FileNotFound Exception.");
            return false;

        } catch (IOException e) {

            // e.printStackTrace();
            System.err.println("IO Exception.");
            return false;
        }
    }


/* *********************************************
                OTHER METHODS
************************************************ */

/*
    string toString():
        - overrides the implementation of toString()
            provided by the object class
        - returns "{Post.text} by {Post.user}"
*/

    @Override
    public String toString() {
        return this.text + " by " + this.user;
    }


/* *********************************************
            MAIN() METHOD (testing only)
************************************************ */

    public static void main(String[] args) throws FileNotFoundException {

        
    }
}
