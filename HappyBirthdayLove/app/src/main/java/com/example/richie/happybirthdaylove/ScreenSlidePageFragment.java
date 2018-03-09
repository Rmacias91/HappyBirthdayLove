package com.example.richie.happybirthdaylove;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Richie on 3/9/2018.
 */

public class ScreenSlidePageFragment extends Fragment{
    private ImageView imageContainer;
    private String imageName;


    public static ScreenSlidePageFragment newInstance(String imageName) {
        ScreenSlidePageFragment fragmentPage = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putString("imageName", imageName);
        fragmentPage.setArguments(args);
        return fragmentPage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageName = getArguments().getString("imageName", "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(
                R.layout.fragment_slider_view, container, false);
        imageContainer = view.findViewById(R.id.imageContainer);
        Picasso.with(getContext()).load(R.drawable.image1).into(imageContainer);
        Log.d("Fragment","imageName is: "+R.drawable.image1);
        return view;

    }
}
