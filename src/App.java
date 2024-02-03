import java.sql.SQLException;

import database.Connect;
import database.Login;
import utils.Input;
import utils.resource;
import view.BookingClerk;
import view.Manager;
import view.Viewer;

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
        Manager.start();
        while(true){
            System.out.println("1.view");
            System.out.println("2.login");
            System.out.println("3.exit");
            System.out.println();
            System.out.print("enter your choice: ");
            int choice=Input.sc.nextInt();
            Input.sc.nextLine();
            System.out.println();
            if(choice==1){
                Viewer.start();
                System.out.println();
            }
            else if(choice==2){
                System.out.print("Enter your Email: ");
                String email=Input.sc.nextLine();
                System.out.print("Enter Password: ");
                String password=Input.sc.nextLine();
                System.out.println();
                try{
                    int check=Login.check(email, password);
                    if(check==1){
                        System.out.println("booking clerk");
                        System.out.println();
                        BookingClerk.start();
                    }
                    else if(check==2){
                        System.out.println("manager");
                        System.out.println();
                        Manager.start();
                    }
                    else if(check==-1){
                        System.out.println("not a employee");
                    }
                    else{
                        System.out.println("Invalid password");
                    }
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
                System.out.println();
            }
            else{
                break;
            }
        }
    }
}