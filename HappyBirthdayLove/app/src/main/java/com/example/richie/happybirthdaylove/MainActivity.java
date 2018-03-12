package com.example.richie.happybirthdaylove;

import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TypedArray imageArray;
    private String[] captionArray;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageArray = getResources().obtainTypedArray(R.array.ourPictures);
        captionArray = getResources().getStringArray(R.array.captions);
        mPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        AssetFileDescriptor afd = null;
        try {
            afd = getAssets().openFd("rememberMe.mp3");
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            player.prepare();
            player.setLooping(true);
            player.start();


        } catch (IOException e) {
            e.printStackTrace();
        }


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
}
