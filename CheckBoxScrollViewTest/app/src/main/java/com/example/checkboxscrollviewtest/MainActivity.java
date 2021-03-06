package com.example.checkboxscrollviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox chbSalSup,chbMangSup,chbPersSup,chbAdmSup,
            chbSalPer,chbSalEng,chbOpMang,chbAdmPer,chbSenSecr,chbSalAss;
    private Button btOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildViews();
    }

    private void buildViews(){
        chbSalSup=(CheckBox)findViewById(R.id.chbIdSalSup);
        chbMangSup=(CheckBox)findViewById(R.id.chbIdMangSup);
        chbPersSup=(CheckBox)findViewById(R.id.chbIdPersSup);
        chbAdmSup=(CheckBox)findViewById(R.id.chbIdAdmSup);
        chbSalPer=(CheckBox)findViewById(R.id.chbIdSalPer);
        chbSalEng=(CheckBox)findViewById(R.id.chbIdSalEng);
        chbOpMang=(CheckBox)findViewById(R.id.chbIdOpMang);
        chbAdmPer=(CheckBox)findViewById(R.id.chbIdAdmPer);
        chbSenSecr=(CheckBox)findViewById(R.id.chbIdSenSecr);
        chbSalAss=(CheckBox)findViewById(R.id.chbIdSalAss);
        btOk = (Button)findViewById(R.id.btOk);

        btOk.setOnClickListener(btListener);

    }

    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = "您的工作經歷為:\n";

            if (chbSalSup.isChecked())
                msg += chbSalSup.getText().toString()+"\n";

            if (chbMangSup.isChecked())
                msg += chbMangSup.getText().toString()+"\n";

            if (chbPersSup.isChecked())
                msg += chbPersSup.getText().toString()+"\n";

            if (chbAdmSup.isChecked())
                msg += chbAdmSup.getText().toString()+"\n";

            if (chbSalEng.isChecked())
                msg += chbSalEng.getText().toString()+"\n";

            if (chbOpMang.isChecked())
                msg += chbOpMang.getText().toString()+"\n";

            if (chbAdmPer.isChecked())
                msg += chbAdmPer.getText().toString()+"\n";

            if (chbSalPer.isChecked())
                msg += chbSalPer.getText().toString()+"\n";

            if (chbSenSecr.isChecked())
                msg += chbSenSecr.getText().toString()+"\n";

            if (chbSalAss.isChecked())
                msg += chbSalAss.getText().toString()+"\n";

            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        }
    };
}