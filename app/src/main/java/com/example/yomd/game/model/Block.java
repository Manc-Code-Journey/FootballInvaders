package com.example.yomd.game.model;

import android.graphics.Rect;

import com.example.yomd.framework.util.RandomNumberGenerator;

/**
 * Created by yomd on 2016-11-28.
 */

public class Block {
    //Variabler för x- och y-värde
    //samt bredd och höjd på block
    //och rektangel som gör att vi kan
    //rita upp blocken på vår Canvas
    private float x, y;
    private int width, height;
    private Rect rect;
    //hålla koll om blocket är synligt eller inte
    private boolean visible;

    //Två konstanter somger max och min y-värden
    //för blockens position
    private static final int UPPER_Y = 275;
    private static final int LOWER_Y = 355;

    //konstruktorn för klassen
    //fyra inparametrar x- och y-position samt
    //bredd och höjd på blocket
    public Block(float x, float y, int width,
                 int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //skapa rektangeln för detta block
        rect = new Rect((int) x, (int) y,
                (int) x + width, (int) y + height);
        //blocket är osynligt som default
        visible = false;
    }

    //metod som uppdaterar blockets position
    //tar två inparametrar delta för hur lång tid
    //velX för själva hastigheten för blocket
    public void update(float delta, float velX) {
        //uppdatera x
        x += velX * delta;
        //om x är mindre än -50
        //anropa reset metoden
        updateRect();
        if (x <= -50) {
            reset();
        }
    }

    //metod för att uppdatera rektangeln
    public void updateRect() {
        rect.set((int) x, (int) y, (int) x + width,
                (int) y + height);
    }

    //metod för att uppdatera blocket om
    //blocket hamnat till vänster om spelplan
    public void reset() {
        //sätt blocket synligt
        visible = true;
        //1/3 chans att bli ett Upper_Block
        //alla Upper_Block måste spelaren ducka
        //för de andra blocken hoppar spelaren
        //över
        if (RandomNumberGenerator.getRandInt(3) == 0) {
            y = UPPER_Y;
        } else {
            y = LOWER_Y;
        }
        //flytta blocket till höger om spelplan
        x += 1000;
        updateRect();
    }

    //metod för att reagera på kollision
    //mellan block och något annant
    //Tar en spelare som inparameter
    public void onCollide(Player p) {
        //gör blocket osynligt
        visible = false;
        //tryck spelaren 30 pixlar bakåt
        p.pushBack(30);
    }

    //metoder för att hämta x- och y- för blocket
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    //metod för att kolla om blocket
    //är synligt eller inte
    public boolean isVisible() {
        return visible;
    }
    //metod som ger rektangeln för blocket
    public  Rect getRect(){
        return rect;
    }
}
