package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import at.favre.lib.crypto.bcrypt.BCrypt;
import utils.Cookie;

public class Login extends Connect{
    public static int check(String email,String password) throws SQLException{
        ResultSet res=statement.executeQuery("SELECT L.password,L.emp_id,E.emp_designation FROM login L JOIN employee E using(emp_id) WHERE email='"+email+"'");
        if(res.next()){
            BCrypt.Result vefy=BCrypt.verifyer().verify(password.toCharArray(), res.getString(1));
            if(vefy.verified){
                Cookie.empId=res.getInt(2);
                Cookie.designation=res.getString(3);
                if(Cookie.designation.equals("booking clerk")){
                    return 1;
                }
                return 2;
            }
            return -2;
        }
        return -1;
    }
}
