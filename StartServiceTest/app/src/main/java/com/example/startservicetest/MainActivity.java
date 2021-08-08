package com.example.startservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btEnd,btPlay,btStop;
    private String Tag = "StartServiceTest";

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
        btPlay.setOnClickListener(btPlayListener);
        btStop.setOnClickListener(btStopListener);
    }

    private View.OnClickListener btEndListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener btPlayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,PlayMusicService.class);
            startService(intent);
            Log.e(Tag,"startService");
        }
    };

    private View.OnClickListener btStopListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,PlayMusicService.class);
            stopService(intent);
            Log.e(Tag,"stopService");
        }
    };
}