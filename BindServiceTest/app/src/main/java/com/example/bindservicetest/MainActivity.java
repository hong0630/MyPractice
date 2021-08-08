package com.example.bindservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btEnd,btPlay,btStop;
    private String Tag = "BindServiceTest";

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
            unbindService(serviceConn);
            Log.e(Tag,"unbinderService");
        }
    };

    private View.OnClickListener btPlayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this,PlayMusicService.class);
            bindService(intent,serviceConn, Context.BIND_AUTO_CREATE);
            Log.e(Tag,"bindService");
        }
    };

    private ServiceConnection serviceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(Tag,"onServiceConnected");
            Toast.makeText(MainActivity.this,"撥放音樂的服務(Service)已經連結.",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(Tag,"onServiceDisconnected");
            Toast.makeText(MainActivity.this,"撥放音樂的服務(Service)已經取消連結.",Toast.LENGTH_SHORT).show();
        }
    };
}