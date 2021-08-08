package com.example.custnotify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

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

        btNtf.setOnClickListener(btNtListener);
        btCanNtf.setOnClickListener(btCanNtListener);
    }

    private View.OnClickListener btNtListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ShowCustNotify();
        }
    };

    private void ShowCustNotify() {
        int icon = R.drawable.icon1;
        Context context = getApplicationContext();
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(icon)
                .setTicker("軟體更新通知")
                .setAutoCancel(false);
        Notification ntf = builder.build();

        RemoteViews contentView = new RemoteViews(getPackageName(),R.layout.custnotify);
        contentView.setImageViewResource(R.id.image,icon);
        contentView.setTextViewText(R.id.title,"軟體更新通知");
        contentView.setTextViewText(R.id.Text,"已有新版軟體可下載");
        ntf.contentView = contentView;

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivities(this,0, new Intent[]{intent},0);
        ntf.contentIntent = pi;

        ntfMgr.notify(Ntf_ID,ntf);

    }

    private View.OnClickListener btCanNtListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ntfMgr.cancel(Ntf_ID);
        }
    };
}