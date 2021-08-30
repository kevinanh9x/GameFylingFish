package com.example.caanmoi;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //
    private FylingFishView gameView;
    //
    private  final static long Interval = 30;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //an tunba
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //
        gameView=new FylingFishView(this);
        setContentView(gameView);
        //
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //
                        gameView.invalidate();
                    }
                });
            }
        },0,Interval);
    }
}
