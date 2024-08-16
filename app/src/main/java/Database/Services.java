package Database;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj1.Home_src;

import org.w3c.dom.Text;

public class Services {

    public void Insert(SQLiteDatabase myDb, EditText tieude, Spinner dout, EditText ngaykt, android.content.Context context )
    {
        String TextTd = tieude.getText().toString();
        String selecteditems = dout.getSelectedItem().toString();
        String Textngaykt = ngaykt.getText().toString();

        ContentValues Values = new ContentValues();
        Values.put("tieuDe",TextTd);
        Values.put("doUt",selecteditems);
        Values.put("ngayKt",Textngaykt);

        String msg="";
        if(myDb.insert("Congviec", null, Values) == -1)
        {
            msg = "Thêm thất bại";
        }else{
            msg="Thêm thành công";
        }
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public int Deleted(SQLiteDatabase mydb, String tableName, String whereClause, String[] whereArgs, Context context)
    {
        int rowDeleted = mydb.delete(tableName,whereClause, whereArgs);
        String msg ="";
        if(rowDeleted==0)
        {
            msg="False";
        }else {
            msg="Succ";
        }
        Toast.makeText(context, msg,Toast.LENGTH_LONG).show();
        mydb.close();
        return rowDeleted;
    }


    //lấy tất cả bản ghi trong database = Cursor
    public Cursor finAll(SQLiteDatabase mydb, java.lang.String table)
    {
        Cursor cursor = mydb.query(table,null,null,null,null,null,null);
        return cursor;
    }

    public void FindbyID(SQLiteDatabase mydb,String id, EditText txt1, EditText txt2, Context context )
    {
        Cursor cursor = mydb.rawQuery("SELECT * FROM Congviec WHERE ID = ?", new String[] { id });
        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            txt1.setText(cursor.getString(1));
            txt2.setText(cursor.getString(3));
        }else
        {
            Toast.makeText(context,"Không tìm thấy dữ liệu",Toast.LENGTH_SHORT);
        }
        cursor.close();
    }


}
