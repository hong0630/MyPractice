package com.example.aldialodlisttest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etColor1;
    private EditText etColor2;
    private EditText etHobby;
    private AlertDialog.Builder adBuild;
    private CharSequence[] Clitems = {"紅色","綠色","藍色"};
    private CharSequence[] HbItems = {"打球","健行","爬山"};
    private String st = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        etColor1 = (EditText)findViewById(R.id.etIdColor1);
        etColor2 = (EditText)findViewById(R.id.etIdColor2);
        etHobby = (EditText)findViewById(R.id.etIdHobby);

        etColor1.setOnKeyListener(kyListener);
        etColor2.setOnKeyListener(kyListener);
        etHobby.setOnKeyListener(kyListener);
    }
    private View.OnKeyListener kyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                EditText et = (EditText) v;
                Integer id = et.getId();
                adBuild = new AlertDialog.Builder(MainActivity.this){};
                if (id == R.id.etIdColor1){
                    adBuild.setTitle("請選擇一個顏色");
                    adBuild.setItems(Clitems,aIdLtListener);
                }
                else if (id == R.id.etIdColor2){
                    adBuild.setTitle("請選擇一個顏色");
                    adBuild.setSingleChoiceItems(Clitems,-1,aIdRgListener);
                    adBuild.setPositiveButton("確定",aIdBtListener1);
                }
                else {
                    adBuild.setTitle("請選擇興趣(可複選)");
                    adBuild.setMultiChoiceItems(HbItems,
                            new boolean[]{false,false,false},
                            aIdCgListener);
                    adBuild.setPositiveButton("確定",aIdBtListener2);
                }
                AlertDialog alert = adBuild.create();
                alert.show();
                return true;
            }else {
                return false;
            }
        }
    };

    private DialogInterface.OnClickListener aIdLtListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            etColor1.setText(Clitems[which]);
        }
    };

    private DialogInterface.OnClickListener aIdRgListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            etColor2.setText(Clitems[which]);
        }
    };

    private DialogInterface.OnMultiChoiceClickListener aIdCgListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            if (isChecked){
                st+=HbItems[which]+"/";
            }
        }
    };

    private DialogInterface.OnClickListener aIdBtListener1 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    private DialogInterface.OnClickListener aIdBtListener2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            etHobby.setText(st);
        }
    };
}