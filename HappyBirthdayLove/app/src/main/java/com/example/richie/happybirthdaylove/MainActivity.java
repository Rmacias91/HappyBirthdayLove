package com.example.richie.happybirthdaylove;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TypedArray imageArray;
    private String[] captionArray;
    private MusicService musicService;
    private  boolean mBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageArray = getResources().obtainTypedArray(R.array.ourPictures);
        captionArray = getResources().getStringArray(R.array.captions);
        mPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        Toast.makeText(this,"====> Swipe for more :)",Toast.LENGTH_LONG).show();
        Intent musicIntent = new Intent(this,MusicService.class);
        bindService(musicIntent, mConnection, Context.BIND_AUTO_CREATE);


    }

    @Override
    public void onBackPressed() {
        if(mPager.getCurrentItem()==0){
            super.onBackPressed();
        }
    }

    private class ScreenSlidePageAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePageAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            int imageId = imageArray.getResourceId(position,-1);
            String caption = captionArray[position];
            return ScreenSlidePageFragment.newInstance(imageId,caption);
        }

        @Override
        public int getCount() {
            return imageArray.length();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBound) {
            musicService.pauseMusic();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
        mBound = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mBound){
            musicService.startMusic();
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            musicService = binder.getService();
            mBound = true;
            musicService.startMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


}


