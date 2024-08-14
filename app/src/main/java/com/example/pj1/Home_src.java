package com.example.pj1;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import Database.Services;

public class Home_src extends AppCompatActivity {

    Button them;
    LinearLayout linearLayout;
    private static SQLiteDatabase mydb;

    private Services services = new Services();



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
                Intent act = new Intent(Home_src.this, Add_src.class);
                startActivity(act);
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
            ImageView deleted = view.findViewById(R.id.bin);
            ImageView pencil = view.findViewById(R.id.pencil);

            card_tieude.setText(cursor.getString(1));
            card_dut.setText(cursor.getString(2));
            linearLayout.addView(view);
            cursor.moveToNext();
        }
        cursor.close();

    }


}
