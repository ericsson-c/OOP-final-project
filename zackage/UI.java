package zackage;

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
    private String username;

    // width (in characters) of program "screen"
    private static int screenWidth = 50;

/* *********************************************** 
                    CONSTRUCTORS
************************************************** */

    public UI() {

    };

    public UI(String user) {
        this.username = user;
    }


/* *********************************************** 
                    METHODS
************************************************** */

    public static void printLine() {

        System.out.println("*".repeat(screenWidth));
    }

    public void homepage() {

        printLine();
        System.out.println("Welcome, " + username + "!");
    }


    public void menu() {

        printLine();

    }

    public void createPost() {

    }

    public void deletePost() {

    }

    
    
}
