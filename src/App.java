import java.sql.SQLException;

import database.Connect;
import utils.Input;

public class App {
    public static void main(String[] args){
        try{
            Connect.getConnection();
        }
        catch(SQLException err){
            System.out.println("Database not connecter.");
            return;
        }

        while(true){
            System.out.println("1.view");
            System.out.println("2.login");
            System.out.println("3.exit");
            System.out.println();
            System.out.println("enter your choice: ");
            int choice=Input.sc.nextInt();
            if(choice==1){
                
            }
            else if(choice==2){

            }
            else{
                break;
            }
        }
    }
}