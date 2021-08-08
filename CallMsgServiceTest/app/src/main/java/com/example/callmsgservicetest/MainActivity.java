package com.example.callmsgservicetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String Tag = "CallMsgServiceTest";
    private boolean isBound;
    private TextView tvActRecMsg, tvServRecMsg;
    private Button btSendMsg, btEnd;
    private Messenger ActMsger;     //活動(客戶)端的信息傳遞者
    private Messenger ServMsger;    //服務端的信息傳遞者

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        tvActRecMsg = (TextView) findViewById(R.id.tvIdActRecMsg);
        tvServRecMsg = (TextView) findViewById(R.id.tvIdServRecMsg);
        btSendMsg = (Button) findViewById(R.id.btIdSendMsg);
        btEnd = (Button) findViewById(R.id.btIdEnd);
        btEnd.setOnClickListener(btEndListener);
        btSendMsg.setOnClickListener(btSendMsgListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, MyMsgService.class), serviceConn,
                Context.BIND_AUTO_CREATE);
        Log.e(Tag, "onStart -> bindService");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            Log.e(Tag, "unbindService");
            unbindService(serviceConn);
            isBound = false;
        }
    }

    private ServiceConnection serviceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(Tag, "onServiceConnected");
            ServMsger = new Messenger(service);
            ActMsger = new Messenger(mHandler);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(Tag, "onServiceDisconnected");
            ServMsger = null;
        }
    };

    private View.OnClickListener btEndListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(Tag,"btEndListener --> finish");
            finish();
        }
    };

    private View.OnClickListener btSendMsgListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isBound) return;
            Log.e(Tag, "btSendMsgListener：開始 活動<-->服務 互傳訊息！");
            Message message = Message.obtain(null, MyMsgService.MSG_SET_VALUE);

            message.replyTo = ActMsger;

            Bundle ActBundLe = new Bundle();
            String stNane1 = "11111";
            ActBundLe.putString("Name", stNane1);
            ActBundLe.setClassLoader(getClassLoader());
            message.setData(ActBundLe);
            message.arg1 = 1;

            try {
                ServMsger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MyMsgService.MSG_SET_VALUE:
                    String st = MyMsgService.st2;
                    tvServRecMsg.setTextColor(Color.RED);
                    tvServRecMsg.setText(st);

                    Log.e(Tag, "活動的handleMessage-->" +
                            "活動 接收到 服務 傳過來的訊息'22222'");

                    Bundle ServBundle = msg.getData();
                    String stName2 = ServBundle.getString("Name");
                    tvActRecMsg.setTextColor(Color.BLUE);
                    st = "接著 活動 也收到從 服務 傳過來的 訊息！\n" + "訊息內容為" +
                            stName2 + "\n arg1=" + msg.arg1;
                    tvActRecMsg.setText(st);
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };

}