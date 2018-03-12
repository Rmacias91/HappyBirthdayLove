package com.example.richie.happybirthdaylove;


import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Rmaci on 3/12/2018.
 */

public class MusicService extends Service {
    public static final String PARAM_SONG="song";
    MediaPlayer player;

    private final IBinder mBinder = new LocalBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AssetFileDescriptor afd = null;
        try {
            afd = getAssets().openFd("cant_take_my_eyes.mp3");
            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            player.prepare();
            player.setLooping(true);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }
    public void startMusic() {

        player.start();
    }

    public void pauseMusic() {
        player.pause();
    }

    public class LocalBinder extends Binder {
        MusicService getService(){
            // Return this instance of LocalService so clients can call public methods
            return MusicService.this;
        }
    }

}
