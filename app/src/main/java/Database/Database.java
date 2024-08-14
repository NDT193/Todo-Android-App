package Database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Database {

    public void CreatDB(SQLiteDatabase mydb){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS Congviec(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tieuDe VARCHAR, doUt VARCHAR, ngayKt VARCHAR )";
            mydb.execSQL(sql);
        }catch (Exception e)
        {
            Log.e("Error","Tạo bảng thất bại");
        }
    }
    public void DeletedDB(SQLiteDatabase myDB)
    {
       try {
           myDB.execSQL("DROP TABLE IF EXISTS Congviec");
       }catch (Exception e)
       {
           Log.e("Error","Xóa thất bại");
       }
    }
}
