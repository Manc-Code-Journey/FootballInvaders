package com.example.yomd.game.state;

import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.simpleandroidgdf_monday.Assets;

/**
 * Created by yomd on 2016-11-14.
 */

public class LoadState extends State {
    @Override
    public void init() {
        //ladda in alla bilder och ljud
        //Använd load metoden i klassen Assets
        Assets.load();
    }

    @Override
    public void update(float delta) {
        //När vi laddat allt så ska vi byta state
        //till MenuState()
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Painter g) {

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
