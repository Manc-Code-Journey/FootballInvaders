package com.example.yomd.game.model;

import android.graphics.Rect;

/**
 * Created by tc980615 on 2017-02-16.
 */
public class Goal {

    //variabler för x- och y-position
    private float x, y;
    private int width, height;
    private Rect rect;
    private Ball ball;

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
        updateRects();
    }

    //metod för att uppdatera bollens rektangel
    public void updateRects() {
        rect.set((int) x, (int) y, (int) x + (width),
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
    public int getHeight(){
        return height;
    }

    public boolean onCollied (Ball ball) {
        return rect.contains(ball.getRect());
    }
}
