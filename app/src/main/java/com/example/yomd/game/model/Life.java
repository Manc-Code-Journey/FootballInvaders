package com.example.yomd.game.model;

import android.graphics.Rect;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.simpleandroidgdf_monday.Assets;

/**
 * Created by Ann-Marie on 2017-03-18.
 */

public class Life {

    private static final int LIFE_WIDTH = 50;
    private static final int LIFE_HEIGHT = 51;
    private int lives = 3;

    //variabler för x- och y-position
    private float x, y;

    //konstruktorn till klassen
    //tar fyra inparametrar x- och y-position
    //bredd och höjd på bollen
    public Life (float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void render(Painter g){
        for (int i = 0; i < lives - 1; i++) {
            g.drawImage(Assets.football, (int) x + (LIFE_WIDTH + 15) * i,
                    (int) y, LIFE_WIDTH, LIFE_HEIGHT);
        }
    }

    public void decreaseLife () {
        lives = lives - 1;
    }

    public boolean isAlive () {
        if (lives > 0) {
            return true;
        } else {
            return false;
        }
    }

    //metoder för att hämta spelarens x- och
    //y-position
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }




}
