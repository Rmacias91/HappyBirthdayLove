package com.example.richie.happybirthdaylove;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Richie on 3/9/2018.
 */

public class ScreenSlidePageFragment extends Fragment{
    private ImageView imageContainer;
    private int imageId;
    private String caption;
    private TextView captionTV;


    public static ScreenSlidePageFragment newInstance(int imageId, String caption) {
        ScreenSlidePageFragment fragmentPage = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("imageId", imageId);
        args.putString("caption",caption);
        fragmentPage.setArguments(args);
        return fragmentPage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageId = getArguments().getInt("imageId", 0);
        caption = getArguments().getString("caption","");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_slider_view, container, false);
        imageContainer = view.findViewById(R.id.imageContainer);
        captionTV = view.findViewById(R.id.caption);
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        in.setDuration(5000);
        captionTV.setText(caption);
        captionTV.startAnimation(in);
        Picasso.with(getContext()).load(imageId).transform(new ImageRoundCorners()).into(imageContainer);

        if(imageId==R.drawable.image11){
            final ImageView imageView = view.findViewById(R.id.tinderMatch);
            imageView.setVisibility(View.VISIBLE);
            imageView.setAnimation(in);
            Toast.makeText(getContext(),R.string.message,Toast.LENGTH_SHORT).show();

            new CountDownTimer(5000, 1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
                    imageView.setVisibility(View.INVISIBLE);
                    imageView.setAnimation(out);
                }
            }.start();

        }

        return view;

    }

}
