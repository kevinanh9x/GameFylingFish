package com.example.caanmoi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private Button btn_startGame,btn_Thoat;
    private TextView txt_DisplayScore;
    private String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //an tunba
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        AnhXa();
        score = getIntent().getExtras().get("Score").toString();
        btn_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent=new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });
        txt_DisplayScore.setText("Diem = " + score);
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        btn_startGame =(Button)findViewById(R.id.btnPlay);
        txt_DisplayScore=(TextView)findViewById(R.id.txt_diem);
        btn_Thoat=(Button)findViewById(R.id.btnEx);
    }
}
