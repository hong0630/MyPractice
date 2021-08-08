package com.example.callmsgservicetest;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyMsgService extends Service {
    private String Tag = "MyMsgService";
    static final int MSG_SET_VALUE = 1;
    static String st2 = null;

    public MyMsgService() {
    }

    @Override
    public void onDestroy() {
        Log.e(Tag, "onDestroy");
        ActMsger = null;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(Tag,"onBind");
        return ServMsger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(Tag, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(Tag,"onRebind");
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Log.e(Tag, "服務的handleMessage-->服務 接收到 活動 傳過來的訊息'11111'" +
                    "，也傳送信息'22222'給活動");
            switch (msg.what) {
                case MyMsgService.MSG_SET_VALUE:
                    try {
                        Bundle ActBundle = msg.getData();
                        String stName1 = ActBundle.getString("Name");
                        st2 = "服務先 接收到從 活動 傳過來的 訊息！\n 訊息內容為" +
                                stName1 + "\n arg1 = " + msg.arg1;
                        Message message = Message.obtain(null, MyMsgService.MSG_SET_VALUE);
                        ActMsger = msg.replyTo;

                        Bundle ServBundle = new Bundle();
                        String stName2 = "22222";
                        ServBundle.putString("Name", stName2);
                        ServBundle.setClassLoader(getClassLoader());
                        message.setData(ServBundle);
                        message.arg1 = 2;

                        ActMsger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };
    private Messenger ActMsger;
    private Messenger ServMsger = new Messenger(mHandler);



}