package com.example.cusaldialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btLogin;
    private Dialog CusDialog;
    String st = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        btLogin = (Button)findViewById(R.id.btIdPtlogin);
        btLogin.setOnClickListener(btListener);
    }

    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CusDialog = new Dialog(MainActivity.this);
            CusDialog.setTitle("登入畫面");
            CusDialog.setCancelable(false);
            CusDialog.setContentView(R.layout.custdial);
            Button btOK = (Button)CusDialog.findViewById(R.id.btIdOk);
            Button btCancel = (Button)CusDialog.findViewById(R.id.btIdCancel);
            btOK.setOnClickListener(btOkListener);
            btCancel.setOnClickListener(btCancelListener);
            CusDialog.show();
        }
    };
    private View.OnClickListener btOkListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText etUser = (EditText)CusDialog.findViewById(R.id.etIdUserNa);
            EditText etPass = (EditText)CusDialog.findViewById(R.id.tvIdPass);
            st = "你輸入的使用者名稱：" + etUser.getText().toString() +
                    "\n" +"使用者密碼："+ etPass.getText().toString();
            Toast.makeText(MainActivity.this,st,Toast.LENGTH_SHORT).show();
            CusDialog.cancel();

        }
    };

    private View.OnClickListener btCancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            st = "你按了<取消>鈕。";
            Toast.makeText(MainActivity.this,st,Toast.LENGTH_SHORT).show();
            CusDialog.cancel();
        }
    };
}