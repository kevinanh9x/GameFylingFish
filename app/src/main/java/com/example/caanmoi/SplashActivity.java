package com.example.caanmoi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //an tunba
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                }catch (Exception ex){
                    ex.getMessage();
                    ex.printStackTrace();
                    Toast.makeText(SplashActivity.this,"Loi Error"+ex,Toast.LENGTH_SHORT).show();
                }
                finally {
                    Intent mainIntent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                }
            }
        };
        thread.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
