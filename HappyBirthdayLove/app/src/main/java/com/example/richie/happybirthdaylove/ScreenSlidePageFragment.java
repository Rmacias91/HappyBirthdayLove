package com.example.richie.happybirthdaylove;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        captionTV.setText(caption);
        Picasso.with(getContext()).load(imageId).into(imageContainer);
        return view;

    }
}
