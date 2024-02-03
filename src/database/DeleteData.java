package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Input;
import utils.resource;

public class DeleteData extends Connect{
    private PreparedStatement employee;
    private PreparedStatement login;
    private PreparedStatement schedule;
    public DeleteData() throws SQLException{
        employee=conn.prepareStatement("DELETE FROM employee WHERE emp_id=?");
        login=conn.prepareStatement("DELETE FROM login WHERE emp_id=?");
        schedule=conn.prepareStatement("DELETE FROM movie_schedule WHERE schedule_id=?");
    }
    public void removeEmployee(int id) throws SQLException{
        resource.display.displayEmployee(id);
        System.out.print("Do you want to delete (1/0): ");
        int choice=Input.sc.nextInt();
        Input.sc.nextLine();
        if(choice==1){
            employee.setInt(1, id);
            login.setInt(1, id);
            login.executeUpdate();
            employee.executeUpdate();
            System.out.println("employee removed");
            System.out.println();
        }
    }
    public void removeSchedule(int id)throws SQLException{
        ResultSet res=statement.executeQuery("SELECT COUNT(*) FROM booking WHERE schedule_id="+id);
        if(res.next()){
            if(res.getInt(1)>0){
                System.out.println("you connect delete schedule");
                System.out.println(res.getInt(1)+" users booked ticket");
                System.out.println();
                return;
            }
        }
        schedule.setInt(1, id);
        schedule.executeQuery();
        System.out.println("deleted schedule");
        System.out.println();
    }
}
