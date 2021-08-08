package com.example.fragdialogtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btExitEd;
    String st = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildViews();
    }

    private void buildViews() {
        btExitEd = (Button)findViewById(R.id.btIdExitEd);
        btExitEd.setOnClickListener(btListener);
    }

    private View.OnClickListener btListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            android.app.DialogFragment newFrag = MyDialFragment.newInstance(
                    "示範對話方塊的視窗區塊");
            newFrag.show(getFragmentManager(),"dialog");
        }
    };

    public static class MyDialFragment extends DialogFragment {
        public static MyDialFragment newInstance(String title){
            MyDialFragment frag = new MyDialFragment();
            Bundle args = new Bundle();
            args.putString("title",title);
            frag.setArguments(args);
            return frag;
        }

        @NonNull
        @org.jetbrains.annotations.NotNull
        @Override
        public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            String title = getArguments().getString("title");

            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.save)
                    .setTitle(title)
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainActivity)getActivity()).doPositiveClick();
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainActivity)getActivity()).doNegativeClick();
                        }
                    })
                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainActivity)getActivity()).doNeutralClick();
                        }
                    })
                    .create();
        }
    }
    public void doPositiveClick(){
        st = "您按了'是'鈕，將會儲存檔案並結束編輯";
        this.finish();
        Toast.makeText(this,st,Toast.LENGTH_SHORT).show();
    }
    public void doNegativeClick(){
        st = "您按了'否'鈕，將不會儲存檔案並結束編輯";
        this.finish();
        Toast.makeText(this,st,Toast.LENGTH_SHORT).show();
    }
    public void doNeutralClick(){
        st = "您按了'取消'鈕，將取消結束編輯回到編輯模式";
        Toast.makeText(this,st,Toast.LENGTH_SHORT).show();
    }
}