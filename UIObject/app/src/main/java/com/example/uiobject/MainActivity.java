package com.example.uiobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btOk;
    private EditText etName;
    private RadioGroup rdgSex;
    private Spinner spnEdu;
    static String stEdu,stE;
    String stSex,st = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews(){
        etName = (EditText)findViewById(R.id.etIdName);
        rdgSex = (RadioGroup)findViewById(R.id.rdgIdSex);
        btOk = (Button)findViewById(R.id.btIdOK);



        rdgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if(i ==R.id.rbIdMale)stSex = "男性";
//                else stSex = "女性";
                int intChkRb = rdgSex.getCheckedRadioButtonId();
                switch (intChkRb){
                    case R.id.rbIdMale:
                        stSex = "男性";
                        break;
                    case R.id.rbIdFemale:
                        stSex = "女性";
                        break;
                }
            }
        });
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence csName = etName.getText();
                if(stSex.equals("男性")){
                    st = csName.toString()+"先生，你好";
                }else {
                    st = csName.toString()+"小姐，妳好";
                }

                Toast.makeText(MainActivity.this,st,Toast.LENGTH_SHORT).show();

            }
        });

        spnEdu = (Spinner)findViewById(R.id.spnIdEdu);
        ArrayAdapter<CharSequence> adEduList = ArrayAdapter.createFromResource(
                this,R.array.spnEduList,R.layout.support_simple_spinner_dropdown_item);
        adEduList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnEdu.setAdapter(adEduList);
        spnEdu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                stEdu = adapterView.getSelectedItem().toString();
                stE = "教育程度為" + stEdu;
                Toast.makeText(MainActivity.this,stE,Toast.LENGTH_SHORT).show();
            }
        });
    }

}