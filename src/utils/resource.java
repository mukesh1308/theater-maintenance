package utils;

import java.sql.SQLException;

import database.InsertData;

public class resource {
    public static InsertData insert;

    public static void init() throws SQLException{
        insert=new InsertData();
    }
}
