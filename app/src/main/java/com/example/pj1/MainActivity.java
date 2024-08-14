package com.example.pj1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list ;
    public SQLiteDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mydb = openOrCreateDatabase("Congviec", MODE_PRIVATE, null);

        list= findViewById(R.id.list1);

        List<String> liststring = new ArrayList<>();
        ArrayAdapter adap = new ArrayAdapter(this, android.R.layout.simple_list_item_1,liststring);
        list.setAdapter(adap);
        Select(liststring, mydb, adap,"Congviec");


    }

    public void Select(List s, SQLiteDatabase db, ArrayAdapter adap, java.lang.String table){
        s.clear();
        Cursor c = db.query(table,null,null,null,null, null, null);
        c.moveToNext();
        String data ="";
        while (c.isAfterLast()==false)
        {
            data= c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2);
            c.moveToNext();
            s.add(data);
        }
        c.close();
        adap.notifyDataSetChanged();
    }


}