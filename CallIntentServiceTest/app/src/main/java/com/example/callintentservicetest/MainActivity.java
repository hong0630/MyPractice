package com.example.callintentservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btEnd,btPlay,btStop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        btEnd = (Button)findViewById(R.id.btIdEnd);
        btPlay = (Button)findViewById(R.id.btIdPlay);
        btStop = (Button)findViewById(R.id.btIdStop);

        btEnd.setOnClickListener(btEndListener);
        btStop.setOnClickListener(btStopListener);
        btPlay.setOnClickListener(btPlayListener);
    }

    private View.OnClickListener btEndListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener btStopListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.stop();

        }
    };

    private View.OnClickListener btPlayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,MyIntentService.class);
            startService(intent);
        }
    };
}