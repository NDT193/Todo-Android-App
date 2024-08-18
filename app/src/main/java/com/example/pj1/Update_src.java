package com.example.pj1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Database.Evn;
import Database.Services;
import Function.Func;

public class Update_src extends AppCompatActivity {

    public SQLiteDatabase mydb;
    private Services services= new Services();
    private Func func = new Func();
    private Evn evn = new Evn();
    EditText upTD, upNgaykt;
    Button btlNo, btlYes;
    Spinner upDUT;
    ImageView troVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_src);
        mydb = openOrCreateDatabase("Congviec", MODE_PRIVATE, null);

        troVe = findViewById(R.id.backIcon);
        upTD = findViewById(R.id.upTD);
        upDUT = findViewById(R.id.upDUT);
        upNgaykt =findViewById(R.id.upNgaykt);
        btlYes = findViewById(R.id.btl_yes);
        btlNo = findViewById(R.id.btl_no);

        func.Spiner(upDUT,Update_src.this);
        GetData();

        troVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                func.goToActivity(Update_src.this, Home_src.class);
            }
        });
        btlNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upTD.setText("");
                upNgaykt.setText("");
            }
        });

        btlYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(upTD.equals("")||upNgaykt.equals(""))
                {
                    Toast.makeText(Update_src.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }else {
                   int rowUP =  services.Update(mydb,"Congviec",upTD,upDUT,upNgaykt,evn.getID,Update_src.this);
                   String msg ="";
                   if(rowUP==0)
                   {
                       msg = "Thay đổi thất bại";
                   }else {
                       msg ="Thay đổi thành công";
                   }
                    Toast.makeText(Update_src.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void GetData()
    {
        Cursor cursor = services.finAll(mydb,"Congviec");
        cursor.moveToNext();

        while(cursor.isAfterLast()==false) {
            upTD.setText(cursor.getString(1));
            upNgaykt.setText(cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
    }

}