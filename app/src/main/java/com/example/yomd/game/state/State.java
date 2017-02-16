package com.example.yomd.game.state;

import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by yomd on 2016-11-07.
 * Klass som håller koll på de olika
 * lägena i spelet
 * Denna klass är en mall (abstract) för alla
 * andra state klasser. Alla meoder som är
 * abstract måste implemneteras av klasser som
 * ärver denna klass.
 */

public abstract class State {

    //metod som sätter nuvarande läge för spelet
    public void setCurrentState(State newState){
        GameMainActivity.sGame.setCurrentState(newState);
    }

    //några abstrakta metoder som måste implemnteras
    //om man använder denna klass som föräldraklass
    //initierar aktuellt state
    public abstract void init();
    //uppdaterar aktuellt state
    public abstract void update(float delta);
    //ritar ut aktyuellt state
    public abstract void render(Painter g);
    //känner om någon tryckt på skärmen
    public abstract boolean onTouch(MotionEvent e,
                                    int scaledX,
                                    int scaledY);
}
