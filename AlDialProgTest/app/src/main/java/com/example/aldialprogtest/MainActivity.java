package com.example.aldialprogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    protected static final int BUTTON_POSITIVE = -1;
    protected static final int BUTTON_NEGATIVE = -2;
    protected static final int BUTTON_NEUTRAL = -3;
    private Button btExited;
    private Handler mHandler = new Handler();
    private ProgressDialog pgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        btExited = (Button) findViewById(R.id.btIdExitEd);
        btExited.setOnClickListener(btListener);
    }

    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder adBuild = new AlertDialog.Builder(MainActivity.this);
            adBuild.setTitle(R.string.aIdTitle);
            adBuild.setMessage(R.string.aIdPormpt);
            adBuild.setIcon(R.drawable.save);
            adBuild.setCancelable(false);
            adBuild.setPositiveButton(R.string.btPtPosit, aIdBtListener);
            adBuild.setNegativeButton(R.string.btPtNeg, aIdBtListener);
            adBuild.setNeutralButton(R.string.btPtNeut, aIdBtListener);
            adBuild.show();
        }
    };

    private DialogInterface.OnClickListener aIdBtListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String st = new String();
            switch (which) {
                case BUTTON_POSITIVE:
                    st = "您按了'是'鈕，將會儲存檔案並結束編輯！";
                    pgDialog = new ProgressDialog(MainActivity.this);
                    pgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                    pgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pgDialog.setMessage("正在儲存檔案中，請稍後...");
                    pgDialog.setCancelable(false);
                    pgDialog.setMax(100);
                    pgDialog.show();
                    shoeProgress();
                    break;
                case BUTTON_NEGATIVE:
                    st = "您按了'否'鈕，將不會儲存檔案並結束編輯！";
                    MainActivity.this.finish();
                    break;
                case BUTTON_NEUTRAL:
                    st = "您按了'取消'鈕，將取消結束編輯並回到編輯模式！";
                    break;
            }
            Toast.makeText(MainActivity.this, st, Toast.LENGTH_SHORT).show();
        }
    };

    private void shoeProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Calendar begin = Calendar.getInstance();
                do {
                    Calendar now = Calendar.getInstance();
                    final int DiffSec = 60 * (now.get(Calendar.MINUTE) -
                            begin.get(Calendar.MINUTE) +
                            now.get(Calendar.SECOND) -
                            begin.get(Calendar.SECOND));
                    if (DiffSec * 2 > 100) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                pgDialog.setProgress(100);
                            }
                        });
                        break;
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pgDialog.setProgress(DiffSec * 2);
                        }
                    });

                } while (true);

                pgDialog.cancel();
                MainActivity.this.finish();
            }
        }).start();
    }
}