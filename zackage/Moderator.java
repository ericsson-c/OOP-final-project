package zackage;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Attribute.*;

import java.io.*;

import static com.diogonunes.jcolor.Ansi.colorize;


public class Moderator extends User {
/* ************************************************
                    "Moderator" CLASS

    * Represents a moderator
    * Has all the same functionality as a User.
    * Can additionally delete the post of any User, Admin, or Moderator

*************************************************** */

    private static final long serialVersionUID = 5L;

    public static Moderator readModerator(String filename) {

        try {

            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/zackage/users/" + filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object readObject = ois.readObject();

            Moderator readUser = (Moderator) readObject;

            ois.close();
            return readUser;

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            return null;

        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Could not create ObjectInputStream for " + filename);
            return null;

        } catch (ClassNotFoundException e) {
            System.err.println("Could not cast read object as User");
            return null;

        // if the read user is not a moderator
        } catch (ClassCastException e) {
            return null;
        }
    }

    
    public Moderator(String u, String pw) {
        super(u, pw);
    }

    // Moderator usernames are printed in Blue
    protected Attribute color = BLUE_TEXT();

/* *********************************************** 
                    METHODS
************************************************** */

    //delete's a user's post
    //Parameters: int: postID, User: user
    @Override
    public boolean deletePost(int postID) {

        for (int i = 0; i < Post.allPosts.size(); i++) {

            Post curr = Post.allPosts.get(i);
            if (curr.getID() == postID) { //moderators can skip the user check

                Post.allPosts.remove(curr);
            } 
        }

        return true;
    }

    //Parameters: takes no parameters
    //Prints moderator's username is a blue to signifiy it is a moderator

    /*
    @Override
    public void printUsername(){

        System.out.println(colorize(username + " (M)", color));
    }
    */

    @Override
    public String toString() {
        
        return colorize(username + " (M)", color);
    }

}
