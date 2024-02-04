package view;

import java.sql.SQLException;

import utils.Input;
import utils.resource;

public class Viewer {
    public static void start() {
        while (true) {
            System.out.println("1.Today Schedule");
            System.out.println("2.Any Date");
            System.out.println("3.go back");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = Input.sc.nextInt();
            Input.sc.nextLine();
            System.out.println();
            if (choice == 1) {
                try{
                    resource.display.displaySchedule(resource.today);
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
            } else if (choice == 2) {
                try{
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String date = Input.sc.nextLine();
                    resource.display.displaySchedule(date);
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
            }
            else if(choice==3){
                break;
            }
        }
    }
}
