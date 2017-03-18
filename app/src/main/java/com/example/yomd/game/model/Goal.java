package com.example.yomd.game.model;

import android.graphics.Rect;

import com.example.yomd.framework.util.RandomNumberGenerator;
import com.example.yomd.game.state.SelectLevelState;

/**
 * Created by tc980615 on 2017-02-16.
 */
public class Goal {

    //variabler för x- och y-position
    private float x, y;
    private int width, height;
    private Rect rect;

    //konstruktorn till klassen
    //tar fyra inparametrar x- och y-position
    //bredd och höjd på bollen
    public Goal(float x, float y, int width,
                int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //Bollens rektangel
        rect = new Rect();
    }

    //metod för att uppdatera bollens rektangel
    public void updateRects() {
        rect.set((int) x + 10, (int) y, (int) x + (width - 20),
                (int) y + height);
    }
    //metoder för att hämta spelarens x- och
    //y-position
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    //metoder som returnerar bredd
    // och höjd för spelaren
    public int getWidth(){
        return width;
    }
    public int getHeight() {
        return height;
    }


}
