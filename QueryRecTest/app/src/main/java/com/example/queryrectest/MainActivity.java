package com.example.queryrectest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String DBname = "先進公司.db";
    private static final int DBversion = 1;
    private EditText etCusNo;
    private Button btOk;
    private CompDBHper dbHper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        etCusNo = findViewById(R.id.etIdCusNo);
        btOk = findViewById(R.id.btIdOk);
        btOk.setOnClickListener(btOKListener);

        initDB();
    }

    private void initDB() {
        if (dbHper==null)
                dbHper = new CompDBHper(this,DBname,null,DBversion);
        dbHper.creatTB();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dbHper ==null)
            dbHper = new CompDBHper(this,DBname,null,DBversion);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHper != null){
            dbHper.close();
            dbHper = null;
        }
    }

    private View.OnClickListener btOKListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String result = null;
            String CusNo = etCusNo.getText().toString().trim();
            if (CusNo.length()!= 0){
                String rec = dbHper.FindRec(CusNo);
                if (rec!= null){
                    result = "找到的客戶資料為：\n"+rec;
                }else
                    result = "找不到指定的 客戶編號："+CusNo;
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
            }
        }
    };
}