package com.example.fragmenu2test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragtManager;
    private FragmentTransaction fragTran;
    static private SubMenu menu1, menu2;
    private static final int itPhoto = Menu.FIRST,
            itMusic = Menu.FIRST + 1,
            itStop = Menu.FIRST+3,
            subitPhoto1 = Menu.FIRST + 4,
            subitPhoto2 = Menu.FIRST + 5,
            subitMusic1 = Menu.FIRST + 6,
            subitMusic2 = Menu.FIRST + 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContMenuFragment contFrag = new ContMenuFragment();
        fragtManager = getFragmentManager();
        fragTran = fragtManager.beginTransaction();
        fragTran.add(android.R.id.content,contFrag);
        fragTran.commit();

    }

    public static class ContMenuFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.activity_main, container, false);
            registerForContextMenu(root.findViewById(R.id.long_press));
            return root;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu1 = menu.addSubMenu(0, itPhoto, 0, "????????????");
            menu1.add(0, subitPhoto1, 0, "?????? flow1.png");
            menu1.add(0, subitPhoto2, 0, "?????? flow2.png");

            menu2 = menu.addSubMenu(0, itMusic, 0, "????????????");
            menu2.add(0, subitMusic1, 0, "????????????.midi");
            menu2.add(0, subitMusic2, 0, "????????????.midi");

            menu.add(0, itStop, Menu.NONE, "??????");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case itPhoto:
                msg = "????????? ???????????? ??????";
                break;
            case subitPhoto1:
                msg = "????????? ???????????? ?????? --> ???????????? flow1.png";
                break;
            case subitPhoto2:
                msg = "????????? ???????????? ?????? --> ???????????? flow2.png";
                break;

            case itMusic:
                msg = "????????? ???????????? ??????";
                break;
            case subitMusic1:
                msg = "????????? ???????????? ?????? --> ?????? ????????????.midi";
                break;
            case subitMusic2:
                msg = "????????? ???????????? ?????? --> ?????? ????????????.midi";
                break;

            case itStop:
                msg = "????????? ?????? ?????? --> ?????????????????????";
                finish();
                break;
        }
        Log.d("MainActivity",msg);
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}