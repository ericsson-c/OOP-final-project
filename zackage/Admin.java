package zackage;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Attribute.*;

import java.io.*;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Admin extends User {

/* ************************************************
                    "Admin" CLASS

    * Represents an Admin
    * Has all the same functionality as a User.
    * Can additionally revoke posting privileges of a User, Admin, or Moderator

*************************************************** */

    private static final long serialVersionUID = 5L;

    public Admin(String u, String pw) {
        super(u, pw);
    }

    // Admin username are printed in Red
    protected Attribute color = RED_TEXT();

/* *********************************************** 
                    METHODS
************************************************** */

    public static Admin readAdmin(String filename) {

        try {

            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/zackage/users/" + filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object readObject = ois.readObject();

            Admin readUser = (Admin) readObject;

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
        
        // if the read object is not an admin
        } catch (ClassCastException e) {
            return null;
        }
    }


    //Parameters: User: user, boolean: set
    //admin has the ability to stop Users and Moderators from posting.
    public void setPrivileges(User user, boolean set){
        
        user.canPost = set;

        if(set == user.canPost){
            System.out.println(user.username + " already has these permissions!");
        }
        else if(set){
            System.out.println("Granted " + user.username + " permission to post.");
        }
        else{
            System.out.println("Revoked " + user.username + " permission to post.");
        }

    }
    
    //Parameters: takes no parameters
    //prints Username in red to signify that the user is an Admin
    /*
    @Override
    public void printUsername(){

        System.out.println(colorize(username + " (A)", color));
    }
    */

    @Override
    public String toString() {
        
        return colorize(username + " (A)", color);
    }
}
