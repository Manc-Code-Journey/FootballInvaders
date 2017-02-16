package com.example.yomd.framework.animation;

import com.example.yomd.framework.util.Painter;

/**
 * Created by yomd on 2016-11-21.
 * Använder denna klass för att animera
 * saker i spelet
 */

public class Animation {
    //använda klassen Frame för att skapa bildrutor
    private Frame[] frames;
    private double[] framEndTimes;
    private int currentFrameIndex = 0;
    //variabler för att byta bildrutor
    private double totalDuration = 0;
    private double currentTime = 0;

    //konstruktor till klassen som tar ett gäng
    //bildrutor som inparametrar
    public Animation(Frame... frames){
        //lägg in alla bildrutor i vår frames array
        this.frames = frames;
        framEndTimes = new double[frames.length];
        //initiera alla frames
        for(int i = 0; i <frames.length;i++){
            Frame f = frames[i];
            totalDuration += f.getDuration();
            framEndTimes[i] = totalDuration;
        }
    }
    //metod som uppdaterar vilka frames som ska visas
    //metod använder synchronized för att tima med tråden
    public synchronized void update(float increment){
        currentTime += increment;
        //kolla om det är dags att byta bildruta
        if(currentTime > totalDuration){
            wrapAnimation();
        }
        //öka till nästa bildruta
        while(currentTime > framEndTimes[currentFrameIndex]){
            currentFrameIndex++;
        }
    }
    //metod för att byta till nästa bildruta
    private synchronized void wrapAnimation(){
        currentFrameIndex = 0;
        //%= betyder att den delar currentTime med
        //totalDuration och sparar resten
        //exempel skulle 102 %= 10 bli lika med 2
        currentTime %= totalDuration;
    }
    //metod  för att rita bildrutorna
    public synchronized void render(Painter g, int x,
                                    int y){
        g.drawImage(frames[currentFrameIndex].getImage(),
                x,y);
    }
    //samma som metoden ovan men med bredd o höjd
    public synchronized void render(Painter g, int x, int y,
                                    int width, int height){
        g.drawImage(frames[currentFrameIndex].getImage(),
                x,y,width,height);
    }
}
