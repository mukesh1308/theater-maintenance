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
            System.out.println("3.update password");
            System.out.println("4.logout");
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
                        resource.display.displaySchedule(resource.today);
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
                    if(available[seat-1]<=0){
                        System.out.println("Seat is fill");
                        continue;
                    }
                    resource.insert.addBooking(schedule, seat);
                }
                catch(SQLException err){
                    System.out.println("DB error");
                }
            }
            else if(choice==2){
                try{
                    System.out.println("1.today");
                    System.out.println("2.any date");
                    System.out.println("3.total");
                    System.out.println();
                    System.out.print("Enter your choice: ");
                    int c2=Input.sc.nextInt();
                    Input.sc.nextLine();
                    System.out.println();
                    if(c2==1){
                        resource.display.bookingReportByDate(resource.today);
                    }
                    else if(c2==2){
                        System.out.print("Enter date (yyyy-mm-dd): ");
                        String date=Input.sc.nextLine();
                        resource.display.bookingReportByDate(date);
                    }
                    else if(c2==3){
                        resource.display.bookingReport();
                    }
                }
                catch(SQLException err){
                    System.out.println(err);
                    System.out.println("DB error");
                }
            }
            else if(choice==3){
                try{
                    System.out.print("Enter password: ");
                    String password=Input.sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword=Input.sc.nextLine();
                    System.out.print("Enter conform password: ");
                    String conformPassword=Input.sc.nextLine();
                    if(newPassword.equals(conformPassword)){
                        resource.update.updatePassword(password, newPassword);
                    }
                    else{
                        System.out.println("password and conform password does not match");
                    }
                }
                catch(SQLException err){
                    System.out.println(err);
                    System.out.println("DB error");
                }
            }
            else if(choice==4){
                Cookie.empId=-1;
                Cookie.designation=null;
                break;
            }
            System.out.println();
        }
    }
}
