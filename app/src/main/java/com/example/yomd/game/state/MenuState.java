package com.example.yomd.game.state;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.framework.util.UIButton;
import com.example.yomd.simpleandroidgdf_monday.Assets;

/**
 * Created by yomd on 2016-11-14.
 */

public class MenuState extends State {

    //två UIknappar
    private UIButton playButton, scoreButton;

    @Override
    public void init() {
        //skapa de två knapparna
         playButton = new UIButton(316,227,484,286,
                 Assets.start,Assets.startDown);
        scoreButton = new UIButton(316,300,484,359,
                Assets.score, Assets.scoreDown);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        //Rita upp welcome bilden på skärmen
        //Hämta bilden från Assets och
        //x- och y-värdet för övre vänstra hörnet är (0,0)
        g.drawImage(Assets.welcome,0,0);
        //rita ut de två knapparna
        playButton.render(g);
        scoreButton.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        //Kolla vad man tryckt på
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            playButton.onTouchDown(scaledX,scaledY);
            scoreButton.onTouchDown(scaledX,scaledY);
        }
        //Reagera när man tryckt klart på skärmen
        if(e.getAction() == MotionEvent.ACTION_UP){
            //om playknappen ä r aktiv och
            //man släpper skärmen
            if(playButton.isPressed(scaledX,scaledY)){
                playButton.cancel();
                //se till att vi går till spelläget
                //att spelet startar!
                setCurrentState(new PlayState());

            }else if(scoreButton.isPressed(scaledX,scaledY)){
                scoreButton.cancel();
                //logga att man tryckt ner scorebutton
                //Log.d("MenuState", "Score Button Pressed");
                setCurrentState(new ScoreState());
            }else{
                playButton.cancel();
                scoreButton.cancel();
            }
        }
        return true;
    }
}
