package com.example.expandablelistviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ExpandableListActivity {
    private ExpandableListAdapter adExpList;
    String msg;
    String[] stAryEdu = {
            "國小", "國中", "高中", "大學", "碩士", "博士"
    };
    String[] stAryEduDesc = {"", "", "分為普通高中及高職", "分為普通大學及科技大學", "", ""};
    String[][] stArySch = {{"建國中學", "建國中學", "內湖高中", "松山家商"}, {"台灣大學", "北一女高", "台灣科大", "德明科大"}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews() {
        List<Map<String, String>> stEduList = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> SchSet = new ArrayList<List<Map<String, String>>>();

        for (int i = 0; i < 6; i++) {
            Map<String, String> EduItem = new HashMap<String, String>();
            EduItem.put("ItName", stAryEdu[i]);
            EduItem.put("subItname", stAryEduDesc[i]);
            stEduList.add(EduItem);

            List<Map<String, String>> stSchList = new ArrayList<Map<String, String>>();
            if (i == 2 || i == 3) {
                for (int j = 0; j < 3; j++) {
                    Map<String, String> SchItem = new HashMap<String, String>();
                    SchItem.put("ItName", stArySch[i - 2][j]);
                    SchItem.put("subItname", "");
                    stSchList.add(SchItem);
                }
            }
            SchSet.add(stSchList);
        }

        adExpList = new SimpleExpandableListAdapter(
                this,
                stEduList,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{"ItName", "subItname"},
                new int[]{android.R.id.text1, android.R.id.text2},
                SchSet,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"ItName", "subItname"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        msg = "您的教育程度為：" + stAryEdu[groupPosition] + "\n就讀學校：" + stArySch[groupPosition-2][childPosition];
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }
}