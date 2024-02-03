package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.Input;
import utils.resource;

public class DeleteData extends Connect{
    private PreparedStatement employee;
    private PreparedStatement login;
    public DeleteData() throws SQLException{
        employee=conn.prepareStatement("DELETE FROM employee WHERE emp_id=?");
        login=conn.prepareStatement("DELETE FROM login WHERE emp_id=?");
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
}
