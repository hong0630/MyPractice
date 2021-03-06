package com.example.menu2test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int VISIBLE = 0;
    private static final int INVISIBLE = 4;
    private MediaPlayer mp1 = null,mp2 = null;
    private ImageView ivPhoto;
    private boolean isPlaying1 = false,isPlaying2 = false;
    private static final int itPhoto = Menu.FIRST,
    itMusic = Menu.FIRST+1,
    itInfo = Menu.FIRST+2,
    itStop = Menu.FIRST+3,
    subitPhoto1 = Menu.FIRST+4,
    subitPhoto2 = Menu.FIRST+5,
    subitMusic1 = Menu.FIRST+6,
    subitMusic2 = Menu.FIRST+7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu menu1 = menu.addSubMenu(0,itPhoto,0,"瀏覽照片").setIcon(R.drawable.frame);
        menu1.add(0,subitPhoto1,0,"照片flow1.png");
        menu1.add(0,subitPhoto2,1,"照片flow2.png");

        SubMenu menu2 = menu.addSubMenu(0,itMusic,0,"撥放音樂").setIcon(R.drawable.music);
        menu2.add(0,subitMusic1,0,"天空之城.midi");
        menu2.add(0,subitMusic2,1,"灌籃高手.midi");

        menu.add(0,itInfo,Menu.NONE,"關於")
                .setIcon(R.drawable.info)
                .setCheckable(true);
        menu.add(0,itInfo,Menu.NONE,"結束-可按Alt+4")
                .setIcon(R.drawable.stop)
                .setShortcut('4','4');

        ivPhoto = (ImageView)findViewById(R.id.ivIdPhoto);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        ivPhoto.setVisibility(View.INVISIBLE);
        if (isPlaying1){mp1.stop();isPlaying1 = false;}
        if (isPlaying2){mp2.stop();isPlaying2 = false;}

        switch ( item.getItemId()){
            case itPhoto:
                msg = "您選擇 瀏覽照片 功能";
                break;
            case subitPhoto1:
                msg = "您選擇 瀏覽照片 功能-->瀏覽照片 flow1.png";
                ivPhoto.setVisibility(View.VISIBLE);
                ivPhoto.setImageResource(R.drawable.flow1);
                break;
            case subitPhoto2:
                msg = "您選擇 瀏覽照片 功能-->瀏覽照片 flow2.png";
                ivPhoto.setVisibility(View.VISIBLE);
                ivPhoto.setImageResource(R.drawable.flow2);
                break;
            case itMusic:
                msg = "您選擇 撥放音樂 功能";
                if (isPlaying1){mp1.stop();isPlaying1 = false;}
                if (isPlaying2){mp2.stop();isPlaying2 = false;}
                break;
            case subitMusic1:
                msg = "您選擇 撥放音樂 功能-->播放 天空之城.midi";
                mp1 = MediaPlayer.create(this,R.raw.skycity);
                mp1.start();
                isPlaying1 = true;
                break;
            case subitMusic2:
                msg = "您選擇 撥放音樂 功能-->播放 灌籃高手.midi";
                mp2 = MediaPlayer.create(this,R.raw.basket);
                mp2.start();
                isPlaying2 = true;
                break;
            case itInfo:
                msg = "您選擇 關於 功能";
                ShowDial();
                break;
            case itStop:
                msg = "您選擇 結束 功能-->將結束所有作業";
                if (isPlaying1){mp1.stop();isPlaying1 = false;}
                if (isPlaying2){mp2.stop();isPlaying2 = false;}
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        return true;
    }

    private void ShowDial() {
        AlertDialog.Builder adBuild = new AlertDialog.Builder(MainActivity.this){};
        adBuild.setTitle("關於本程式");
        adBuild.setMessage("本程式示範 功能表 之設計");
        adBuild.setCancelable(false);
        adBuild.setPositiveButton("確定",aIdBtListener);
        adBuild.show();
    }

    private DialogInterface.OnClickListener aIdBtListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    };

}