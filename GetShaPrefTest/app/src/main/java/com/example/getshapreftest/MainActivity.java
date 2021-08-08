package com.example.getshapreftest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btSvPref;
    private Button btChgPref;
    private Button btRestPref;
    private EditText etItName;
    private int defSize = 20;
    private int defColor = Color.GREEN;
    private int defTf = Typeface.ITALIC;
    private String prefName = "prefSets";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bulidViews();
    }

    private void bulidViews() {
        etItName =(EditText) findViewById(R.id.etItName);
        btSvPref = (Button)findViewById(R.id.btIdSvPref);
        btChgPref = (Button)findViewById(R.id.btIdChgPref);
        btRestPref = (Button)findViewById(R.id.btIdResPref);
        etItName.setTextSize(defSize);
        etItName.setTextColor(defColor);
        etItName.setTypeface(Typeface.MONOSPACE,defTf);

        btSvPref.setOnClickListener(btSvPrefListener);
        btChgPref.setOnClickListener(btChgPrefListener);
        btRestPref.setOnClickListener(btRestPrefListener);

    }

    private View.OnClickListener btSvPrefListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefSets = getSharedPreferences(prefName,Context.MODE_PRIVATE);
            prefSets.edit()
                    .putInt("defSize",defSize)
                    .putInt("defColor",defColor)
                    .putInt("defTf",defTf).commit();
        }
    };

    private View.OnClickListener btChgPrefListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            etItName.setTextSize(30);
            etItName.setTextColor(Color.RED);
            etItName.setTypeface(Typeface.MONOSPACE,Typeface.BOLD);
        }
    };

    private View.OnClickListener btRestPrefListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefSet = getSharedPreferences(prefName, Context.MODE_PRIVATE);

            Integer defSize = prefSet.getInt("defSize",20);
            Integer defColor = prefSet.getInt("defColor",Color.GREEN);
            Integer defTf = prefSet.getInt("defTf",Typeface.ITALIC);

            etItName.setTextSize(defSize);
            etItName.setTextColor(defColor);
            etItName.setTypeface(Typeface.MONOSPACE,defTf);
        }
    };

}