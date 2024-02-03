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
}
