package com.example.handlertest;

public class userThread extends Thread {
    MainActivity activity;
        int what = 1;

    public userThread(MainActivity activity) {
        this.activity = activity;
    }


    @Override
    public void run() {
        while (true) {

            activity.myHandler.sendEmptyMessage((what++)%4);
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
