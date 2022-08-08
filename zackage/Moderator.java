package zackage;
import java.io.*;


public class Moderator extends User {
    
    public Moderator(String u, String pw) {
        super(u, pw);
    }

    protected String color = "\u001B[42m";

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

    public void printUser(){

        System.out.println(username + " (M)");

    }

}
