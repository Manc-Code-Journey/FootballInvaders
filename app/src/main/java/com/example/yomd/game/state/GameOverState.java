package com.example.yomd.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by YOMD on 2017-01-23.
 */

public class GameOverState extends State {

    //variabel för text på skärm
    private String gameOverMessage = "GAME OVER";

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
        g.setColor(Color.rgb(255,145,0));
        g.fillRect(0,0, GameMainActivity.GAME_WIDTH,
                GameMainActivity.GAME_HEIGHT);
        //byter färg
        g.setColor(Color.DKGRAY);
        //Skriv ut lite text på skärmen
        //fet text med storlek 50
        g.setFont(Typeface.DEFAULT_BOLD, 50);
        g.drawString(gameOverMessage, 257, 175);
        g.drawString("Touch the screen", 220,350);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_UP){
            setCurrentState(new SelectLevelState());
        }
        return true;
    }
}
