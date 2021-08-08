package com.example.browserectest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String DBname = "先進公司.db";
    private static final int DBversion = 1;
    private EditText etCusNo,etCusNa,etCusPho,etCusAdd;
    private TextView tvTitle;
    private Button btNext,btPrev;
    private CompDBHper dbHper;
    private ArrayList<String> recSet;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        tvTitle = findViewById(R.id.tvIdTitle);
        etCusNo = findViewById(R.id.etIdCusNo);
        etCusNa = findViewById(R.id.etIdCusNa);
        etCusPho = findViewById(R.id.etIdCusPho);
        etCusAdd = findViewById(R.id.etIdCusAdd);
        btNext = findViewById(R.id.btIdNext);
        btPrev = findViewById(R.id.btIdPrev);

        btPrev.setOnClickListener(btPrevListener);
        btNext.setOnClickListener(btNextListener);

        initDB();
        showRec(index);
    }

    private void showRec(int index) {
        if (recSet.size()!=0){
            String stHead = "顯示客戶資料:第"+(index+1)+"筆/共" +
                    recSet.size()+"筆";
            tvTitle.setTextColor(Color.RED);
            tvTitle.setText(stHead);
            Toast.makeText(MainActivity.this,recSet.get(index).toString(),
                    Toast.LENGTH_SHORT).show();
            String[] fId = recSet.get(index).split("#");
            etCusNo.setText(fId[0]);
            etCusNa.setText(fId[1]);
            etCusPho.setText(fId[2]);
            etCusAdd.setText(fId[3]);
        }else{
            etCusNo.setText("");
            etCusNa.setText("");
            etCusPho.setText("");
            etCusAdd.setText("");
        }
    }

    private void initDB() {
        if (dbHper ==null){
            dbHper = new CompDBHper(this,DBname,null,DBversion);
        }
        dbHper.creatTB();
        recSet = dbHper.getRecSet();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dbHper == null)
            dbHper = new CompDBHper(this,DBname,null,DBversion);
        recSet = dbHper.getRecSet();
        showRec(index);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (dbHper!=null) {
            dbHper.close();
            dbHper = null;
        }
        recSet.clear();
    }

    private View.OnClickListener btPrevListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            index--;
            if (index<0) index =recSet.size() -1 ;
            showRec(index);
        }
    };

    private View.OnClickListener btNextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            index++;
            if (index>=recSet.size())
                index = 0;
            showRec(index);
        }
    };
}