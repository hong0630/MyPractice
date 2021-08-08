package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView lvEdu;
    static String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bulidViews();
    }

    private void bulidViews(){
        lvEdu = (ListView)findViewById(R.id.lvIdView);
        ArrayAdapter<CharSequence> adEduList = ArrayAdapter.createFromResource(this,
                R.array.spnEduList,R.layout.support_simple_spinner_dropdown_item);

        lvEdu.setAdapter(adEduList);
        lvEdu.setOnItemClickListener(lvListener);
    }

    private AdapterView.OnItemClickListener lvListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            msg = "您的教育程度為：\n";
            msg += ((TextView)view).getText().toString();
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        }
    };
}