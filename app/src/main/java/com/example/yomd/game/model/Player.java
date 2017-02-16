package com.example.yomd.game.model;

import android.graphics.Rect;

import com.example.yomd.simpleandroidgdf_monday.Assets;

/**
 * Created by yomd on 2016-11-28.
 */

public class Player {
    //variabler för x- och y-position
    //bredd och höjd, hastighet i y-led
    //samt rektanglar för spelaren
    //i normalläge, när spelaren hoppar
    //och rektangeln för marken
    private float x,y;
    private int width, height, velY;
    private Rect rect, duckRect, ground;
    //variabeler för att kolla om spelaren lever
    //kolla om spelaren duckar och hur länge
    private boolean isAlive;
    private boolean isDucked;
    //duckar i 0,6 sekunder
    private float duckDuration = .6f;

    //Hastighet när spelaren hoppar
    private static final int JUMP_VELOCITY = -600;
    //gravitationen på spelaren
    private static  final int ACCEL_GRAVITY = 1800;

    //konstruktorn till klassen
    //tar fyra inparametrar x- och y-position
    //bredd och höjd på spelaren
    public Player(float x, float y, int width,
                  int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //Markens rektangel
        ground = new Rect(0, 405, 0 +800 , 405 + 45);
        //spelarens rektangel
        rect = new Rect();
        //spelarens rektangel när spelaren duckar
        duckRect = new Rect();
        //spelaren är levande som default
        isAlive = true;
        //duckar inte som default
        isDucked = false;
    }
    //Uppdatera spelarens position
    //delta värdet avgör hastigheten
    public void update(float delta){
        //Om spelaren duckar och
        //det finns kvar tid på tiden
        //spelaren duckar
        if(duckDuration > 0 && isDucked){
            //minska spelarens tid att ducka
            //med delta
            duckDuration -= delta;
        }else{
            //spelaren duckar inte
            isDucked = false;
            //sätt tillbaka tiden att ducka till 0.6
            duckDuration = 0.6f;
        }
        //Är spelaren inte (!) på backen (hoppar)
        if(!isGrounded()){
            //öka gravitationen med delta
            velY += ACCEL_GRAVITY * delta;
        }else{
            //y värdet är 406 - höjden på spelaren
            y = 406 - height;
            //hastigheten i y-led är 0
            velY = 0;
        }

        //uppdatera y-värdet för spelaren
        y += velY * delta;
        //anropa metoden updateRects för att
        //rita om spelaren på ny position
        updateRects();
    }
    //metod för att uppdatera spelarens rektangel
    public void updateRects(){
        rect.set((int)x + 10, (int)y, (int)x + (width-20),
                (int)y + height);
        //rektangeln när spelaren hoppar
        //har inte riktigt koll varför han lägger
        //till och drar ifrån 20 lite överallt
        duckRect.set((int)x, (int)y+20,
                (int)x + width,
                (int)y + 20 + (height -20));
    }
    //metod för att hålla koll på när
    //spelaren hoppar
    public void jump(){

        //om spelaren är på backen
        if(isGrounded()){
            //spela hoppljud
            Assets.playSound(Assets.onJumpID);
            //tala om att vi inte duckar
            isDucked = false;
            duckDuration = .6f;
            //Hoppa 10 pixlar i y-led
            y -= 10;
            velY = JUMP_VELOCITY;
            //uppdatera spelarens rektangel
            updateRects();
        }
    }
    //metod för när spelaren duckar
    public void duck(){
        //om spelaren är på backen
        if(isGrounded()){
            isDucked = true;
        }
    }
    //metod som anropas när spelaren kolliderar
    //med ett block tar en inparameter dx
    //som talar om hur mycket spelaren
    //ska flyttas i x-led
    public void pushBack(int dx){
        //uppdaterar x-värdet för spelaren
        x -= dx;
        //spela ett ljud när spelaren kolliderar
        Assets.playSound(Assets.hitID);
        //När spelaren är på vänster sida
        // om spelplan så dör man (med
        //halva spelarens bredd)
        if (x < -width/2){
            isAlive = false;
        }
        //uppdater spelarens rektangel
        rect.set((int)x, (int)y, (int)x +width,
                (int)y + height);
    }
    //Metod för att kolla om spelaren är
    //på backen eller inte
    public boolean isGrounded(){
        //kolla om spelarens rektangel
        //krockar med markens rektangel
        return Rect.intersects(rect,ground);
    }
    //metod för att kolla om man duckar eller inte
    public boolean isDucked(){
        return isDucked;
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
    //metod som returnerar spelarens
    //hastighet i y-led
    public int getVelY(){
        return velY;
    }
    //metoder som returnera spelarens
    //båda rektanglar samt markens rektangel
    public Rect getRect(){
        return rect;
    }
    public Rect getDuckRect(){
        return duckRect;
    }
    public Rect getGround(){
        return ground;
    }
    //metod för att reurnera om
    //spelaren lever eller inte
    public  boolean isAlive(){
        return isAlive;
    }
    //metod för att returnera
    //tiden som spelaren duckat
    public float getDuckDuration(){
        return duckDuration;
    }
}
