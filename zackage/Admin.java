package zackage;

public class Admin extends User {
    
    public Admin(String u, String pw) {
        super(u, pw);
    }

    protected String color = "\u001B[41m";


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
    
    public void printUser(){

        System.out.println(username + " (A)");

    }
}
