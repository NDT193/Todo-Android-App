package Function;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    public static void goToActivity(Context context, Class<?> activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }

    public boolean CompaeTime(String inputTime)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date currentTime = new Date();
            Date inputDate = sdf.parse(inputTime);

            // So sánh thời gian hiện tại với thời gian nhập vào
            return currentTime.compareTo(inputDate) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }

    }

}

