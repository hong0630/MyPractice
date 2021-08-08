package com.example.imgview_seekbar_ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btNext,btPrev;
    private ImageView ivFlow;
    private TextView tvCurrPhoNo;
    private SeekBar sbAlp;
    int currPhotoId = 0;
    private Integer[] AryPhoto = {
            R.drawable.flow01,R.drawable.flow02,R.drawable.flow03,R.drawable.flow04,
            R.drawable.flow05,R.drawable.flow06};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews(){
        btNext=(Button)findViewById(R.id.btIdNext);
        btPrev=(Button)findViewById(R.id.btIdPrev);
        ivFlow = (ImageView)findViewById(R.id.ivIdFlow);
        tvCurrPhoNo = (TextView)findViewById(R.id.tvIdCurrPhoNo);
        sbAlp = (SeekBar)findViewById(R.id.sbIdAlp);
        tvCurrPhoNo.setText("目前檢視的照片編號:1"+"/共"+(AryPhoto.length)+"張");

        btNext.setOnClickListener(btListener);
        btPrev.setOnClickListener(btListener);
        sbAlp.setOnSeekBarChangeListener(sbListener);
    }

    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button v1 = (Button)v;
            if (v1.getText().equals("下一張")){
                currPhotoId = (currPhotoId+1)% AryPhoto.length;
                ivFlow.setImageResource(AryPhoto[currPhotoId]);
            }else {
                currPhotoId = (currPhotoId-1+AryPhoto.length)% AryPhoto.length;
                ivFlow.setImageResource(AryPhoto[currPhotoId]);
            }

            tvCurrPhoNo.setText("目前檢視的照片編號："+(currPhotoId+1)+"/共"+(AryPhoto.length)+"張");

        }
    };

    private SeekBar.OnSeekBarChangeListener sbListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            ivFlow.setImageAlpha(255*progress/100);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}