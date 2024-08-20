package com.example.pj1;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Database.Evn;
import Database.Services;
import Function.Func;

public class Home_src extends AppCompatActivity {

    Button them;
    LinearLayout linearLayout;
    private static SQLiteDatabase mydb;
    private Services services = new Services();
    private Func fun = new Func();
    private Evn evn = new Evn();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_src);
        mydb = openOrCreateDatabase("Congviec", MODE_PRIVATE, null);

        linearLayout = findViewById(R.id.container);
        them = findViewById(R.id.themcv);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fun.goToActivity(Home_src.this, Add_src.class);
            }
        });

        addCard();
    }

    public void addCard()
    {
        Cursor cursor = services.finAll(mydb,"Congviec");
        cursor.moveToNext();
        while(cursor.isAfterLast()==false)
        {
            View view = getLayoutInflater().inflate(R.layout.card_xml, null);

            EditText card_tieude = view.findViewById(R.id.cardTD);
            TextView card_dut = view.findViewById(R.id.cardDut);
            TextView time_exp = view.findViewById(R.id.time_expierd);
            ImageView deleted = view.findViewById(R.id.bin);
            ImageView pencil = view.findViewById(R.id.pencil);

            String id = cursor.getString(0);
            card_tieude.setText(cursor.getString(1));
            card_dut.setText(cursor.getString(2));
            String invalitdate = cursor.getString(3);

            if(fun.CompaeTime(invalitdate))
            {
                time_exp.setVisibility(view.VISIBLE);
            }

            deleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    services.Deleted(mydb,"Congviec", "ID =?",new String[] {id},Home_src.this);
                    ViewGroup parent = (ViewGroup) view.getParent();
                    parent.removeView(view);
                }
            });

            pencil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v2) {
                    evn.getID = id ;
                    fun.goToActivity(Home_src.this,Update_src.class);
                }
            });
            linearLayout.addView(view);
            cursor.moveToNext();
        }
        cursor.close();
    }

}
