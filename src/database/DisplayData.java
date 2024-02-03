package database;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void displaySchedule() throws SQLException{
        ResultSet res=statement.executeQuery("SELECT S.schedule_id,S.date,S.show_time,M.movie_name,L.language,Sc.screen_no,St.screen_type FROM movie_schedule S JOIN movie M USING(movie_id) JOIN languages L USING(language_id) JOIN screen Sc USING(screen_id) JOIN screen_types St USING(screen_type_id) WHERE S.Date=CURRENT_TIMESTAMP");
        System.out.printf("%-20s%-20s%-20s%-40s%-20s%-20s%-20s\n","Schedule id","Date","Time","movie name","language","screen","screen type");
        System.out.println("-".repeat(160));
        while(res.next()){
            System.out.printf("%-20d%-20s%-20s%-40s%-20s%-20s%-20s\n",res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getInt(6),res.getString(7));
        }
        System.out.println();
    }
    public void displaySchedule(String date) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT S.schedule_id,S.date,S.show_time,M.movie_name,L.language,Sc.screen_no,St.screen_type FROM movie_schedule S JOIN movie M USING(movie_id) JOIN languages L USING(language_id) JOIN screen Sc USING(screen_id) JOIN screen_types St USING(screen_type_id) WHERE S.Date='"+date+"'");
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
}
