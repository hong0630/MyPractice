package com.example.actbarmenu3test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp1 = null,mp2 = null;
    private ImageView ivPhoto;
    private boolean isPlay1 = false,isPlay2 = false;
    TextView searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        searchText = new TextView(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.app_search).getActionView();
        searchView.setOnQueryTextListener(ActViewListener);
        return true;
    }

    private SearchView.OnQueryTextListener ActViewListener =
            new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(MainActivity.this,"所要尋找的文字為："+query+
                    "...",Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            newText = newText.isEmpty()?"":"到目前輸入的文字為：" + newText;
            searchText.setTextColor(Color.RED);
            searchText.setTextSize(20);
            searchText.setText(newText);
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        ivPhoto = new ImageView(this);

        setContentView(ivPhoto);
        if (isPlay1){mp1.stop();isPlay1 = false;}
        if (isPlay2){mp2.stop();isPlay2 = false;}

        switch(item.getItemId()){
            case R.id.itPhoto:
                msg = "您選擇 瀏覽照片 功能";
                break;
            case R.id.subitPhoto1:
                msg = "您選擇 瀏覽照片 功能-->瀏覽照片 flow1.png";
                ivPhoto.setImageResource(R.drawable.flow1);
                setContentView(ivPhoto);
                break;
            case R.id.subitPhoto2:
                msg = "您選擇 瀏覽照片 功能-->瀏覽照片 flow2.png";
                ivPhoto.setImageResource(R.drawable.flow2);
                setContentView(ivPhoto);
                break;

            case R.id.itMusic:
                msg = "您選擇 撥放音樂 功能";
                if (isPlay1){mp1.stop();isPlay1 = false;}
                if (isPlay2){mp2.stop();isPlay2 = false;}
                break;
            case R.id.subitMusic1:
                msg = "您選擇 撥放音樂 功能 --> 播放 天空之城.midi";
                mp1 = MediaPlayer.create(this,R.raw.skycity);
                mp1.start();
                isPlay1 = true;
                break;
            case R.id.subitMusic2:
                msg = "您選擇 撥放音樂 功能 --> 播放 灌籃高手.midi";
                mp2 = MediaPlayer.create(this,R.raw.basket);
                mp2.start();
                isPlay2 = true;
                break;

            case R.id.itInfo:
                msg = "您選擇 關於 功能";
                break;
            case R.id.itStop:
                msg = "您選擇 結束 功能 --> 將結束所有功能";
                if (isPlay1){mp1.stop();isPlay1 = false;}
                if (isPlay2){mp2.stop();isPlay2 = false;}
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        return true;
    }

    private void ShowDial(){
        AlertDialog.Builder adBuild = new AlertDialog.Builder(MainActivity.this){};
        adBuild.setTitle("關於本程式");
        adBuild.setMessage("本程式示範 動作視窗+動作列功能表 之設計");
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