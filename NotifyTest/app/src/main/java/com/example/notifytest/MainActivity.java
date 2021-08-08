package com.example.notifytest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final static int Ntf_ID = 0;
    private Button btNtf,btCanNtf;
    private NotificationManager ntfMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews() {
        ntfMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        btNtf = (Button)findViewById(R.id.btIdNtf);
        btCanNtf = (Button)findViewById(R.id.btIdCanNtf);

        btNtf.setOnClickListener(btNtfListener);
        btCanNtf.setOnClickListener(btCanNtfListener);
    }

    private View.OnClickListener btNtfListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int icon = R.drawable.icon1;
            Context context = getApplicationContext();
            Notification.Builder builder = new Notification.Builder(context);
            Intent intent = new Intent(context,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

            long[] vibratetepattern = {100,400,500,400};
            builder.setSmallIcon(icon)
                    .setContentTitle("軟體通知更新")
                    .setContentText("已有新版軟體")
                    .setContentInfo("訊息")
                    .setTicker("軟體更新通知")
                    .setLights(0xFFFFFFFF,1000,1000)
                    .setVibrate(vibratetepattern)
                    .setAutoCancel(false);

            Notification notification = builder.build();
            ntfMgr.notify(icon,notification);
        }
    };

    private View.OnClickListener btCanNtfListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ntfMgr.cancel(Ntf_ID);
        }
    };
}