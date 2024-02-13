package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {
    private  GameView gameView;
    int ScreenW,ScreenH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        ScreenW = displaymetrics.widthPixels;
        ScreenH = displaymetrics.heightPixels;
        gameView = new GameView(this,point.x, point.y,ScreenW,ScreenH);

        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        Log.d("onr", "onResume: ");
        super.onResume();
        gameView.resume();
    }
    public void finishActivity(){
        Log.d("end", "end ");
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
 }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
//            // Handle right arrow key press
//            return true; // Indicate that the event has been handled
//        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
//            // Handle left arrow key press
//            return true; // Indicate that the event has been handled
//        }
//        return super.onKeyDown(keyCode, event); // Call super implementation for other key events
//    }
}
