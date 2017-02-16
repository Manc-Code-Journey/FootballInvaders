package com.example.yomd.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by YOMD on 2017-01-23.
 */

public class GameOverState extends State {

    //variabel för att hålla koll på spelarens poäng
    private String playerScore;
    //variabel för text påp skärm
    private String gameOverMessage = "GAME OVER";

    //konstruktor alltså det som anropas
    //när vi skriver new GameOverState()
    public GameOverState(int playerScore){
        //konvertera int till String m.h.a. + ""
        this.playerScore = playerScore + "";
        //kolla om vi fått nytt highscore
        if(playerScore > GameMainActivity.getHighScore()){
            //nytt highscore !!!!
            GameMainActivity.setHighScore(playerScore);
            gameOverMessage = "HIGH SCORE!";
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        //välj och rita ut en rektangel
        //som fyller hela spelskärmen
        g.setColor(Color.rgb(255,145,0));
        g.fillRect(0,0, GameMainActivity.GAME_WIDTH,
                GameMainActivity.GAME_HEIGHT);
        //byter färg
        g.setColor(Color.DKGRAY);
        //Skriv ut lite text på skärmen
        //fet text med storlek 50
        g.setFont(Typeface.DEFAULT_BOLD, 50);
        g.drawString(gameOverMessage, 257, 175);
        g.drawString(playerScore, 385,250);
        g.drawString("Touch the screen", 220,350);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_UP){
            setCurrentState(new MenuState());
        }
        return true;
    }
}
