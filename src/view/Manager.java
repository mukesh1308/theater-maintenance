package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.Cookie;
import utils.Input;
import utils.resource;

public class Manager {
    public static String shows[]={"8:00","12:00","16:00","20:00"};
    public static void start(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        while(true){
            System.out.println("1.Today's revenue");
            System.out.println("2.View movie schedule");
            System.out.println("3.Add movie schedule");
            System.out.println("4.remove movie schedule");
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
                try{
                    resource.display.todayRevenue(resource.today);
                }
                catch(SQLException err){
                    System.out.println(err);
                    System.out.println("DB error");
                }
            }
            else if(choice==2){
                try{
                    System.out.println("1.Today Schedule");
                    System.out.println("2.Any Date");
                    System.out.println();
                    System.out.print("Enter your choice: ");
                    int c2=Input.sc.nextInt();
                    Input.sc.nextLine();
                    System.out.println();
                    if(c2==1){
                        resource.display.displaySchedule(resource.today);
                    }
                    else if(c2==2){
                        System.out.print("Enter date (yyyy-mm-dd): ");
                        String date=Input.sc.nextLine();
                        resource.display.displaySchedule(date);
                    }
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
            }
            else if(choice==3){
                try{
                    System.out.print("Enter schedule date (yyyy-MM-dd): ");
                    String date=Input.sc.nextLine();
                    try{
                        Date d1=format.parse(date);
                        Date d2=new Date();
                        if(d1.before(d2)){
                            System.out.println("you cannot enter the past date\n");
                            continue;
                        }
                    }
                    catch(ParseException err){
                        System.out.println("invalid date format\n");
                        continue;
                    };
                    System.out.println();
                    for(int i=0;i<shows.length;i++){
                        System.out.println(i+" -> "+shows[i]);
                    }
                    System.out.print("Enter show: ");
                    int time=Input.sc.nextInt();
                    System.out.print("Enter movie id: ");
                    int movie=Input.sc.nextInt();
                    System.out.print("Enter screen id: ");
                    int screen=Input.sc.nextInt();
                    Input.sc.nextLine();
                    System.out.println();
                    resource.insert.addSchedule(date, shows[time], movie, screen);
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
            }
            else if(choice==4){
                try{
                    System.out.print("Enter schedule id: ");
                    int id=Input.sc.nextInt();
                    Input.sc.nextLine();
                    System.out.println();
                    resource.delete.removeSchedule(id);
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
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
                try{
                    System.out.println("1.Top revenue");
                    System.out.println("2.find movie");
                    System.out.println();
                    System.out.println("Enter your choice: ");
                    int c6=Input.sc.nextInt();
                    Input.sc.nextLine();
                    System.out.println();
                    if(c6==1){
                        System.out.print("Enter top: ");
                        int top=Input.sc.nextInt();
                        Input.sc.nextLine();
                        resource.display.movieReportTop(top);
                    }
                    else if(c6==2){
                        System.out.print("Enter movie id: ");;
                        int id=Input.sc.nextInt();
                        Input.sc.nextLine();
                        resource.display.movieReport(id);
                    }
                }
                catch(SQLException err){
                    System.out.println(err);
                    System.out.println("DB error");
                }
            }
            else if(choice==7){
                try{
                    System.out.print("Enter employee name: ");
                    String name=Input.sc.nextLine();
                    System.out.print("Enter gender (M/F): ");
                    String gender=Input.sc.nextLine().toUpperCase();
                    System.out.print("Enter email: ");
                    String email=Input.sc.nextLine();
                    System.out.print("Enter password: ");
                    String password=Input.sc.nextLine();
                    System.out.println();
                    resource.insert.addEmployee(name, "booking clerk", gender, email, password);
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
            }
            else if(choice==8){
                try{
                    System.out.print("Enter employee id: ");
                    int emp=Input.sc.nextInt();
                    Input.sc.nextLine();
                    System.out.println();
                    resource.delete.removeEmployee(emp);
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
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
