package com.example.userbroadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btSendBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        btSendBD = (Button)findViewById(R.id.btIdSendBD);
        btSendBD.setOnClickListener(btSendBDListener);
    }

    private View.OnClickListener btSendBDListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction("andbas.broadcast.send.UPDATE");
            intent.putExtra("msg","已完成更新作業");
            sendBroadcast(intent);
        }
    };
}