package com.example.insertrectest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String DBname = "先進公司.db";
    private static final int DBversion = 1;
    private static final String TBname = "客戶";
    private EditText etCusNo,etCusNa,etCusPho,etCusAdd;
    private Button btIns, btCancel;
    private CompDBHper dbHper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHper!=null){
            dbHper.close();
            dbHper = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHper == null){
            dbHper = new CompDBHper(this,DBname,null,DBversion);
        }
    }

    private void buildViews() {
        etCusNo = (EditText)findViewById(R.id.etIdCusNo);
        etCusNa = (EditText)findViewById(R.id.etIdCusNa);
        etCusPho = (EditText)findViewById(R.id.etIdCusPho);
        etCusAdd = (EditText)findViewById(R.id.etIdCusAdd);
        btIns = (Button)findViewById(R.id.btIdIns);
        btCancel = (Button)findViewById(R.id.btIdCancel);

        btIns.setOnClickListener(btInsListener);
        btCancel.setOnClickListener(btCancelListener);
    }

    private View.OnClickListener btInsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String CusNo = etCusNo.getText().toString().trim();
            String CusNa = etCusNa.getText().toString().trim();
            String CusPho = etCusPho.getText().toString().trim();
            String CusAdd = etCusAdd.getText().toString().trim();
            if (CusNo.equals("")||CusNa.equals("")){
                Toast.makeText(MainActivity.this,"請輸入欲新增的客戶資料！",Toast.LENGTH_SHORT).show();
                return;
            }

            String msg = null;
            long rowID = dbHper.insertRec(CusNo,CusNa,CusPho,CusAdd);
            if (rowID !=1){
                msg = "新增紀錄 成功！\n" +
                        "目前客戶資料表共有"+dbHper.RecCount()+"筆記錄！";
            }else {
                msg = "新增紀錄 失敗！";
            }
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener btCancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            etCusNo.setText("");
            etCusNa.setText("");
            etCusPho.setText("");
            etCusAdd.setText("");
        }
    };
}