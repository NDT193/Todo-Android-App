package com.example.pj1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import Database.Services;
import Function.Func;

public class Add_src  extends AppCompatActivity {
    ImageView backtohome_src, datepicker;
    Button them;
    EditText tieude, ngaykt;
    Spinner dut;

    private Services services = new Services();
    private SQLiteDatabase mydb;
    public Func func= new Func();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_scr);
        mydb = openOrCreateDatabase("Congviec", MODE_PRIVATE, null);

        tieude= findViewById(R.id.tieude);
        ngaykt= findViewById(R.id.ngaykt);
        dut = findViewById(R.id.sp_dut);
        datepicker =findViewById(R.id.date_picker);

        func.Spiner(dut,Add_src.this );
        func.Datepicker(datepicker,ngaykt,Add_src.this);


        backtohome_src = findViewById(R.id.backtohome_src);
        backtohome_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                func.goToActivity(Add_src.this, Home_src.class);
            }
        });

        them = findViewById(R.id.addButton);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tieude.equals("") || ngaykt.equals(""))
                {
                    Toast.makeText(Add_src.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    services.Insert(mydb, tieude, dut, ngaykt, Add_src.this);
                }
                tieude.setText("");
                ngaykt.setText("");
            }
        });
    }



}
