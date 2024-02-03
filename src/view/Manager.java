package view;

import java.sql.SQLException;

import utils.Cookie;
import utils.Input;
import utils.resource;

public class Manager {
    public static void start(){
        while(true){
            System.out.println("1.Today's revenue");
            System.out.println("2.View movie schedule");
            System.out.println("3.Add movie schedule");
            System.out.println("4.Edit movie schedule");
            System.out.println("5.Add new movie");
            System.out.println("6.movie report");
            System.out.println("7.Add Booking clerk");
            System.out.println("8.remove Booking clerk");
            System.out.println("9.logout");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice=Input.sc.nextInt();
            Input.sc.nextLine();
            System.out.println();
            if(choice==1){
    
            }
            else if(choice==2){
    
            }
            else if(choice==3){
    
            }
            else if(choice==4){
    
            }
            else if(choice==5){
                try{
                    System.out.print("Enter movie name: ");
                    String name=Input.sc.nextLine();
                    System.out.print("Enter release date (yyyy-mm-dd):");
                    String date=Input.sc.nextLine();
                    System.out.print("Enter movie duration (min): ");
                    int duration=Input.sc.nextInt();
                    System.out.println("\n");
                    resource.display.displayGenre();
                    System.out.print("Enter genre id: ");
                    int genre=Input.sc.nextInt();
                    System.out.println("\n");
                    resource.display.displayLanguage();
                    System.out.print("Enter language id: ");
                    int language=Input.sc.nextInt();
                    Input.sc.nextLine();
                    resource.insert.addMovie(name, genre, language, duration, date);
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
            }
            else if(choice==6){ 
                
            }
            else if(choice==7){
    
            }
            else if(choice==8){
    
            }
            else if(choice==9){
                Cookie.empId=-1;
                Cookie.designation=null;
                break;
            }
            System.out.println();
        }
    }
}
