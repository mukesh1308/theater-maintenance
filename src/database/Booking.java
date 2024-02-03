package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Bill;

public class Booking extends Connect {
    public static int[] seatAvailablity(int id) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT basic_seat,economic_seat,luxurious_seat FROM screen WHERE screen_id IN (SELECT screen_id FROM movie_schedule WHERE schedule_id="+id+")");
        int arr[]=new int[3];
        if(res.next()){
            arr[0]=res.getInt(1);
            arr[1]=res.getInt(2);
            arr[2]=res.getInt(3);
        }
        else{
            System.out.println("invalid schedule id");
            return null;
        }
        res=statement.executeQuery("SELECT seat_id,COUNT(*) FROM booking WHERE schedule_id="+id+" GROUP BY seat_id ORDER BY seat_id");
        while(res.next()){
            arr[res.getInt(1)-1]-=res.getInt(2);
        }
        System.out.println();
        return arr;
    }
    public static Bill cost(int schedule,int seat) throws SQLException{
        ResultSet res=statement.executeQuery("Select seat_type,seat_cost FROM seat_cost WHERE seat_id="+seat);
        Bill bill=new Bill();
        if(res.next()){
            bill.seat=res.getString(1);
            bill.seatCost=res.getInt(2);
        }
        else{
            System.out.println("invalid seat id");
            return null;
        }
        res=statement.executeQuery("SELECT screen_type,screen_size,screen_cost FROM screen_types WHERE screen_type_id IN (SELECT screen_type_id FROM screen WHERE screen_id IN (SELECT screen_id FROM movie_schedule WHERE schedule_id="+schedule+"))");
        if(res.next()){
            bill.screenType=res.getString(1);
            bill.screenSize=res.getInt(2);
            bill.screenCost=res.getInt(3);
        }
        else{
            System.out.println("invalid schedule id");
        }
        res=statement.executeQuery("SELECT movie_duration FROM movie WHERE movie_id IN (SELECT movie_id FROM movie_schedule WHERE schedule_id="+schedule+")");
        if(res.next()){
            bill.movieDuration=res.getInt(1);
        }
        if(bill.movieDuration<=100){
            bill.movieCost=15;
        }
        else if(bill.movieDuration<=150){
            bill.movieCost=20;
        }
        else{
            bill.movieCost=25;
        }
        return bill;
    }
}
