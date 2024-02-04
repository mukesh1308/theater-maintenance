package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import at.favre.lib.crypto.bcrypt.BCrypt;
import utils.Cookie;


public class UpdateData extends Connect{
    private PreparedStatement updatePassword;
    public UpdateData() throws SQLException{
        updatePassword=conn.prepareStatement("UPDATE login SET password=? WHERE emp_id=?");
    }
    public void updatePassword(String password,String newPassword) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT password FROM login WHERE emp_id="+Cookie.empId);
        if(res.next()){
            BCrypt.Result vefy=BCrypt.verifyer().verify(password.toCharArray(), res.getString(1));
            if(vefy.verified){
                updatePassword.setString(1,BCrypt.withDefaults().hashToString(10,newPassword.toCharArray()));
                updatePassword.setInt(2, Cookie.empId);
                updatePassword.executeUpdate();
                System.out.println("password updated");
            }
            else{
                System.out.println("Invalid password");
            }
            return;
        }
        System.out.println("not a user");
        return;
    }
}
