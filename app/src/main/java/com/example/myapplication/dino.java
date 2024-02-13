package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

public class dino {
    Bitmap r1,r2,r3,r4,r5,r6,r7,r8,r1f,r2f,r3f,r4f,r5f,r6f,r7f,r8f;
    int x, y, width, height;
    public dino(Resources res,int swidth, int sheight){
        x = swidth;
        y = sheight;

        r1= BitmapFactory.decodeResource(res,R.drawable.run1);
        r2= BitmapFactory.decodeResource(res,R.drawable.run2);
        r3= BitmapFactory.decodeResource(res,R.drawable.run3);
        r4= BitmapFactory.decodeResource(res,R.drawable.run4);
        r5= BitmapFactory.decodeResource(res,R.drawable.run5);
        r6= BitmapFactory.decodeResource(res,R.drawable.run6);
        r7= BitmapFactory.decodeResource(res,R.drawable.run7);
        r8= BitmapFactory.decodeResource(res,R.drawable.run8);
        r1f= BitmapFactory.decodeResource(res,R.drawable.r1f);
        r2f= BitmapFactory.decodeResource(res,R.drawable.r2f);
        r3f= BitmapFactory.decodeResource(res,R.drawable.r3f);
        r4f= BitmapFactory.decodeResource(res,R.drawable.r4f);
        r5f= BitmapFactory.decodeResource(res,R.drawable.r5f);
        r6f= BitmapFactory.decodeResource(res,R.drawable.r6f);
        r7f= BitmapFactory.decodeResource(res,R.drawable.r7f);
        r8f= BitmapFactory.decodeResource(res,R.drawable.r8f);
        width = r1.getWidth()/4;
        height = r1.getHeight()/4;
        r1f = Bitmap.createScaledBitmap(r1f, (r1.getWidth()/4),(r1.getHeight()/4), false);
        r2f = Bitmap.createScaledBitmap(r2f, (r2.getWidth()/4),(r2.getHeight()/4), false);
        r3f = Bitmap.createScaledBitmap(r3f, (r3.getWidth()/4),(r3.getHeight()/4), false);
        r4f = Bitmap.createScaledBitmap(r4f,(r4.getWidth()/4),(r4.getHeight()/4), false);
        r5f = Bitmap.createScaledBitmap(r5f, (r5.getWidth()/4),(r5.getHeight()/4), false);
        r6f = Bitmap.createScaledBitmap(r6f,(r6.getWidth()/4),(r6.getHeight()/4), false);
        r7f = Bitmap.createScaledBitmap(r7f, (r7.getWidth()/4),(r7.getHeight()/4), false);
        r8f = Bitmap.createScaledBitmap(r8f, (r8.getWidth()/4),(r8.getHeight()/4), false);

        r1 = Bitmap.createScaledBitmap(r1,(r1.getWidth()/4),(r1.getHeight()/4),false);
        r2 = Bitmap.createScaledBitmap(r2,(r2.getWidth()/4),(r2.getHeight()/4),false);
        r3 = Bitmap.createScaledBitmap(r3,(r3.getWidth()/4),(r3.getHeight()/4),false);
        r4 = Bitmap.createScaledBitmap(r4,(r4.getWidth()/4),(r4.getHeight()/4),false);
        r5 = Bitmap.createScaledBitmap(r5,(r5.getWidth()/4),(r5.getHeight()/4),false);
        r6 = Bitmap.createScaledBitmap(r6,(r6.getWidth()/4),(r6.getHeight()/4),false);
        r7 = Bitmap.createScaledBitmap(r7,(r7.getWidth()/4),(r7.getHeight()/4),false);
        r8 = Bitmap.createScaledBitmap(r8,(r8.getWidth()/4),(r8.getHeight()/4),false);

    }

    public Bitmap getDino(int val){
        switch (val){
            case 1 : return r1;
            case 2 : return r2;
            case 3 : return r3;
            case 4 : return r4;
            case 5 : return r5;
            case 6 : return r6;
            case 7 : return r7;
            case 8 : return r8;
            case 9 : return r2f;
            case 10 : return r3f;
            case 11 : return r4f;
            case 12 : return r5f;
            case 13 : return r6f;
            case 14 : return r7f;
            case 15 : return r8f;
            default : return r1f;
        }
    }
    public Rect getRect(){
        Log.d("dino", "x: "+x+" y ="+y+" width ="+(x+width) + " height ="+(y+height));
        return new Rect(x,y,x+width-100,y+height-50);
    }
}
