package zackage;

import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;

public class Admin extends User {
    
    public Admin(String u, String pw) {
        super(u, pw);
    }

    protected Attribute color = RED_TEXT();


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
    
    @Override
    public void printUsername(){

        System.out.println(colorize(username + " (A)", color));
    }
}
