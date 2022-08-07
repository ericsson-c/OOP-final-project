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
    private User user;

    // width (in characters) of program "screen"
    private static int screenWidth = 50;

/* *********************************************** 
                    CONSTRUCTORS
************************************************** */

    public UI() {

    };

    public UI(User user) {
        this.user = user;
    }


/* *********************************************** 
                    METHODS
************************************************** */

    public static void printLine() {

        System.out.println("*".repeat(screenWidth));
    }

    public static void printLine(String str) {
    
        String output = String.format("%-" + screenWidth + "s", str);
        System.out.println(output);
    }


    public void homepage() {

        printLine();
        printLine("Welcome, " + user.getUsername() + "!");
        printLine("Press any key to login.");
    }


    public void menu() {

        printLine();
    }

    public void createPost() {

    }

    public void deletePost() {

    }

    public static void main(String[] args) {
        
        UI ui = new UI(new User("ericsson", "password"));
        ui.homepage();
    }

    
    
}
