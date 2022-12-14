package zackage;
import java.io.*;

/* 
JColor: package for printing text in different colors in the Terminal.
Compatible with Windows, MacOS, and linux.

@source: https://github.com/dialex/JColor 
@author: Diogo Nunes
*/
import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Attribute.*;
// we use it to print each user's username as a different color
// depending on whether they are an admin, moderator, or basic user

import java.util.ArrayList;


/* ************************************************
                    "User" CLASS

    * Represents an individual User
    * Has methods for saving itself to a file,
        printing itself (in color), and creating/deleting posts
    * Static method for reading any User 
        from a file into memory

*************************************************** */

public class User implements Serializable {  

    //Static Attributes
    public static ArrayList<User> allUsers = new ArrayList<User>();

    //Non-Static Attributes
    protected String username;
    protected String password;
    protected boolean canPost = true;
    protected Attribute color = NONE();

    // ArrayList of user's posts
    protected ArrayList<Post> posts = new ArrayList<Post>();

    private static final long serialVersionUID = 5L;


/* ******************************************************************************
                            STATIC METHODS
******************************************************************************** */


    protected static boolean checkFor(String u, String pw) {

        for (int i=0; i<allUsers.size(); i++){
            if (allUsers.get(i).username.equals(u) && allUsers.get(i).password.equals(pw)){
                return true;
            }
        }
        return false;
    }

    protected static User login(String u, String pw) {

        for (int i=0; i<allUsers.size(); i++) {

            if (allUsers.get(i).username.equals(u) && allUsers.get(i).password.equals(pw)){
                return allUsers.get(i);
            }
        }
        return null;
    }

    protected static User register(String u, String pw) {

        try {
            User user = new User(u, pw);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    protected static User findUser(String username) {

        for (int i = 0; i < allUsers.size(); i++) {

            if (allUsers.get(i).username == username) {
                return allUsers.get(i);
            }
            
        }

        return null;
    }


/* ***********************************************
        METHODS FOR READING AND WRITING USERS
************************************************** */

/*
    static Post.readUser()
    * @params: 
        - String filename: name of the file to read.

    * @return User:
        - if successful, return the post that was read
        - if unsuccessful, return an empty User
*/

    public static User readUser(String filename) {

        try {

            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/zackage/users/" + filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object readObject = ois.readObject();

            User readUser = (User) readObject;

            ois.close();
            return readUser;

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            return null;

        } catch(IOException e) {
            System.err.println("Could not create ObjectInputStream for " + filename);
            return null;

        } catch (ClassNotFoundException e) {
            System.err.println("Could not cast read object as User");
            return null;
        }
    }


/* ******************************************************************************
                            NON-STATIC METHODS
******************************************************************************** */

/*
    save()
    - Write the User instance the method was called on to a .bin file
    * @params: None
    * @return boolean:
        - true if file write was successful
        - false if an error occured
*/

    public boolean save() {

        try {
            
            FileOutputStream fos = new FileOutputStream(new File("zackage/users/" + username + ".bin"));
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            ois.writeObject(this);

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



/* *********************************************** 
                    CONSTRUCTORS
************************************************** */

    public User() {
        
    }

    public User(String u, String pw) {
        this.username = u;
        this.password = pw;
        
        //Adding new user to the all users array 
        User.allUsers.add(this);
    }


/* ******************************************** 
                GETTERS / SETTERS
*********************************************** */


    public String getUsername() {
        return username;
    }


/* *********************************************** 
                OTHER METHODS
************************************************** */


    public boolean createPost(String text) {

        if (canPost) {
            Post newPost = new Post(text, this);
            posts.add(newPost);
            return true;
        } else {
            return false;
        }
    }


    public boolean deletePost(int postID) {

        // remove from allPosts array
        for (int i = 0; i < Post.allPosts.size(); i++) {

            Post curr = Post.allPosts.get(i);
            if (curr.getID() == postID && curr.getUser().username.equals(username)) {

                Post.allPosts.remove(curr);
            }
        }

        // remove from user's posts attribute
        for (int i = 0; i < posts.size(); i++) {

            Post curr = posts.get(i);
            if (curr.getID() == postID) {

                posts.remove(curr);
            } 
        }

        return true;
    }


    public void deleteUser() {
        username = null;
        password = null;
    }

  
    @Override
    public String toString() {
        return colorize(username, color);
    }

    public static void main(String[] args) {
       
    }
}
