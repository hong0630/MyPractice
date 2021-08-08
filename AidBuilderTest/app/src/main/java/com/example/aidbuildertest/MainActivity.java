package com.example.aidbuildertest;

import androidx.appcompat.app.AlertDialog;
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
        btExitEd = (Button)findViewById(R.id.btIdExitEd);
        btExitEd.setOnClickListener(btListener);
    }

    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(MainActivity.this){};
            adBuilder.setIcon(R.drawable.save);
            adBuilder.setTitle("結束編輯程式");
            adBuilder.setMessage("是否儲存編輯內容?");
            adBuilder.setCancelable(false);
            adBuilder.setPositiveButton("是",adBtListener);
            adBuilder.setNegativeButton("否",adBtListener);
            adBuilder.setNeutralButton("取消",adBtListener);
            adBuilder.show();
        }
    };

    private DialogInterface.OnClickListener adBtListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String st = new String();
            switch (which){
                case BUTTON_POSITIVE:
                    st = "您按了'是'鈕，將會儲存檔案並結束編輯";
                    MainActivity.this.finish();
                    break;
                case BUTTON_NEGATIVE:
                    st = "您按了'否'鈕，將不會儲存檔案並結束編輯";
                    MainActivity.this.finish();
                    break;
                case BUTTON_NEUTRAL:
                    st = "您按了'取消'鈕，將取消結束編輯回到編輯模式";
                    break;
            }
            Toast.makeText(MainActivity.this,st,Toast.LENGTH_SHORT).show();
        }
    };
}