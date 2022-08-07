package zackage;

public class Admin extends User {
    
    public Admin(String u, String pw) {
        super(u, pw);
    }

    protected String color = "\u001B[41m";

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

}
