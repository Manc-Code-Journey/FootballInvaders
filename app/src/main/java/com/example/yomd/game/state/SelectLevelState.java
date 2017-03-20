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
    private UIButton level1, level2, level3, exitButton;

    @Override
    public void init() {
        //skapa de tre knapparna
        level1 = new UIButton(40, 190, 120, 267, Assets.level1, Assets.level1);
        level2 = new UIButton(150, 190, 230, 267, Assets.level2, Assets.level2);
        level3 = new UIButton(260, 190, 340, 267, Assets.level3, Assets.level3);
        exitButton = new UIButton(0, 0, 100, 52, Assets.exit, Assets.exit);
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        //Rita upp welcome bilden på skärmen
        //Hämta bilden från Assets och
        //x- och y-värdet för övre vänstra hörnet är (0,0)
        g.drawImage(Assets.selectlevelpic, 0, 0);
        level1.render(g);
        level2.render(g);
        level3.render(g);
        exitButton.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        //Kolla vad man tryckt på
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            level1.onTouchDown(scaledX, scaledY);
            level2.onTouchDown(scaledX, scaledY);
            level3.onTouchDown(scaledX, scaledY);
            exitButton.onTouchDown(scaledX,scaledY);
        }
        //Reagera när man tryckt klart på skärmen
        if (e.getAction() == MotionEvent.ACTION_UP) {
            //om playknappen är aktiv och
            //man släpper skärmen
            if (level1.isPressed(scaledX, scaledY)) {
                level1.cancel();
                //se till att vi går till spelläget
                //att spelet startar!
                GameMainActivity.currentLevel = 1;
                setCurrentState(new LevelOneState());
            }else if (level2.isPressed(scaledX, scaledY)) {
                level2.cancel();
                GameMainActivity.currentLevel = 2;
                setCurrentState(new LevelTwoState());
            }else if (level3.isPressed(scaledX, scaledY)) {
                level3.cancel();
                GameMainActivity.currentLevel = 3;
                setCurrentState(new LevelThreeState());
            }else if (exitButton.isPressed(scaledX,scaledY)) {
                exitButton.cancel();
                setCurrentState(new MenuState());
            }else {
                level1.cancel();
                level2.cancel();
                level3.cancel();
                exitButton.cancel();
            }
        }
        return true;
    }
}
