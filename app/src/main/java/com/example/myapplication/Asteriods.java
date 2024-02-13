package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.res.Resources;
public class Asteriods {
    float x, y, dx, dy, diameter;
    float width, height;

    public void update()
    {
        x += dx;
        y += dy;
        if (x < 0) dx = -dx;
        if (y < 0) dy = -dy;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(x, y, diameter, paint);
    }

/*
    public void createAsteroids(int width, int height){
        bg = BitmapFactory.decodeResource(res,R.drawable.bg2);
        bg = Bitmap.createScaledBitmap(bg,screenx,screeny,false);
    }
*/
}
