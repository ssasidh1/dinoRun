package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.os.Handler;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class GameView  extends SurfaceView implements Runnable {
    private Thread gameThread;
    private Paint paint,outlinePaint;
    int y;
    int posx, posy;
    int dx, dy;
    int count;
    boulder[] b;
    Boolean forward = true;
    Random r = new Random();
    int height, width;
    private background bg1, bg2,bg3;
    private dino dinoSprite;
    private float screenRatiox, screenRatioy;
    private  int screenX, screenY;
    private int sWidth, sHeight;
    private boolean isPlaying;
    private int selectDino;

    private int isDead = 5;
    private Boolean running;
    private GameActivity activity;
    public GameView(GameActivity activity, int screenX, int screenY
            ,int sWidth,int sHeight) {
        super(activity);
        this.activity = activity;
        this.sWidth = sWidth;
        this.sHeight =sHeight;
        this.screenX = screenX;
        this.screenY = screenY;
        bg1 = new background(screenX,screenY,getResources());
        bg2 = new background(screenX,screenY,getResources());
        bg3 = new background(screenX,screenY,getResources());
        screenRatiox = 2220f/screenX;
        screenRatioy = 1080f/screenY;
        bg2.x = screenX;
        bg3.x = -screenX;
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.BLACK);
        dinoSprite = new dino(getResources(),screenX/4,screenY-300);
        selectDino = 1;
        running = false;
    }
    public void initializeAsteriods(){
        b = new boulder[10];
        posx = 1;
        posy = 1;
        dx = 2;
        dy = 4;
        count++;
        for (int i = 0; i < b.length; ++i) {
            Log.d("boulderCreation", "times: "+String.valueOf(count)+ "Screen "+sWidth);
            b[i] = new boulder();
            b[i].createAsteroids(getResources());
            int xMul = r.nextInt(5);
            b[i].x = 2210 +(xMul == 0 ? 1 : xMul*500);
            b[i].y = r.nextInt(300)*(xMul > 2 ? (-1*xMul) : xMul);
            Log.d("initial", "b: "+b[i].y);
//                b[i].dx = r.nextInt(30-15);
//                b[i].dy = r.nextInt(30-15);
            b[i].diameter = 20;
        }
    }
    @Override
    public void run() {
        initializeAsteriods();
        while(isPlaying && isDead != 0){
            update();
            draw();
            sleep();
        }
    }

    private void update(){
       if(running) {

//        Log.d("bg1width "+bg1.bg.getWidth() +" bg1.x " +bg1.x,"diffBG1 "+(bg1.x +bg1.bg.getWidth()) +" screenx "+screenX );
//        Log.d("bg2width "+bg2.bg.getWidth() +" bg2.x " +bg2.x,"diffBG1 "+(bg2.x +bg2.bg.getWidth())  );
            if ((bg1.x + bg1.bg.getWidth()) <= 0) {
                bg1.x = screenX;
            }
            if ((bg2.x + bg2.bg.getWidth()) <= 0) {
                bg2.x = screenX;
            }
           if ((bg3.x ) >= 0) {
               bg3.x = -screenX;
           }
           if(!forward){
               bg1.x += 10 * screenRatiox;
               bg2.x += 10 * screenRatiox;
               bg3.x += 10 * screenRatiox;

               dinoSprite.x -= dx;
           }
           if(forward){
               bg1.x -= 10 * screenRatiox;
               bg2.x -= 10 * screenRatiox;
               bg3.x -= 10 * screenRatiox;
               dinoSprite.x +=dx;
           }
           Log.d("bg3", "x: " +bg3.x +" "+bg2.x +" "+bg1.x);
        }

        for (int i = 0; i < b.length; ++i){
            posx=b[i].x;
            posy=b[i].y;

            posx -= 10 * screenRatiox;
            posy += dy;
            Log.d("final", "posx: "+posx+" posy ="+posy +" "+width +" "+height );

            if(posx < -10  || posy > height){
                int xMul = r.nextInt(5);
                posx = 2210+(xMul == 0 ? 1 : xMul*500);;
                posy = r.nextInt(300) * (xMul > 2 ? (-1*xMul) : xMul);
//                Log.d("positijon", "posx: "+posx+" posy ="+posy);
            }
            if(!forward){posx += 10 * screenRatiox;}
            b[i].x = posx;
            b[i].y= posy;

            if(Rect.intersects(b[i].getRect(),dinoSprite.getRect())){
                --isDead;
                int xMul = r.nextInt(5);
                posx = 2210+(xMul == 0 ? 1 : xMul*500);;
                posy = r.nextInt(300) * (xMul > 2 ? (-1*xMul) : xMul);
                b[i].x = posx;
                b[i].y= posy;

            }

            //b[i].update(posx,posy);
        }
    }
    private void sleep(){
        try {
            gameThread.sleep(40);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(bg1.bg,bg1.x,bg1.y,paint);
            canvas.drawBitmap(bg2.bg,bg2.x,bg2.y,paint);
            canvas.drawBitmap(bg3.bg,bg3.x,bg3.y,paint);
            width = canvas.getWidth();
            height = canvas.getHeight();
/*
            paint.setColor(Color.argb(255, 112, 105, 105));
*/
            if(isDead >= 0){
                canvas.drawText("Lives:"+isDead,screenX-500, 164,paint);
            }if(isDead == 0){
                Log.d("GamView end", "end ");

                canvas.drawText("END",screenX/2, screenY/2,paint);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            activity.finishActivity();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
            for (int i = 0; i < b.length; ++i) {
                canvas.drawBitmap(b[i].asteroid,b[i].x,b[i].y,paint);
            }
            if(running){
                if(forward){
                    if(selectDino > 8 ){selectDino = 2;}
                }
                else{
                    if(selectDino < 8 || selectDino > 15){selectDino = 10;}
                }
                //canvas.drawBitmap(dinoSprite.getDino(9),screenX/2,screenY-500,paint);
                canvas.drawBitmap(dinoSprite.getDino(selectDino),dinoSprite.x,screenY-300,paint);
                selectDino++;


            }
            else{
                canvas.drawBitmap(dinoSprite.r1,dinoSprite.x,screenY-300,paint);
            }

            getHolder().unlockCanvasAndPost(canvas);

        }
    }

    public void resume(){
        isPlaying = true;
        gameThread = new Thread(this);
        Log.d("g", "gameView: ");
        gameThread.start();
    }
    public void pause(){
        try {
            Log.d("pause", "paused: ");
            isPlaying = false;
            gameThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                running = true;
                if(event.getX() >= sWidth/2){
                    forward = true;
                }
                else{
                    forward = false;
                }
                break;
            case  MotionEvent.ACTION_UP:
                forward = true;
                running = false;
                break;
        }
        return  true;
    }


}
