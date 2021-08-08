package com.example.datepickertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private CalendarView dpDate;
    private Button btOk;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {

        dpDate = (CalendarView)findViewById(R.id.dpIdDate);

        dpDate.setOnDateChangeListener(cvListener);
    }

    private CalendarView.OnDateChangeListener cvListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            btOk =(Button) findViewById(R.id.btIdOk);
            btOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"你選擇的日期為："+
                            year+"年"+month+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    
}