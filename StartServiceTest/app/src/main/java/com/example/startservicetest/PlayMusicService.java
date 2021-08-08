package com.example.startservicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class PlayMusicService extends Service {
    public static final int START_STICKY= 1;
    private MediaPlayer mp;
    private String Tag = "PlayMusicService";

    public PlayMusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Tag,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,0,startId);
        mp = MediaPlayer.create(this,R.raw.skycity);
        mp.start();
        Log.e(Tag,"onStartCommand");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        Log.e(Tag,"onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(Tag,"onBind");
        throw null;
    }
}