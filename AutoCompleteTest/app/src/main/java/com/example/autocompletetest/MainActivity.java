package com.example.autocompletetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView acPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bulidViews();
    }

    private void bulidViews(){
        acPlace = (AutoCompleteTextView)findViewById(R.id.acIdPlace);
        String[] PlaceAry =
                getResources().getStringArray(R.array.PlaceArray);
        ArrayAdapter<String> adapterPlace =
            new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,PlaceAry);
        acPlace.setAdapter(adapterPlace);
    }
}