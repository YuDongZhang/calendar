package com.example.customcalendarview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customcalendarview.widget.CustomCalendarView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {

    private CustomCalendarView calendarView;
    private TextView tv_date;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 计算日期差异
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // 定义两个日期
            LocalDate date1 = LocalDate.of(2023, 8, 1);
            LocalDate date2 = LocalDate.of(2023, 8, 31);
            long daysDiff = ChronoUnit.DAYS.between(date1, date2);
            Log.i("TAG", "onCreate: "+daysDiff);
        }



        calendarView = findViewById(R.id.calendarView);
        tv_date = findViewById(R.id.tv_date);
        ImageView iv_last_month = findViewById(R.id.iv_last_month);
        ImageView iv_next_month = findViewById(R.id.iv_next_month);

        String month = calendarView.getMonth();
        tv_date.setText(month);

        iv_last_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上一个月
                calendarView.goLastMonth();
            }
        });
        iv_next_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下一个月
                calendarView.goNextMonth();
            }
        });

        calendarView.setOnMonthChangerListener(new CustomCalendarView.OnMonthChangerListener() {
            @Override
            public void onMonthChanger(String lastMonth, String newMonth) {
                tv_date.setText(newMonth);
            }
        });

    }
}