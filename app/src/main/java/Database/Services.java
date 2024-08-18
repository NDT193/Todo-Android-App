package Database;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
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

    public void Deleted(SQLiteDatabase mydb, String tableName, String whereClause, String[] whereArgs, Context context)
    {
        int rowDeleted = mydb.delete(tableName,whereClause, whereArgs);
        String msg ="";
        if(rowDeleted==0)
        {
            msg="Xóa thất bại";
        }else {
            msg="Xóa thành công";
        }
        Toast.makeText(context, msg,Toast.LENGTH_LONG).show();
        mydb.close();
    }

    public int Update(SQLiteDatabase mydb, java.lang.String table, EditText txt1 ,Spinner sp1, EditText txt2,String id , Context context)
    {
        String upTD = txt1.getText().toString();
        String upDUT = sp1.getSelectedItem().toString();
        String upNgaykt = txt2.getText().toString();
        ContentValues values= new ContentValues();
        values.put("tieuDe",upTD);
        values.put("doUt",upDUT);
        values.put("ngayKt",upNgaykt);

        int rowUpdate = mydb.update(table,values,"ID =?",new String[]{id});

        return rowUpdate;
    }


    //lấy tất cả bản ghi trong database = Cursor
    public Cursor finAll(SQLiteDatabase mydb, java.lang.String table)
    {
        Cursor cursor = mydb.query(table,null,null,null,null,null,null);
        return cursor;
    }




}
