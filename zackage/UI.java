package zackage;
import java.util.*;

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
    protected static User user;

    // width (in characters) of program "screen"
    private static int screenWidth = 50;

    // single instance of Scanner created in Main
    protected static Scanner kb;

/* *********************************************** 
                    CONSTRUCTORS
************************************************** */

    /*
    public UI() {

    };

    public UI(User user, Scanner s) {
        this.user = user;
        this.kb = s;
    }
    */


/* *********************************************** 
                GETTERS / SETTERS
************************************************** */


/* *********************************************** 
                OTHER METHODS
************************************************** */

    public static void printLine() {

        System.out.println("*".repeat(screenWidth) + "\n");
    }

    
    public static void printLine(String str) {
    
        String output = String.format("%-" + screenWidth + "s", str);
        System.out.println(output);
    }


    public static void homepage() {

        printLine();
        printLine("Welcome! press L to login or R to register.");
        askForInput();
    }


    public static void askForInput() {
        printLine("\n> ");
    }
    

    public static User register() {

        printLine();
        System.out.println("Username: ");
        String username = kb.next();
        System.out.println("Password: ");
        String password = kb.next();

        return User.register(username, password);
        
    }


    public static User login(){

        printLine();

        User loggedInUser = null;
        String strLst[]  = new String[2];
        while(loggedInUser == null) {
            System.out.println("Username: ");
            String username = kb.next();
            System.out.println("Password: ");
            String password = kb.next();
            
            strLst[0] = username;
            strLst[1] = password;

            loggedInUser = User.login(username, password);

            if (loggedInUser == null) {
                printLine("Username or password is incorrect. Please try again.\n");
            }
        }

        return loggedInUser;
    }


    public static void menu() {

        printLine();
        printLine("1. Create Post");
        printLine("2. Delete Post");
        printLine("3. Show Your Posts ");
        printLine("4. Search for User's Posts");
        printLine("5. Show all Posts");

        if(user instanceof Admin){
            printLine("6. Remove Post Privileges of User");
        }

        printLine("Type \"Q\" to Log Out");
        askForInput();
    }


    public static boolean createPost() {

        printLine();

        if (user.canPost) {

            printLine("Please enter your post:\n");
            String post_text = kb.nextLine();
            user.createPost(post_text);
            return true;

        } else {

            printLine("Your post privilieges have been revoked!");
            return false;
        } 
    }


    public static boolean deletePost(){

        printLine();

        if (user instanceof Moderator){
            printLine("1. Delete your Post");
            printLine("2. Delete any Post");
            System.out.println("\nChoose an option: ");
            int opt = kb.nextInt();

            if (opt == 1){
               return user_deletePost();
            }

            else {
                return mod_deletePost();
            }
        }
        else {
            return user_deletePost();
        }
    }


    public static boolean user_deletePost(){

        printLine();

        for(int i = 0; i < user.posts.size(); i++){
            
            printLine();
            System.out.println(user.posts.get(i).getID() + " | " + user.posts.get(i));
            printLine();
        }

        System.out.println("\nType the ID of the post you want to delete: ");
        int delete_id = kb.nextInt();
        return user.deletePost(delete_id);
    }


    public static boolean mod_deletePost(){
        
        printLine();

        for(int i = 0; i < Post.allPosts.size(); i++){
            printLine();
            System.out.println(Post.allPosts.get(i).getID() + " | " + Post.allPosts.get(i));
            printLine();
        }

        printLine("\nType the ID of the post you want to delete: ");
        int delete_id = kb.nextInt();
        return user.deletePost(delete_id);
    }


    public static void showAllPosts() {
        for (int i = 0; i < user.posts.size(); i++) {
            System.out.println(user.posts.get(i));
        }
    }


    // for testing
    public static void main(String[] args) {
        

    }
}
