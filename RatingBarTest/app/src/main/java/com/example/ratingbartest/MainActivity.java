package com.example.ratingbartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RatingBar rbPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews(){
        rbPhoto = (RatingBar)findViewById(R.id.rbIdPhoto);
        rbPhoto.setOnRatingBarChangeListener(rbListener);
    }

    private RatingBar.OnRatingBarChangeListener rbListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            String msg = null;
            msg = "妳給的評分為：" + rating + " 分";
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        }
    };
}