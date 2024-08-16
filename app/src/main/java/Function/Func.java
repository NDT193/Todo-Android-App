package Function;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


import com.example.pj1.R;

import java.util.Calendar;

import Database.Services;

public class Func {

    private Services services = new Services();
    public void Spiner(Spinner spinner, Context context) {
        //Dùng 1 mang String[] để lưu trữ các phần tử
        String[] items = new String[]{"Thấp", "Trung Bình", "Cao"};
        // Tạo một ArrayAdapter từ mảng String
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items);
        //Xét adpter cho spinner để hiện các items đã thêm
        spinner.setAdapter(adapter);
    }

    public void Datepicker(ImageView img, EditText text, Context context)
    {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Hiển thị ngày đã chọn vào EditText
                                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                text.setText(selectedDate);
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }
        });
    }

    public void UpdateDialog(int gravity, Context context, SQLiteDatabase mydb, String id)
    {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window =dialog.getWindow();
        if (window==null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAtt = window.getAttributes();
        windowAtt.gravity = gravity;
        window.setAttributes(windowAtt);

        if(Gravity.BOTTOM == gravity)
        {
         dialog.setCancelable(false);
        }else {
            dialog.setCancelable(true);
        }

        EditText upTD = dialog.findViewById(R.id.upTD);
        Spinner upDUT = dialog.findViewById(R.id.upDUT);
        EditText upNgaykt = dialog.findViewById(R.id.upNgaykt);
        Button btlYes = dialog.findViewById(R.id.btl_yes);
        Button btlNo = dialog.findViewById(R.id.btl_no);

        Spiner(upDUT, context);
        services.FindbyID(mydb, id, upTD, upNgaykt,context);

        btlYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btlNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setCancelable(true);
            }
        });

        dialog.show();
    }

}

