package com.example.spinnertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Spinner spnEdu;
    private Spinner spnEdu2;
    private Button btOk;
    static String stEdu,st;
    static String stEdu2,st2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildView();

        buildViews();
    }
//第一種Spinner建構方式
    private void buildView(){
        spnEdu = (Spinner)findViewById(R.id.spnIdEdu);
        ArrayAdapter<CharSequence> adEduList = ArrayAdapter.createFromResource(
                this,R.array.spnEduList,R.layout.support_simple_spinner_dropdown_item);
        adEduList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnEdu.setOnItemSelectedListener(spnEduListener);
    }

    private AdapterView.OnItemSelectedListener spnEduListener =
            new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            stEdu= parent.getSelectedItem().toString();
            st = "教育程度為" +stEdu;
            Toast.makeText(MainActivity.this,st,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
//第二種Spinner建構方式
    private void buildViews(){
        spnEdu2 = (Spinner)findViewById(R.id.spnIdEdu2);

        String[] stAryEdu = {"國小","國中","高中","大學","碩士","博士"};
        ArrayAdapter<String> adEduList = new ArrayAdapter<>(
                this,R.layout.support_simple_spinner_dropdown_item,stAryEdu);

        adEduList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnEdu2.setAdapter(adEduList);
        spnEdu2.setOnItemSelectedListener(spnEduListener2);

        btOk = (Button)findViewById(R.id.btPtOk);
        btOk.setOnClickListener(btListener);
    }

    private AdapterView.OnItemSelectedListener spnEduListener2 =
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    stEdu2= parent.getSelectedItem().toString();
                    st2 = "教育程度為" +stEdu;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            };
    private View.OnClickListener btListener = v -> {
        Toast.makeText(MainActivity.this, st2, Toast.LENGTH_SHORT).show();
    };

}