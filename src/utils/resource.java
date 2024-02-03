package utils;

import java.sql.SQLException;

import database.DeleteData;
import database.DisplayData;
import database.InsertData;

public class resource {
    public static InsertData insert;
    public static DisplayData display;
    public static DeleteData delete;

    public static void init() throws SQLException{
        insert=new InsertData();
        display=new DisplayData();
        delete=new DeleteData();
    }
}
