package com.example.yomd.framework.util;

import android.view.MotionEvent;
import android.view.View;

import com.example.yomd.game.state.State;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by yomd on 2016-11-07.
 */

public class InputHandler implements View.OnTouchListener {
    //vilket spelläge befinner vi oss i?
    private State currentState;

    //hämta aktuellt spelläge
    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    //metod som bestämmer var man tryckt på skärmen
    //i förhållande till spelets bredd och höjd
    public boolean onTouch(View v, MotionEvent event){
        //skala skärmens x- och y-värde till spelets
        //x- och y-värde
        int scaledX = (int)((event.getX()/v.getWidth())*
                GameMainActivity.GAME_WIDTH);
        int scaledY = (int)((event.getY()/v.getHeight())*
                GameMainActivity.GAME_HEIGHT);
        return currentState.onTouch(event,scaledX,scaledY);
    }
}
