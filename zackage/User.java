package zackage;

import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class User {  

    protected String username;
    private String password;
    protected boolean canPost = true;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[41m";

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

    public void deleteUser(){
        username = null;
        password = null;
    }

    public void printUser(){

        System.out.println(ANSI_CYAN + this.username + ANSI_RESET);

    }


}