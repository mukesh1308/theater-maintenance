import java.sql.SQLException;

import database.Connect;
import database.InsertData;
import utils.Input;
import utils.resource;

public class App {
    public static void main(String[] args){
        try{
            Connect.getConnection();
            resource.init();
        }
        catch(SQLException err){
            System.out.println("Database not connecter.");
            return;
        }
        // try{
        //     resource.insert.addEmployee("k sathya", "manager", "M", "sathya210304@gmail.com", "1234");
        //     resource.insert.addEmployee("m mukesh kumar", "booking clerk", "M", "mukeshkumar130803@gmail.com", "yashini@070202");
        // }
        // catch(SQLException err){
        //     System.out.println(err);
        // }
        while(true){
            System.out.println("1.view");
            System.out.println("2.login");
            System.out.println("3.exit");
            System.out.println();
            System.out.print("enter your choice: ");
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