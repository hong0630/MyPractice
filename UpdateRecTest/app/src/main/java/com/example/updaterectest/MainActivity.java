package com.example.updaterectest;

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
    private EditText etCusNa,etCusPho,etCusAdd;
    private TextView tvTitle,tvCusNo;
    private Button btNext,btPrev,btEdit,btDel;
    private CompDBHper dbHper;
    private ArrayList<String> recSet;
    private int index = 0;
    String msg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHper!= null){
            dbHper.close();
            dbHper = null;
        }
        recSet.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHper ==null)
            dbHper = new CompDBHper(this,DBname,null,DBversion);
        recSet = dbHper.getRecSet();
        showRec(index);
    }

    private void buildViews() {
        tvTitle = findViewById(R.id.tvIdTitle);
        tvCusNo = findViewById(R.id.tvIdCusNo);
        etCusNa = findViewById(R.id.etIdCusNa);
        etCusPho = findViewById(R.id.etIdCusPho);
        etCusAdd = findViewById(R.id.etIdCusAdd);
        btNext = findViewById(R.id.btIdNext);
        btPrev = findViewById(R.id.btIdPrev);
        btEdit = findViewById(R.id.btIdEdit);
        btDel = findViewById(R.id.btIdDel);

        btNext.setOnClickListener(btNextListener);
        btPrev.setOnClickListener(btPrevListener);
        btEdit.setOnClickListener(btEditListener);
        btDel.setOnClickListener(btDelListener);

        initDB();
        showRec(index);
    }

    private void showRec(int index) {
        if (recSet.size()!=0){
            String stHead = "顯示客戶資料：第 "+(index+1)+" 筆/共 "+ recSet.size() +" 筆";
            tvTitle.setTextColor(Color.BLUE);
            tvTitle.setText(stHead);
            String[] fId = recSet.get(index).split("#");
            tvCusNo.setTextColor(Color.RED);
            tvCusNo.setText(fId[0]);
            etCusNa.setText(fId[1]);
            etCusPho.setText(fId[2]);
            etCusAdd.setText(fId[3]);
        }else {
            tvCusNo.setText("");
            etCusNa.setText("");
            etCusPho.setText("");
            etCusAdd.setText("");
        }
    }

    private void initDB() {
        if (dbHper == null)
            dbHper = new CompDBHper(this,DBname,null,DBversion);
        dbHper.creatTB();
        recSet = dbHper.getRecSet();
    }

    private View.OnClickListener btNextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            index++;
            if (index>=recSet.size())index =0;
            showRec(index);
        }
    };
    private View.OnClickListener btPrevListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            index--;
            if (index<0) index =recSet.size()-1;
            showRec(index);
        }
    };

    private View.OnClickListener btEditListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String CusNo = tvCusNo.getText().toString().trim();
            String CusNa = etCusNa.getText().toString().trim();
            String CusPho = etCusPho.getText().toString().trim();
            String CusAdd = etCusAdd.getText().toString().trim();
            int rowsAffected = dbHper.updateRec(CusNo,CusNa,CusPho,CusAdd);
            if (rowsAffected==-1){
                msg = "資料表以空，無法修改！";
            }else if (rowsAffected==0){
                msg = "找不到欲修改的紀錄，無法修改！";
            }else {
                msg = "第 "+(index+1)+" 筆紀錄 已修改！\n" +
                        "共 "+ rowsAffected +" 筆紀錄 被修改！";
                recSet = dbHper.getRecSet();
                showRec(index);
            }
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener btDelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String CusNo = tvCusNo.getText().toString().trim();
            int rowsAffected = dbHper.deleteRec(CusNo);
            if (rowsAffected == -1){
                msg = "資料表已刪除，無法修改！";
            }else if (rowsAffected ==0){
                msg = "找不到欲刪除的紀錄，無法修改！";
            }else {
                msg = "第 "+(index+1)+" 筆紀錄 已刪除！\n" +
                        "共 "+ rowsAffected +" 筆紀錄 被刪除！";
                recSet = dbHper.getRecSet();
                showRec(0);
            }
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        }
    };
}