package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class boulder {
    int x, y, dx, dy, diameter;
    int width, height;
    Bitmap asteroid;
    public void update(int posx, int posy)
    {
        x = posx;
        y = posy;

//        if (x < 0) dx = -dx;
//        if (y < 0) dy = -dy;
//        if (x > width) dx = -dx;
//        if (y > height) dy = -dy;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(x, y, diameter, paint);
    }
    public void createAsteroids(Resources res){
        Log.d("aster ","dim "+width+" "+height  );
        asteroid = BitmapFactory.decodeResource(res,R.drawable.fasteriod);
        width = (asteroid.getWidth()/6);
        height = (asteroid.getHeight()/6);
        asteroid = Bitmap.createScaledBitmap(asteroid,width,height,false);
    }

    public Rect getRect(){
        Log.d("boulder", "x: "+x+" y ="+y+" width ="+(x+width-50) + " height ="+(y+height-50));
        return new Rect(x,y,x+width-50,y+height-50);
    }

}
