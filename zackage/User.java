package zackage;

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


public class User {  

    //Static Attributes
    static private ArrayList<String[]> allUsers = new ArrayList();

    //Non-Static Attributes
    protected String username;
    private String password;
    protected boolean canPost = true;
    protected Attribute color = NONE();



//Static Methods
    //Open all users method
    
    //check username and password validity
    protected static boolean checkFor(String u, String pw){
        for (int i=0; i<allUsers.size(); i++){
            if (allUsers.get(i)[0].equals(u) && allUsers.get(i)[1].equals(pw)){
                return true;
            }
        }
        return false;
    }





//Non-Static Methods


    public User() {
        
    }

    public User(String u, String pw) {
        this.username = u;
        this.password = pw;
        
        //Adding username and password to the all users array 
        String[] toAdd = {u, pw};
        allUsers.add(toAdd);
    }

    public String getUsername() {
        return username;
    }

    public boolean createPost(String text) {
        Post newPost = new Post(text, this);
        return true;
    }

    public boolean deletePost(int postID, User user) {

        for (int i = 0; i < Post.allPosts.size(); i++) {

            Post curr = Post.allPosts.get(i);
            if (curr.getID() == postID && curr.getUser().username == user.username) {

                // TODO: remove file from folder...
                Post.allPosts.remove(curr);
            } 
        }

        return true;
    }

    public void deleteUser(){
        username = null;
        password = null;
    }

    public void printUsername(){

        System.out.println(colorize(username, color));
    }


}