package com.example.yomd.game.model;

import android.graphics.Rect;

import com.example.yomd.framework.util.RandomNumberGenerator;

/**
 * Created by tc980615 on 2017-02-16.
 */
public class Goal {

    //variabler för x- och y-position
    private float x, y;
    private int width, height;
    private Rect rect;
    private Ball ball;
    private static final int Y = 100;
    private boolean visible;

    //konstruktorn till klassen
    //tar fyra inparametrar x- och y-position
    //bredd och höjd på bollen
    public Goal(float x, float y, int width,
                int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //målets rektangel
        rect = new Rect();
        updateRects();
        visible = false;
    }
    public void update(float delta, float velX){
        x += velX * delta;
        if (x <= -50){
            reset();
        }
    }
    public void reset(){
    visible = true;
        if (RandomNumberGenerator.getRandInt(2) == 0) {
            y = Y;
        }
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
