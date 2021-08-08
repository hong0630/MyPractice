package com.example.datetimedialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button btInpDate,btInpTime;

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews() {
        btInpDate = (Button)findViewById(R.id.btIdInpDate);
        btInpTime = (Button)findViewById(R.id.btIdInpTime);
        tvResult = (TextView)findViewById(R.id.tvIdResult);

        btInpDate.setOnClickListener(btInpDateListener);
        btInpTime.setOnClickListener(btInpTimeListener);
    }

    private View.OnClickListener btInpDateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvResult.setText("");

            Calendar now = Calendar.getInstance();

            DatePickerDialog datePicDig = new DatePickerDialog(MainActivity.this,
                    dsListener,now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));
            datePicDig.setTitle("輸入日期");
            datePicDig.setMessage("請輸入日期");
            datePicDig.setIcon(android.R.drawable.ic_dialog_info);
            datePicDig.setCancelable(false);
            datePicDig.show();
        }
    };
    private DatePickerDialog.OnDateSetListener dsListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            tvResult.setText("您輸入的日期是："+Integer.toString(year)+"年"+
                    Integer.toString(month+1)+"月"+Integer.toString(dayOfMonth)+"日");
        }
    };

    private View.OnClickListener btInpTimeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvResult.setText("");

            Calendar now = Calendar.getInstance();

            TimePickerDialog timePicDig = new TimePickerDialog(MainActivity.this,
                    tsListener,now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),true);
            timePicDig.setTitle("輸入時間");
            timePicDig.setMessage("請輸入時間");
            timePicDig.setIcon(android.R.drawable.ic_dialog_info);
            timePicDig.setCancelable(false);
            timePicDig.show();
        }
    };

    private TimePickerDialog.OnTimeSetListener tsListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            tvResult.setText("您輸入的時間是："+
                    Integer.toString(hourOfDay)+"點"+
                    Integer.toString(minute)+"分");
        }
    };
}