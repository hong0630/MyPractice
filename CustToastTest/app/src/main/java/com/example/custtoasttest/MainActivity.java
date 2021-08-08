package com.example.custtoasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews() {
        btShow = (Button)findViewById(R.id.btIdShow);
        btShow.setOnClickListener(btShowfListener);
    }

    private View.OnClickListener btShowfListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ShowCustToast();
        }
    };

    private void ShowCustToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toastlayout,(ViewGroup)findViewById(R.id.toastlayout));

        TextView text = (TextView)findViewById(R.id.text);
        text.setText("這是使用者自訂的 Toast訊息！");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }
}