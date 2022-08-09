package zackage;
import java.util.*;

// maybe implement this as singleton??

/* ************************************************
                    "UI" CLASS

    * Represents the User Interface
    * Abstract aways the printing logic
        needed to display each page of the application 
    * Each page can be printed in Main.java
        by calling a single method

*************************************************** */

public class UI {

    // currently logged in user
    private User user;

    // width (in characters) of program "screen"
    private static int screenWidth = 50;

    private Scanner kb;

/* *********************************************** 
                    CONSTRUCTORS
************************************************** */

    public UI() {

    };

    public UI(User user, Scanner s) {
        this.user = user;
        this.kb = s;
    }


/* *********************************************** 
                    METHODS
************************************************** */

    public static void printLine() {

        System.out.println("*".repeat(screenWidth));
    }

    /*
    public static void printEmptyLine() {
        System.out.println(" ".repeat(screenWidth));
    }
    */

    public static void printLine(String str) {
    
        String output = String.format("%-" + screenWidth + "s", str);
        System.out.println(output);
    }


    public static void homepage() {

        printLine();
        printLine("\nWelcome! press L to login or R to register.");
    }
    
    public String[] register() {
        printLine();
        System.out.println("Username: ");
        String username = kb.next();
        System.out.println("Password: ");
        String password = kb.next();

        String strLst[]  = { username, password };
        return strLst;
    }

    public String[] login(){

        printLine();

        boolean loggedIn = false;
        String strLst[]  = new String[2];
        while(!loggedIn) {
            System.out.println("Username: ");
            String username = kb.next();
            System.out.println("Password: ");
            String password = kb.next();
            
            strLst[0] = username;
            strLst[1] = password;

            loggedIn = User.checkFor(username, password);
        }

        return strLst;
   
    }


    public void menu() {

        printLine();
        printLine("\n1. Create Post");
        printLine("2. Delete Post");
        printLine( "3. Show Your Posts ");
        printLine("4. Search for User's Posts");
        printLine("5. Show all Posts");
        if(this.user instanceof Admin){
            printLine("6. Remove Post Privileges of User");
            printLine("7. Log Out");
        }
        else{
            printLine("6. Log Out");
        }

    }

    public String createPost() {

        printLine();
        if (user.canPost) {

            printLine("\nPlease enter your post:\n");
            String post_text = kb.nextLine();
            return post_text;

        } else {

            printLine("Your post privilieges have been revoked!");
            return "";
        }
        
         
    }

    public void deletePost(){
        printLine();
        if( this.user instanceof Moderator){
            printLine("1. Delete your Post");
            printLine( "2. Delete any Post");
            printLine("Choose an option: ");
            int opt = kb.nextInt();

            if(opt == 1){
                user_deletePost(this.user);
            }
            else{
                mod_deletePost();
            }
        }
        else{
            user_deletePost(this.user);
        }
    }

    public int user_deletePost(User user){
        printLine();
        for(int i = 0; i < user.posts.size(); i++){
            printLine();
            System.out.println(user.posts.get(i).getID() + " | " + user.posts.get(i));
            printLine();
        }

        printLine("Type the ID of the post you want to delete: ");
        int delete_id = kb.nextInt();

        return delete_id;
    }

    public int mod_deletePost(){
        
        printLine();
        for(int i = 0; i < Post.allPosts.size(); i++){
            printLine();
            System.out.println(Post.allPosts.get(i).getID() + " | " + Post.allPosts.get(i));
            printLine();

        }

        printLine("Type the ID of the post you want to delete: ");
        int delete_id = kb.nextInt();
        printLine();
        return delete_id;

        
    }

    public static void main(String[] args) {
        
    }

    
    
}
