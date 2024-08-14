package Database;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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


    public Cursor finAll(SQLiteDatabase mydb, java.lang.String table)
    {
        Cursor cursor = mydb.query(table,null,null,null,null,null,null);

        return cursor;
    }


}
