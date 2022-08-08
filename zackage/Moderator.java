package zackage;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;


public class Moderator extends User {
    
    public Moderator(String u, String pw) {
        super(u, pw);
    }

    protected Attribute color = BLUE_TEXT();


    @Override
    public boolean deletePost(int postID, User user) {

        for (int i = 0; i < Post.allPosts.size(); i++) {

            Post curr = Post.allPosts.get(i);
            if (curr.getID() == postID) { //moderators can skip the user check

                // TODO: remove file from folder...
                Post.allPosts.remove(curr);
            } 
        }

        return true;
    }

    @Override
    public void printUsername(){

        System.out.println(colorize(username + " (M)", color));
    }

}
