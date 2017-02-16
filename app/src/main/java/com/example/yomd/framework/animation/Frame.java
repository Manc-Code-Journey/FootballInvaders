package com.example.yomd.framework.animation;

import android.graphics.Bitmap;

/**
 * Created by yomd on 2016-11-21.
 * Hjälper Animation klassen att hålla koll
 * på alla bildrutor i animationen
 */

public class Frame {

    //variabel för att hålla koll på bilden
    private Bitmap image;
    //hålla koll på tiden
    private double duration;

    //konstruktor till klassen
    public Frame(Bitmap image, double duration){
        this.image = image;
        this.duration = duration;
    }

    //metod för att hämta tiden som gått
    //för denna bildruta
    public double getDuration(){
        return duration;
    }
    //metod för att skicka bilden på bildrutan
    public Bitmap getImage(){
        return image;
    }
}
