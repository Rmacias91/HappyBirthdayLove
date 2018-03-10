package com.example.richie.happybirthdaylove;

import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

    }

    @Override
    public void onBackPressed() {
        if(mPager.getCurrentItem()==0){
            super.onBackPressed();
        }else{
            mPager.setCurrentItem(mPager.getCurrentItem()-1);
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
