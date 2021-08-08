package com.example.sysbroadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews() {
        btEnd = (Button)findViewById(R.id.tvIdSex);
        btEnd.setOnClickListener(btEndListener);
    }

    private View.OnClickListener btEndListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.exit(0);
        }
    };

}