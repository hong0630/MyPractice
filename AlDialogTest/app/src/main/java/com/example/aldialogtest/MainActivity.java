package com.example.aldialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected static final int BUTTON_POSITIVE = -1;
    protected static final int BUTTON_NEGATIVE = -2;
    protected static final int BUTTON_NEUTRAL = -3;
    private Button btExitEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews() {
        btExitEd = (Button) findViewById(R.id.btIdExitEd);
        btExitEd.setOnClickListener(btListener);
    }

    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CharSequence csPosit, csNeg, csNeut, cdMess;
            csPosit = "是";
            csNeg = "否";
            csNeut = "取消";
            cdMess = "是否儲存編輯內容";

            MyAlertDialog aldDial = new MyAlertDialog(MainActivity.this);
            aldDial.setTitle("結束編輯程式");
            aldDial.setIcon(R.drawable.save);
            aldDial.setCancelable(false);
            aldDial.setButton(DialogInterface.BUTTON_POSITIVE, csPosit, aldBtListener);
            aldDial.setButton(DialogInterface.BUTTON_NEGATIVE, csNeg, aldBtListener);
            aldDial.setButton(DialogInterface.BUTTON_NEUTRAL, csNeut, aldBtListener);
            aldDial.show();
        }
    };

    private DialogInterface.OnClickListener aldBtListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String st = new String();
            switch (which) {
                case BUTTON_POSITIVE:
                    st = "您按了'是'鈕，將會儲存檔案並結束編輯！";
                    MainActivity.this.finish();
                    break;
                case BUTTON_NEGATIVE:
                    st = "您按了'否'鈕，將不會儲存檔案並結束編輯！";
                    MainActivity.this.finish();
                    break;
                case BUTTON_NEUTRAL:
                    st = "您按了'取消'鈕，將取消結束編輯並回到編輯模式！";
                    break;
            }
            Toast.makeText(MainActivity.this,st,Toast.LENGTH_SHORT).show();
        }
    };
}