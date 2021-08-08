package com.example.horizontalscrollviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mGallery;
    private Integer[] AryThumPhoto={
            R.drawable.flow01,R.drawable.flow02,R.drawable.flow03,
            R.drawable.flow04,R.drawable.flow05,R.drawable.flow06};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews(){
        mGallery = (LinearLayout)findViewById(R.id.phGallery);
        for (int i = 0;i<AryThumPhoto.length;i++){
            mGallery.addView(getImageView(i));
        }
    }

    private ImageView getImageView(int i){
        ImageView img = new ImageView(this);
        img.setImageResource(AryThumPhoto[i]);
        img.setId(i);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"您選擇了第" + (v.getId()) +" 張照片",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return img;
    }
}