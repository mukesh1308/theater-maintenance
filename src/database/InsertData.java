package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class InsertData extends Connect{
    private PreparedStatement employee;
    private PreparedStatement login;
    private PreparedStatement movie;
    public InsertData()throws SQLException{
        employee=conn.prepareStatement("INSERT INTO employee(emp_name,emp_designation,gender) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
        login=conn.prepareStatement("INSERT INTO login(email,password,emp_id) VALUES (?,?,?)");
        movie=conn.prepareStatement("INSERT INTO movie(movie_name,genre_id,language_id,movie_duration,release_date) VALUES (?,?,?,?,?)");
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
}
