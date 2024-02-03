package utils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DeleteData;
import database.DisplayData;
import database.InsertData;
import database.UpdateData;

public class resource {
    public static InsertData insert;
    public static DisplayData display;
    public static DeleteData delete;
    public static UpdateData update;
    public static String today;

    public static void init() throws SQLException{
        insert=new InsertData();
        display=new DisplayData();
        delete=new DeleteData();
        update=new UpdateData();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        today=format.format(new Date());
    }
}
