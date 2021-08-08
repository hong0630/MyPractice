package com.example.fraglisttest;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragManager;
    private FragmentTransaction fragTran;
    private static String msg;
    private static String[] NewTitle = {
            "News1：俄羅斯莫斯科阿基米德國際發明展競賽",
            "News2：創意點子大募集",
            "News3：高科技設備前瞻技術發展計畫",
            "News4：產業創新與經營學術研討會",
            "News5：人體研究法之衝擊與因應研討會"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragManager = getSupportFragmentManager();
        if (fragManager.findFragmentById(android.R.id.content)==null){
            MylistFragment list = new MylistFragment();
            fragTran = fragManager.beginTransaction();
            fragTran.add(android.R.id.content,list);
            fragTran.commit();
        }
    }
    public class MylistFragment extends ListFragment{
        @Override
        public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, NewTitle));
        }

        @Override
        public void onListItemClick(@NonNull @NotNull ListView l, @NonNull @NotNull View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            msg = "您的選擇的新聞標題：\n";
            msg+=((TextView)v).getText().toString();
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        }
    }
}