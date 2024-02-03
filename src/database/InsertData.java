package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.favre.lib.crypto.bcrypt.BCrypt;
import utils.Bill;
import utils.Cookie;
import utils.Input;

public class InsertData extends Connect{
    private PreparedStatement employee;
    private PreparedStatement login;
    private PreparedStatement movie;
    private PreparedStatement schedule;
    private PreparedStatement booking;
    SimpleDateFormat format;
    public InsertData()throws SQLException{
        format=new SimpleDateFormat("yyyy-MM-dd");
        employee=conn.prepareStatement("INSERT INTO employee(emp_name,emp_designation,gender) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
        login=conn.prepareStatement("INSERT INTO login(email,password,emp_id) VALUES (?,?,?)");
        movie=conn.prepareStatement("INSERT INTO movie(movie_name,genre_id,language_id,movie_duration,release_date) VALUES (?,?,?,?,?)");
        schedule=conn.prepareStatement("INSERT INTO movie_schedule(date,show_time,movie_id,screen_id) VALUES (?,?,?,?)");
        booking=conn.prepareStatement("INSERT INTO booking(schedule_id,booking_cost,emp_id,seat_id) VALUES (?,?,?,?)");
    }
    public void addEmployee(String name,String designation,String gender,String email,String password) throws SQLException{
        employee.setString(1, name);
        employee.setString(2, designation);
        employee.setString(3, gender);
        String hash=BCrypt.withDefaults().hashToString(10, password.toCharArray());
        login.setString(1, email);
        login.setString(2, hash);
        employee.executeUpdate();
        ResultSet res=employee.getGeneratedKeys();
        if(res.next()){
            login.setInt(3, res.getInt(1));
        }
        login.executeUpdate();
        System.out.println("Employee added");
    }
    public void addMovie(String name,int gender,int language,int duration,String date) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT movie_id FROM movie WHERE movie_name='"+name+"' AND language_id="+language);
        if(res.next()){
            System.out.println("movie already exists");
            System.out.println("movie_id: "+res.getString(1));
            System.out.println();
            return;
        }
        movie.setString(1, name);
        movie.setInt(2, gender);
        movie.setInt(3, language);
        movie.setInt(4,duration);
        movie.setString(5,date);
        movie.executeUpdate();
        System.out.println("\nmovie added");
    }
    public void addSchedule(String date,String show,int movie,int screen) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT schedule_id from movie_schedule WHERE date='"+date+"' AND show_time='"+show+"' AND screen_id="+screen);
        if(res.next()){
            System.out.println("schedule id: "+res.getString(1));
            System.out.println("schedule is alrady filled");
            return;
        }
        res=statement.executeQuery("SELECT release_date FROM movie WHERE movie_id="+movie);
        if(res.next()){
            try{
                Date d1=format.parse(date);
                Date d2=format.parse(res.getString(1));
                if(d1.before(d2)){
                    System.out.println("schedule date must be on or after release date");
                    System.out.println("Release Date: "+res.getString(1));
                    System.out.println();
                    return;
                }
            }
            catch(ParseException err){}
        }
        else{
            System.out.println("movie id not found\n");
            return;
        }
        schedule.setString(1, date);
        schedule.setString(2, show);
        schedule.setInt(3, movie);
        schedule.setInt(4, screen);
        schedule.executeUpdate();
        System.out.println("schedule added");
    }
    public void addBooking(int schedule,int seat) throws SQLException{
        Bill bill=Booking.cost(schedule, seat);
        if(bill==null){
            return;
        }
        System.out.println("-".repeat(60));
        System.out.printf("%-40s:%-20d\n","seat("+bill.seat+")",bill.seatCost);
        System.out.printf("%-40s:%-20d\n","screen("+bill.screenType+" "+bill.screenSize+"feet)",bill.screenCost);
        System.out.printf("%-40s:%-20d\n","seat("+bill.movieDuration+"min)",bill.movieCost);
        System.out.println("-".repeat(60));
        int total=bill.movieCost+bill.screenCost+bill.seatCost;
        System.out.printf("%-40s:%-20d\n","total",total);
        System.out.println();
        System.out.print("confirm bill(1/0): ");
        int choice=Input.sc.nextInt();
        if(choice==0){
            return;
        }
        booking.setInt(1, schedule);
        booking.setInt(2, total);
        booking.setInt(3, Cookie.empId);
        booking.setInt(4, seat);
        booking.executeUpdate();
        System.out.println("Bill added");
    }
}
