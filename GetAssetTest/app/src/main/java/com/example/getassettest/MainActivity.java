package com.example.getassettest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private TextView tvIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews(){
        tvIntro = (TextView)findViewById(R.id.tvIdIntro);
        readFromAss();
    }

    protected void readFromAss(){
        try {
            InputStream inpSt = getAssets().open("ch08asset.txt");
            int size = inpSt.available();
            byte[] buffer = new byte[size];
            inpSt.read(buffer);
            inpSt.close();
            String text = new String(buffer);
            tvIntro.setText(text);

        }catch (IOException e){
            Log.e("GetAssetTest",e.toString());
        }
    }
}