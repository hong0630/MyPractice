package com.example.timepickertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TimePicker tpTime;
    private Button btOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews() {
        tpTime = (TimePicker)findViewById(R.id.tpIdTime);

        final Calendar cal = Calendar.getInstance();

        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);

        tpTime.setHour(hour+8);
        tpTime.setMinute(minute);
        tpTime.setIs24HourView(true);

        btOK = (Button)findViewById(R.id.btIdOk);
        btOK.setOnClickListener(btListener);
    }
    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String st = new String();
            st = "您輸入的時間為；"+tpTime.getHour()+"點"+
                    tpTime.getMinute() +"分";
            Toast.makeText(MainActivity.this,st,Toast.LENGTH_SHORT).show();
        }
    };
}