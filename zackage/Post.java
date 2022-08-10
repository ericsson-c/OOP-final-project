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
    private static final long serialVersionUID = 4L;
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
    public static ArrayList<Post> allPosts = new ArrayList<Post>();

    /*
        static int currentID:
            - To make sure no two Posts have
                the same id, keep track of the highest id.
            - When a new Post is constructed, assign it's id such
                that newPost.id = Post.currentID + 1
    */
    public static int currentID = 1;


//  --------- NON-STATIC ATTRIBUTES ------------ \\

    /* unique id to identify the post
        - when written to file, filaname is "{Post.id}.bin */
    private int id;

    // user who posted this Post
    protected User user;

    // text content of the Post
    private String text;

    // timestamp of when the user posted this Post
    private Date postedAt;
    

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
    public Post(String text, User user) {

        this.postedAt = new Date(System.currentTimeMillis());
        this.text = text;
        this.user = user;
        this.id = Post.currentID;
        Post.currentID ++;
        allPosts.add(this);

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

    public User getUser() {
        return user;
    }


/* ***********************************************
        METHODS FOR READING AND WRITING POSTS
************************************************** */

/*
    static Post.readPost()
    * @params: 
        - String filename: name of the file to read.

    * @return Post:
        - if successful, return the post that was read
        - if unsuccessful, return an empty post
*/
    public static Post readPost(String filename) {

        try {

            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/zackage/posts/" + filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object readObject = ois.readObject();
            Post readPost = (Post) readObject;

            ois.close();

            return readPost;

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            return null;

        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Could not create ObjectInputStream for " + filename);
            return null;

        } catch (ClassNotFoundException e) {
            System.err.println("Could not cast read object as Post");
            return null;
        }
    }

    
/*
    save()
    - Write the Post instance the method was called on to a .bin file
    * @params: None
    * @return boolean:
        - true if file write was successful
        - false if an error occured
*/

    public boolean save() {

        try {
            
            File postFile = new File("zackage/posts/" + id + ".bin");
            FileOutputStream fos = new FileOutputStream(postFile);
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            ois.writeObject(this);

            ois.flush();
            ois.close();

            // delete reference to the file
            // postFile.delete();

            return true;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            System.err.println("FileNotFound Exception.");
            return false;

        } catch (IOException e) {

            e.printStackTrace();
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
        
        return "@" + user + "\n\n" + this.text;
    }



/* *********************************************
            MAIN() METHOD (testing only)
************************************************ */

    public static void main(String[] args) throws FileNotFoundException {

        Admin ericsson = new Admin("ericsson", "password");
        Moderator zack = new Moderator("zack", "password");

        Post p1 = new Post("hi zack", ericsson);
        Post p2 = new Post("hi ericsson", zack);

        System.out.println(p1.save());
        System.out.println(p2.save());

        String path = System.getProperty("user.dir") + "/zackage/posts/";
        // System.out.println(path);
        //p1 = Post.readPost("1.bin");
        //p2 = Post.readPost("2.bin");


        // System.out.println(p1 + "\n\n" + p2);

        
    }
}
