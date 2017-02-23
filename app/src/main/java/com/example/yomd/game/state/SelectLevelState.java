package com.example.yomd.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.framework.util.UIButton;
import com.example.yomd.simpleandroidgdf_monday.Assets;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by tc980615 on 2017-02-16.
 */
public class SelectLevelState extends State {


    //tre UIknappar
    private UIButton level1, level2, level3;

    @Override
    public void init() {
        //skapa de tre knapparna
        level1 = new UIButton(40, 90, 120, 167, Assets.level1, Assets.level1);
        level2 = new UIButton(150, 90, 230, 167, Assets.level2, Assets.level2);
        level3 = new UIButton(260, 90, 340, 167, Assets.level3, Assets.level3);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        //Rita upp welcome bilden på skärmen
        //Hämta bilden från Assets och
        //x- och y-värdet för övre vänstra hörnet är (0,0)
        g.drawImage(Assets.levelplan, 0, 0);
        renderSelect(g);
        level1.render(g);
        level2.render(g);
        level3.render(g);
    }

    //rita ut poängen i spelet
    private void renderSelect(Painter g) {
        //bestäm font och färg för texten
        g.setFont(Typeface.SANS_SERIF, 60);
        g.setColor(Color.BLACK);
        //skriv ut poängen
        g.drawString("Select level", 250, 60);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        //Kolla vad man tryckt på
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            level1.onTouchDown(scaledX, scaledY);
            //scoreButton.onTouchDown(scaledX,scaledY);
        }
        //Reagera när man tryckt klart på skärmen
        if (e.getAction() == MotionEvent.ACTION_UP) {
            //om playknappen är aktiv och
            //man släpper skärmen
            if (level1.isPressed(scaledX, scaledY)) {
                level1.cancel();
                //se till att vi går till spelläget
                //att spelet startar!
                setCurrentState(new LevelOneState());

            }//else if(scoreButton.isPressed(scaledX,scaledY)){
            //scoreButton.cancel();
            //logga att man tryckt ner scorebutton
            //Log.d("MenuState", "Score Button Pressed");
            //setCurrentState(new ScoreState());
            else {
                level1.cancel();
                //scoreButton.cancel();
            }
        }
        return true;
    }
}
