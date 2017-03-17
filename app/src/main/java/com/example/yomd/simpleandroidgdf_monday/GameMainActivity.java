package com.example.yomd.simpleandroidgdf_monday;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class GameMainActivity extends AppCompatActivity {

    //konstanter för bredd och höjd på spelet
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;
    //Ett GameView objekt för att skapa vårt spel
    public static GameView sGame;
    //En variabel för att ladda in bilder och ljud till spelet
    public static AssetManager assets;
    //variabler för att lagra highscore
    private static SharedPreferences pref;
    //konstant som är vårat nyckelord
    private static final String HIGHSCORE_KEY = "highScoreKey";
    private static int highScore;

    public static int currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getPreferences(Activity.MODE_PRIVATE);
        highScore = retrieveHighScore();
        //hämta våra filer till spelet
        assets = getAssets();
        //Skapa instansen av spelet med vår bredd och höjd
        sGame = new GameView(this,GAME_WIDTH,GAME_HEIGHT);
        setContentView(sGame);
        //för att skärmen inte ska slockna när vi spelar spelet
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        currentLevel = 0;
    }//här slutar onCreate metoden

    //metod för att sätta highscore
    public static void setHighScore(int highScore){
        GameMainActivity.highScore = highScore;
        //Editor används för att skriva till minnet
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HIGHSCORE_KEY, highScore);
        editor.apply();
    }
    //metod för att hämta highscore
    private int retrieveHighScore(){
        return pref.getInt(HIGHSCORE_KEY, 0);
    }
    //metod för att få reda på highscore
    public static int getHighScore(){
        return highScore;
    }
}
