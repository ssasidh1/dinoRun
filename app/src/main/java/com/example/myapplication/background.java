package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class background implements Runnable {
    int x=0, y=0;
    Bitmap bg;
    Thread bgThread;
    private Resources resources;
    private int screenX;
    private int screenY;
    background(int screenx, int screeny, Resources res){
        resources = res;
        screenX = screenx;
        screenY = screeny;
        bgThread = new Thread();
        bgThread.start();
    }

    @Override
    public void run() {
        bg = BitmapFactory.decodeResource(resources,R.drawable.desert);
        bg = Bitmap.createScaledBitmap(bg,screenX,screenY,false);
    }
}
