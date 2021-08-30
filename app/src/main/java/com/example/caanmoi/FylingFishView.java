package com.example.caanmoi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FylingFishView extends View {
    //
    private Bitmap fish[] = new Bitmap[2];
    //
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;
    //
    private int cavasWidth,cavaHeight;
    //
    private int yellowX,yellowY, yellowSpeend = 16;
    private Paint yellowPaint = new Paint();
    //
    private int greenX,greenY, greenSpeend = 20;
    private Paint greenPaint = new Paint();
    //
    private int redX,redY, redSpeend = 25;
    private Paint redPaint = new Paint();
    //
    private  int score,lifeCounterOfFish;
    //
    private boolean touch = false;

    private Bitmap backgroundImage;

    private Paint scorePaint = new Paint();
    //bien mang
    private Bitmap life[] = new Bitmap[2];

    public FylingFishView(Context context) {

        super(context);
        //Goi doi tuong bitmap
        //ve ca
        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.ish);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        //background
        backgroundImage=BitmapFactory.decodeResource(getResources(),R.drawable.background);
        //tao bong an
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);
        //
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);
        //
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);
        //ve diem
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(30);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        //mang mang
        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        //
        fishY = 550;
        score = 0;
        lifeCounterOfFish =3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
        cavasWidth = canvas.getWidth();
        cavaHeight = canvas.getHeight();
        canvas.drawBitmap(backgroundImage,0,0,null);
        //canvas.drawBitmap(fish,0,0,null);

        //
        int minFishY = fish[0].getHeight();
        int maxFishY = cavaHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;
        //
        if (fishY < minFishY){
            fishY = minFishY;
        }

        if (fishY > maxFishY){
            fishY = maxFishY;
        }
        //
        //fishSpeed =fishSpeed + 2;
        fishSpeed +=2;
        if (touch){
            //
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch = false;
        }else {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }
        //
       // yellowX = yellowX - yellowSpeend;
        yellowX -=yellowSpeend;
        //
        if (hitBallChecker(yellowX,yellowY)){
            score += 10;
            //score = score +10;
            //yellowX = yellowX - 100;
            yellowX = -100;
        }
        //
        if (yellowX < 0){
            yellowX = cavasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) +minFishY;
        }
        //
        canvas.drawCircle(yellowX,yellowY,25,yellowPaint);
        //
        greenX -=greenSpeend;
        //
        if (hitBallChecker(greenX,greenY)){
            score += 20;
            greenX = -100;
        }
        //
        if (greenX < 0){
            greenX = cavasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) +minFishY;
        }
        //
        canvas.drawCircle(greenX,greenY,25,greenPaint);
        //
        redX -=redSpeend;
        //
        if (hitBallChecker(redX,redY)){
            redX = -100;
            lifeCounterOfFish--;
            if (lifeCounterOfFish == 0){
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();
                //
                Intent mhgameOver =new Intent(getContext(),GameOverActivity.class);
                mhgameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mhgameOver.putExtra("Score",score);
                getContext().startActivity(mhgameOver);
            }
        }
        //
        if (redX < 0){
            redX = cavasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) +minFishY;
        }
        //
        canvas.drawCircle(redX,redY,30,redPaint);
        //
        canvas.drawText("Diem: "+score,10,50, scorePaint);
        //
        for (int i=0;i<3;i++){
            int x = (int) (180 + life[0].getWidth() *1.5 *i);
            int y = 20;
            if (i < lifeCounterOfFish){
                canvas.drawBitmap(life[0],x,y,null);
            }else {
                canvas.drawBitmap(life[1],x,y,null);
            }
        }
        /*canvas.drawBitmap(life[0],180,4,null);
        canvas.drawBitmap(life[0],280,4,null);
        canvas.drawBitmap(life[0],380,4,null);*/
    }
    //
    public boolean hitBallChecker(int x,int y){
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight()))
        {
            return  true;
        }

        return  false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //
            touch = true;
            //
            fishSpeed = -22;
        }
        return true;
    }
}
