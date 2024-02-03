package view;

import java.sql.SQLException;

import database.Booking;
import utils.Cookie;
import utils.Input;
import utils.resource;

public class BookingClerk {
    public static void start(){
        while(true){
            System.out.println("1.booking");
            System.out.println("2.report");
            System.out.println("3.logout");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice=Input.sc.nextInt();
            Input.sc.nextLine();
            System.out.println();
            if(choice==1){
                try{
                    System.out.println("1.today");
                    System.out.println("2.any date");
                    System.out.println();
                    System.out.print("Enter your choice: ");
                    int c2=Input.sc.nextInt();
                    Input.sc.nextLine();
                    System.out.println();
                    if(c2==1){
                        resource.display.displaySchedule();
                    }
                    else{
                        System.out.print("Enter Date (yyyy-mm-dd): ");
                        String date=Input.sc.nextLine();
                        resource.display.displaySchedule(date);
                    }
                    System.out.print("Enter schedule id: ");
                    int schedule=Input.sc.nextInt();
                    Input.sc.nextLine();
                    if(schedule==-1){
                        continue;
                    }
                    int available[]=Booking.seatAvailablity(schedule);
                    resource.display.displaySeat(available);
                    System.out.print("Enter seat id: ");
                    int seat=Input.sc.nextInt();
                    Input.sc.nextLine();
                    if(seat==-1){
                        continue;
                    }
                    if(available[seat]<=0){
                        System.out.println("Seat is fill");
                        continue;
                    }
                    resource.insert.addBooking(schedule, seat);
                }
                catch(SQLException err){
                    System.out.println(err);
                    System.out.println("DB error");
                }
            }
            else if(choice==2){
                // try{
                //     System.out.println("1.today");
                //     System.out.println("2.any date");
                //     System.out.println("3.total");
                // }
                // catch(SQLException err){

                // }
            }
            else if(choice==3){
                Cookie.empId=-1;
                Cookie.designation=null;
                break;
            }
            System.out.println();
        }
    }
}
