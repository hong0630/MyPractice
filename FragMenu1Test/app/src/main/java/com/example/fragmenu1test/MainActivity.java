package com.example.fragmenu1test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragtManager;
    private FragmentTransaction fragTran;
    private Fragment frag1, frag2;
    static private SubMenu menu1, menu2;
    private CheckBox chBox1, chBox2;
    private static final int itPhoto = Menu.FIRST,
            itMusic = Menu.FIRST + 1,
            subitPhoto1 = Menu.FIRST + 4,
            subitPhoto2 = Menu.FIRST + 5,
            subitMusic1 = Menu.FIRST + 6,
            subitMusic2 = Menu.FIRST + 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }


    private void buildViews() {
        fragtManager = getFragmentManager();
        fragTran = fragtManager.beginTransaction();
        if (frag1 == null) {
            frag1 = new MenuFragment1();
            fragTran.add(frag1, "f1");
        }
        if (frag2 == null) {
            frag2 = new MenuFragment2();
            fragTran.add(frag2, "f2");
        }
        fragTran.commit();

        chBox1 = (CheckBox) findViewById(R.id.menu1);
        chBox2 = (CheckBox) findViewById(R.id.menu2);
        chBox1.setOnClickListener(chbListener);
        chBox2.setOnClickListener(chbListener);

        UpdFagVisibility();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        UpdFagVisibility();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg = "";
        switch (item.getItemId()){
            case itPhoto:
                msg = "您選擇 瀏覽照片 功能";
                break;
            case subitPhoto1:
                msg = "您選擇 瀏覽照片 功能 --> 瀏覽照片 flow1.png";
                break;
            case subitPhoto2:
                msg = "您選擇 瀏覽照片 功能 --> 瀏覽照片 flow2.png";
                break;

            case itMusic:
                msg = "您選擇 撥放音樂 功能";
                break;
            case subitMusic1:
                msg = "您選擇 撥放音樂 功能 --> 播放 天空之城.midi";
                break;
            case subitMusic2:
                msg = "您選擇 撥放音樂 功能 --> 播放 灌籃高手.midi";
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        return true;
    }

    private void UpdFagVisibility() {
        fragTran = fragtManager.beginTransaction();
        if (chBox1.isChecked()) fragTran.show(frag1);
        else fragTran.hide(frag1);

        if (chBox2.isChecked()) fragTran.show(frag2);
        else fragTran.hide(frag2);

        fragTran.commit();
    }

    private final View.OnClickListener chbListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UpdFagVisibility();
        }
    };

    public static class MenuFragment1 extends Fragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            menu1 = menu.addSubMenu(0, itPhoto, 0, "瀏覽照片")
                    .setIcon(R.drawable.frame);
            MenuItem Menuitem1 = menu1.getItem();
            Menuitem1.setShowAsAction(
                    MenuItem.SHOW_AS_ACTION_IF_ROOM |
                            MenuItem.SHOW_AS_ACTION_WITH_TEXT
            );
            menu1.add(0, subitPhoto1, 0, "照片 flow1.png");
            menu1.add(0, subitPhoto2, 1, "照片 flow2.png");

        }
    }

    public static class MenuFragment2 extends Fragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            menu2 = menu.addSubMenu(0, itMusic, 0, "播放音樂")
                    .setIcon(R.drawable.music);
            MenuItem Menuitem2 = menu2.getItem();
            Menuitem2.setShowAsAction(
                    MenuItem.SHOW_AS_ACTION_IF_ROOM |
                            MenuItem.SHOW_AS_ACTION_WITH_TEXT
            );
            menu2.add(0, subitMusic1, 0, "天空之城.midi");
            menu2.add(0, subitMusic2, 1, "灌籃高手.midi");
        }
    }
}