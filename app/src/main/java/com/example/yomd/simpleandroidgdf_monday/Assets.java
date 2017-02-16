package com.example.yomd.simpleandroidgdf_monday;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.yomd.framework.animation.Animation;
import com.example.yomd.framework.animation.Frame;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yomd on 2016-11-14.
 * Denna klass hjälper oss att ladda in alla
 * bilder och ljud till våra spel
 */

public class Assets {
    //variabel för att fixa ljudet
    private static SoundPool soundPool;
    //variabel som håller koll på välkomstbilden
    //plus alla andra bilder o ljud till spelet
    public static Bitmap welcome, block, cloud1,cloud2,duck,
            grass,jump,run1,run2,run3,run4,run5,scoreDown,
            score,startDown,start,fotbollsplanmeny, selectlevel;

    //animation när gubben springer
    public static Animation runAnim;
    //lite ljud
    public static int hitID, onJumpID;

    //metod för att ladda allt i spelet
    public static void load(){
        //ladda in bild med namnet welcome
        //false betyder att den inte är
        //transparent (genomskinlig).
        //viktigt att stava filnamnet rätt!!!
        welcome = loadBitmap("welcome.png", false);
        block = loadBitmap("block.png", false);
        cloud1 = loadBitmap("cloud1.png", true);
        cloud2 = loadBitmap("cloud2.png", true);
        duck = loadBitmap("duck.png", true);
        grass = loadBitmap("grass.png", false);
        jump = loadBitmap("jump.png", true);
        run1 = loadBitmap("run_anim1.png", true);
        run2 = loadBitmap("run_anim2.png", true);
        run3 = loadBitmap("run_anim3.png", true);
        run4 = loadBitmap("run_anim4.png", true);
        run5 = loadBitmap("run_anim5.png", true);
        scoreDown = loadBitmap("score_button_down.png", true);
        score = loadBitmap("score_button.png", true);
        startDown = loadBitmap("start_button_down.png", true);
        start = loadBitmap("start_button.png", true);
        fotbollsplanmeny = loadBitmap("fotbollsplanmeny.jpg", false);
        selectlevel = loadBitmap("selectlevel.png", true);

        //Ladda in animationen när gubben springer
        Frame f1 = new Frame(run1, .1f);
        Frame f2 = new Frame(run2, .1f);
        Frame f3 = new Frame(run3, .1f);
        Frame f4 = new Frame(run4, .1f);
        Frame f5 = new Frame(run5, .1f);
        runAnim = new Animation(f1,f2,f3,f4,f5,f3,f2);

        //ladda in vårat ljud
        hitID = loadSound("hit.wav");
        onJumpID = loadSound("onjump.wav");
    }

    //metod för att ladda in Bitmaps i vårt spel
    private static Bitmap loadBitmap(String filename,
                                     boolean transparency){
        //variabel för inströmmen som i detta fall
        //är en bild
        InputStream inputStream = null;
        //felhantering eftersom vi håller på
        //med att hämta filer från dator/mobil
        try{
            //här lägger vi kod som kan gå fel
            //öppnar bilden från vår assets mapp
            inputStream = GameMainActivity.assets.open(filename);
        }catch (IOException e){
            //skriv ut felmeddelandet t.ex. att filen inte finns
            e.printStackTrace();
        }
        //här gör vi om filen till en Bitmap som vi kan använda
        //på vår Canvas i vårt spel
        BitmapFactory.Options options = new BitmapFactory.Options();
        if(transparency){
            //om bilden är genomskinlig använd ARGB_8888
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }else{
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        //Skapa bitmapen vi laddat in
        //Antagligen fel i boken på sidan 313 med new Options()
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream,
                null,options);
        return bitmap;
    }

    //metod för att ladda in ljud i spelet
    private static int loadSound(String filename){
        //ljudfilens ID
        int soundID = 0;
        //om soundPool inte är initierad så gör det först
        if(soundPool == null){
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC,
                    0);
        }
        //felhantering eftersom vi letar efter en fil
        try{
            soundID = soundPool.load(
                    GameMainActivity.assets.openFd(filename),1);
        }catch (IOException e){
            e.printStackTrace();
        }
        return soundID;
    }

    //metod för att spela upp ett ljud
    public static void playSound(int soundID){
        soundPool.play(soundID,1,1,1,0,1);
    }
}
