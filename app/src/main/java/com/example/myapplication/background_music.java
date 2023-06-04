package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.Inet4Address;

public class background_music extends Service {

    MediaPlayer mediaPlayer;

    public background_music() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

    }

    public void start() {
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    public  int onStartCommand(Intent intent, int flags, int startId) {
        return  super.onStartCommand(intent, flags, startId);
    }
    public void pause() {
        mediaPlayer.pause();
    }
    @Override
    public  void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }


}