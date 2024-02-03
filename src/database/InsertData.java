package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class InsertData extends Connect{
    private PreparedStatement employee;
    private PreparedStatement login;
    public InsertData()throws SQLException{
        employee=conn.prepareStatement("INSERT INTO employee(emp_name,emp_designation,gender) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
        login=conn.prepareStatement("INSERT INTO login(email,password,emp_id) VALUES (?,?,?)");
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
}
