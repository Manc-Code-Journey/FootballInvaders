package com.example.yomd.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.simpleandroidgdf_monday.Assets;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by YOMD on 2017-01-23.
 */

public class GameOverState extends State {

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        //välj och rita ut en rektangel
        //som fyller hela spelskärmen
        g.drawImage(Assets.gameover, 0, 0);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_UP){
            setCurrentState(new SelectLevelState());
        }
        return true;
    }
}
