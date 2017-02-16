package com.example.yomd.game.model;

import com.example.yomd.framework.util.RandomNumberGenerator;

/**
 * Created by yomd on 2016-11-28.
 */

public class Cloud {

    //variabler för att hålla koll på var molnet är
    private float x,y;
    //konstant (final) som håller koll på hastigheten
    //på molnet
    private static final int VEL_X = -15;

    //Konstuktor för klassen som anropas med new
    //tar två inparametrar för x- och y-värde
    //för molnet
    public Cloud(float x, float y){
        this.x = x;
        this.y = y;
    }

    //metod som uppdaterar molnets position
    //tar en inparameter som talar om hur mycket
    //molnt ska röra sig
    public void update(float delta){
        //uppdatera x-position
        //+= betyder att vi lägger till usprungligt värde
        x += VEL_X * delta;
        //om x-värdet är mindre än -200
        //alltså vi är till vänster om spelplan
        //flytta 1000 till höger alltså blir
        //x-värde 800 för molnet
        if(x <= -200){
            x += 1000;
            //slumpa ett y-värde mellan
            //y=20 och y=100
            y = RandomNumberGenerator.getRandomIntBetween(20,100);
        }
    }

    //metoder för att hämta x- och y-värdena
    //för molnet
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
