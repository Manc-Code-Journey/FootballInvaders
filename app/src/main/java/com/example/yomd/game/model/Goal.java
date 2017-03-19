package com.example.yomd.game.model;

import android.graphics.Rect;

import com.example.yomd.framework.util.RandomNumberGenerator;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

import static com.example.yomd.simpleandroidgdf_monday.GameMainActivity.GAME_HEIGHT;
import static com.example.yomd.simpleandroidgdf_monday.GameMainActivity.GAME_WIDTH;
import static com.example.yomd.simpleandroidgdf_monday.GameMainActivity.currentLevel;

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
    private float velX1 = 100;
    private float velX2 = 180;

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

    }
    //får målet att röra sig i spelplan
    public void update(float delta){
        if (currentLevel == 2) {
            x += velX1 * delta;
            if ( x < 0) {
                velX1 = -velX1;
            }
            if (x > GAME_WIDTH - width) {
                velX1 = -velX1;
            }
        }
        if (currentLevel == 3) {
            x += velX2 * delta;
            if ( x < 0) {
                velX2 = -velX2;
            }
            if (x > GAME_WIDTH - width) {
                velX2 = -velX2;
            }
        }
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
