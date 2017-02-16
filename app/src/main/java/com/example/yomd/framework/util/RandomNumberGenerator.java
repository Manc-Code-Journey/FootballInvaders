package com.example.yomd.framework.util;

import java.util.Random;

/**
 * Created by yomd on 2016-11-21.
 */

public class RandomNumberGenerator {
    //variabel för att lagra ett slumptal
    private static Random rand = new Random();

    //metod för att skapa ett slumptal mellan
    //två givna heltal
    public static int getRandomIntBetween(int lowerBound,
                                          int upperBound){
        return rand.nextInt(upperBound -lowerBound)+lowerBound;
    }
    //metod för att skicka ett slumptal mellan noll och upperbound
    public static int getRandInt(int upperBound){
        return  rand.nextInt(upperBound);
    }
}
