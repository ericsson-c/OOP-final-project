package zackage;

public class Moderator extends User {
    
    public Moderator(String u, String pw) {
        super(u, pw);
    }

    private String username;
    private String password;

    public boolean createPost(String text) {

        Post newPost = new Post(text, username);
        return true;
    }
    
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
}
