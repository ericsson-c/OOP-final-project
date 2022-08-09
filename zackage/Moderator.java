package zackage;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;


public class Moderator extends User {
/* ************************************************
                    "Moderator" CLASS

    * Represents a moderator
    * Has all the same functionality as a User.
    * Can additionally delete the post of any User, Admin, or Moderator

*************************************************** */
    
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
    public boolean deletePost(int postID, User user) {

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
    @Override
    public void printUsername(){

        System.out.println(colorize(username + " (M)", color));
    }

}
