package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ENV;

public class Connect {
    protected static Connection conn;
    protected static Statement statement;
    public static void getConnection() throws SQLException{
        conn=DriverManager.getConnection(ENV.DataBaseURL, ENV.DataBaseUser, ENV.DataBasePassword);
        statement=conn.createStatement();
    }
}
