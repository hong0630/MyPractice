package com.example.fragshowhidetest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragManager;
    private FragmentTransaction fragTran;
    private Fragment frag1,frag2;
    private Button btfrag;
    private boolean isfrag1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        fragManager = getFragmentManager();
        fragTran = fragManager.beginTransaction();
        frag1 = new FirstFragment();
        fragTran.add(android.R.id.content,frag1);

        frag2 = new SecondFragment();
        fragTran.add(android.R.id.content,frag2);
        fragTran.show(frag1);
        fragTran.hide(frag2);
        fragTran.commit();

        btfrag = (Button)findViewById(R.id.btHideShow);
        btfrag.setOnClickListener(btListener);

    }

    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragTran = fragManager.beginTransaction();
            if (isfrag1 ==true){
                fragTran.hide(frag2);
                fragTran.show(frag1);
                fragTran.commit();

                btfrag.setText("Show第2個視窗區塊");
                isfrag1 = false;
            }else{
                fragTran.hide(frag1);
                fragTran.show(frag2);
                fragTran.commit();

                btfrag.setText("Show第1個視窗區塊");
                isfrag1 = true;
            }
        }
    };

    public static class FirstFragment extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.image1,container,false);
        }
    }

    public static class SecondFragment extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.image2,container,false);
        }
    }
}