package com.example.bindservicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class PlayMusicService extends Service {
    private MediaPlayer mp;
    private String Tag = "BindServiceTest";

    public PlayMusicService() {
    }

    @Override
    public void onCreate() {
        Log.e(Tag,"onCreat");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e(Tag,"onDestroy");
        super.onDestroy();
        mp.stop();
    }
    public class PlayBinder extends Binder{
        PlayMusicService getService(){
            Log.e(Tag,"getService");
            return PlayMusicService.this;
        }
    }

    private final IBinder playBinder = new PlayBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(Tag,"onBind");
        // TODO: Return the communication channel to the service.
        mp = MediaPlayer.create(this,R.raw.skycity);
        mp.start();
        return playBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(Tag,"onUnbind");
        return super.onUnbind(intent);
    }
}