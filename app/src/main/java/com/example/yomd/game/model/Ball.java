package com.example.yomd.game.model;

import android.graphics.Rect;



/**
 * Created by tc980615 on 2017-02-23.
 */
public class Ball {

    //variabler för x- och y-position
    private float x, y;
    private int width, height;
    private Rect rect;
    private float velX=0;
    private float velY=0;


    //konstruktorn till klassen
    //tar fyra inparametrar x- och y-position
    //bredd och höjd på bollen
    public Ball(float x, float y, int width,
                int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //Bollens rektangel
        rect = new Rect();
    }

    public void update(float delta){
        x = x + velX * delta;
        y = y + velY * delta;
        updateRects();
    }
    //metod för att uppdatera bollens rektangel
    private void updateRects() {
        rect.set((int) x + 10, (int) y, (int) x + (width - 20),
                (int) y + height);

    }

    public void setPosition (float x, float y) {
        this.x = x;
        this.y = y;
    }

    //metoder för att hämta spelarens x- och
    //y-position
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getVelY(){ return velY; }
    public float getVelX(){ return velX; }
    //metoder som returnerar bredd
    // och höjd för spelaren
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public Rect getRect () {
        return rect;
    }

    public void setVelocities(float velX, float velY){
        this.velX = velX;
        this.velY = velY;
    }


}


