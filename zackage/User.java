package zackage;

import java.util.ArrayList;
import java.util.*;

public class User {  

    private String username;
    private String password;

    public User() {
        
    }

    public User(String u, String pw) {

        this.username = u;
        this.password = pw;
    }

    public String getUsername() {
        return username;
    }

    public boolean createPost(String text) {

        Post newPost = new Post(text, username);
        return true;
    }

    public boolean deletePost(int postID, User user) {

        for (int i = 0; i < Post.allPosts.size(); i++) {

            Post curr = Post.allPosts.get(i);
            if (curr.getID() == postID && curr.getUser() == user.username) {

                // TODO: remove file from folder...
                Post.allPosts.remove(curr);
            } 
        }

        return true;
    }



}