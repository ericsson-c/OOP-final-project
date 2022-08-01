package zackage;

import java.util.*;

public class Post {
    
    static ArrayList<Post> allPosts = new ArrayList<Post>();
    static int currentID = 0;

    private int id;
    private String user;
    private Date postedAt;
    private String text;
    private int likes;
    private ArrayList<String> likedBy;

    public Post(String text, String user) {

        this.postedAt = new Date(System.currentTimeMillis());
        this.likes = 0;
        this.text = text;
        this.user = user;
        this.id = Post.currentID + 1;
        this.likedBy = new ArrayList<String>();
        Post.allPosts.add(this);

        // save to a .bin file...
    }


    public int getID() {
        return this.id;
    }
}
