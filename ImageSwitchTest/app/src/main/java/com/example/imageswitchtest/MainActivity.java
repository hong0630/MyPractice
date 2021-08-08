package com.example.imageswitchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {
    private LinearLayout mGallery;
    private ImageSwitcher imageSwi;

    private Integer[] AryThumPhoto = {
            R.drawable.flow01,R.drawable.flow02,R.drawable.flow03,R.drawable.flow04,
            R.drawable.flow05,R.drawable.flow06
    };
    private Integer[] AryBigPhoto = {
            R.drawable.flow01,R.drawable.flow02,R.drawable.flow03,R.drawable.flow04,
            R.drawable.flow05,R.drawable.flow06
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGallery = (LinearLayout)findViewById(R.id.phGallery);
        for (int i = 0;i<AryThumPhoto.length;i++){
            mGallery.addView(getImageView(i));

            imageSwi = (ImageSwitcher)findViewById(R.id.isIdPhoto);
            imageSwi.setFactory(this);
            imageSwi.setInAnimation(AnimationUtils.loadAnimation(this,
                    android.R.anim.fade_in));
            imageSwi.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));

        }
    }

    private ImageView getImageView(int i){
        ImageView img = new ImageView(this);
        img.setImageResource(AryThumPhoto[i]);
        img.setId(i);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwi.setImageResource(AryBigPhoto[v.getId()]);
                Toast.makeText(v.getContext(),"您選擇了第 "+(v.getId())+ " 張照片",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return img;
    }

    @Override
    public View makeView() {
        ImageView view = new ImageView(this);
        view.setBackgroundColor(0xFFFF00);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        return view;
    }
}