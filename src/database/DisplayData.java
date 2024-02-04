package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Cookie;

public class DisplayData extends Connect{
    public DisplayData() throws SQLException{

    }

    public void displayGenre() throws SQLException{
        ResultSet res=statement.executeQuery("SELECT * FROM genres");
        System.out.printf("%-15s %-20s\n","genre_id","genre");
        System.out.println("-".repeat(50));
        while(res.next()){
            System.out.printf("%-15s %-20s\n",res.getInt(1),res.getString(2));
        }
        System.out.println();
    }

    public void displayLanguage() throws SQLException{
        ResultSet res=statement.executeQuery("SELECT * FROM languages");
        System.out.printf("%-15s %-20s\n","language_id","language");
        System.out.println("-".repeat(50));
        while(res.next()){
            System.out.printf("%-15s %-20s\n",res.getInt(1),res.getString(2));
        }
        System.out.println();
    }

    public void displayEmployee(int id) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT * FROM employee WHERE emp_id="+id);
        System.out.printf("%-20s %-30s %-30s %-30s\n", "Employee id","Employee Name","Employee gender","Employee designation");
        System.out.println("-".repeat(130));
        while(res.next()){
            System.out.printf("%-20s %-30s %-30s %-30s\n",res.getInt(1),res.getString(2),res.getString(4),res.getString(3));
        }
        System.out.println();
    }

    public void displaySchedule(String date) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT S.schedule_id,S.date,S.show_time,M.movie_name,L.language,Sc.screen_no,St.screen_type FROM movie_schedule S JOIN movie M USING(movie_id) JOIN languages L USING(language_id) JOIN screen Sc USING(screen_id) JOIN screen_types St USING(screen_type_id) WHERE S.Date='"+date+"' AND (show_time>CURRENT_TIMESTAMP OR S.date>CURRENT_TIMESTAMP)");
        System.out.printf("%-20s%-20s%-20s%-40s%-20s%-20s%-20s\n","Schedule id","Date","Time","movie name","language","screen","screen type");
        System.out.println("-".repeat(150));
        while(res.next()){
            System.out.printf("%-20d%-20s%-20s%-40s%-20s%-20s%-20s\n",res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getInt(6),res.getString(7));
        }
        System.out.println();
    }
    public void displaySeat(int available[]) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT * FROM seat_cost");
        System.out.printf("%-20s%-30s%-20s%-20s\n","seat id","seat type","seat cost","available");
        System.out.println("-".repeat(80));
        while(res.next()){
            System.out.printf("%-20s%-30s%-20s%-20d\n",res.getInt(1),res.getString(2),res.getString(3),available[res.getInt(1)-1]);
        }
        System.out.println();
    }
    public void bookingReport() throws SQLException{
        ResultSet res=statement.executeQuery("SELECT seat_id,COUNT(*),SUM(booking_cost) FROM booking WHERE emp_id="+Cookie.empId+" GROUP BY seat_id");
        int arr[][]=new int[3][2];
        while(res.next()){
            arr[res.getInt(1)][0]=res.getInt(2);
            arr[res.getInt(1)][1]=res.getInt(3);
        }
        res=statement.executeQuery("SELECT * FROM seat_cost");
        System.out.printf("%-20s%-20s%-20s\n","seat type","ticket sold","total money");
        System.out.println("-".repeat(60));
        int count=0;
        int sum=0;
        while(res.next()){
            System.out.printf("%-20s%-20d%-20d\n",res.getString(2),arr[res.getInt(1)-1][0],arr[res.getInt(1)-1][1]);
            count+=arr[res.getInt(1)-1][0];
            sum+=arr[res.getInt(1)-1][1];
        }
        System.out.println("-".repeat(60));
        System.out.printf("%-20s%-20d%-20d\n","total",count,sum);
        System.out.println();
    }
    public void bookingReportByDate(String date) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT seat_id,COUNT(*),SUM(booking_cost) FROM booking WHERE emp_id="+Cookie.empId+" AND date_time='"+date+"' GROUP BY seat_id");
        int arr[][]=new int[3][2];
        while(res.next()){
            arr[res.getInt(1)][0]=res.getInt(2);
            arr[res.getInt(1)][1]=res.getInt(3);
        }
        res=statement.executeQuery("SELECT * FROM seat_cost");
        System.out.printf("%-20s%-20s%-20s\n","seat type","ticket sold","total money");
        System.out.println("-".repeat(60));
        int count=0;
        int sum=0;
        while(res.next()){
            System.out.printf("%-20s%-20d%-20d\n",res.getString(2),arr[res.getInt(1)-1][0],arr[res.getInt(1)-1][1]);
            count+=arr[res.getInt(1)-1][0];
            sum+=arr[res.getInt(1)-1][1];
        }
        System.out.println("-".repeat(60));
        System.out.printf("%-20s%-20d%-20d\n","total",count,sum);
        System.out.println();
    }
    public void movieReportTop(int top) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT M.movie_id,M.movie_name,SUM(B.booking_cost) FROM booking B JOIN movie_schedule S USING(schedule_id) JOIN movie M USING(movie_id) GROUP BY M.movie_id LIMIT "+top);
        System.out.printf("%-20s%-40s%-20s\n", "movie id","movie name","revenue");
        System.out.println("-".repeat(90));
        while(res.next()){
            System.out.printf("%-20d%-40s%-20d\n",res.getInt(1),res.getString(2),res.getInt(3));
        }
        System.out.println();
    }
    public void movieReport(int id) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT M.movie_id,M.movie_name,SUM(B.booking_cost) FROM booking B JOIN movie_schedule S USING(schedule_id) JOIN movie M USING(movie_id) WHERE M.movie_id="+id+" GROUP BY M.movie_id");
        System.out.printf("%-20s%-40s%-20s\n", "movie id","movie name","revenue");
        System.out.println("-".repeat(90));
        boolean flag=true;
        while(res.next()){
            System.out.printf("%-20d%-40s%-20d\n",res.getInt(1),res.getString(2),res.getInt(3));
            flag=false;
        }
        if(flag){
            res=statement.executeQuery("SELECT movie_id,movie_name FROM movie WHERE movie_id="+id);
            if(res.next()){
                System.out.printf("%-20d%-40s%-20d\n",res.getInt(1),res.getString(2),0);
            }
        }
        System.out.println();
    }
    public void todayRevenue(String date)throws SQLException{
        ResultSet res=statement.executeQuery("SELECT E.emp_name,COUNT(B.booking_cost),SUM(B.booking_cost) FROM booking B JOIN employee E USING(emp_id) WHERE E.emp_designation='booking clerk' AND schedule_id IN (SELECT schedule_id FROM movie_schedule WHERE date='"+date+"') GROUP BY E.emp_id");
        System.out.printf("%-30s%-20s%-20s\n","employee name","ticket sold","total money");
        System.out.println("-".repeat(70));
        int count=0;
        int sum=0;
        while(res.next()){
            System.out.printf("%-30s%-20d%-20d\n",res.getString(1),res.getInt(2),res.getInt(3));
            count+=res.getInt(2);
            sum+=res.getInt(3);
        }
        System.out.println("-".repeat(70));
        System.out.printf("%-30s%-20d%-20d\n","total",count,sum);
        System.out.println();
    }
}
