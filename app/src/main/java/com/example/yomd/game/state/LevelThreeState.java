package com.example.yomd.game.state;

import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.framework.util.UIButton;
import com.example.yomd.game.model.Ball;
import com.example.yomd.simpleandroidgdf_monday.Assets;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by Ann-Marie on 2017-03-09.
 */

public class LevelThreeState extends State{

    //tre UIknappar
    private UIButton exitButton;

    private Ball ball;
    //ett par konstanter för spelarens bredd och höjd
    private static final int BALL_WIDTH = 158;
    private static final int BALL_HEIGHT = 85;

    @Override
    public void init() {
        ball = new Ball(GameMainActivity.GAME_WIDTH / 2 - BALL_WIDTH / 2,
                GameMainActivity.GAME_HEIGHT - 45 - BALL_HEIGHT,
                BALL_WIDTH, BALL_HEIGHT);
        exitButton = new UIButton(0, 0, 100, 52, Assets.exit, Assets.exit);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.levelplan, 0, 0);
        renderBall(g);
        exitButton.render(g);
    }

    private void renderBall(Painter g) {
        g.drawImage(Assets.football, (int) ball.getX(), (int) ball.getY(), BALL_WIDTH, BALL_HEIGHT);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        //Kolla vad man tryckt på
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            exitButton.onTouchDown(scaledX, scaledY);
        }
        //Reagera när man tryckt klart på skärmen
        if (e.getAction() == MotionEvent.ACTION_UP) {
            //om playknappen är aktiv och
            //man släpper skärmen
        } if (exitButton.isPressed(scaledX, scaledY)) {
            exitButton.cancel();
            //logga att man tryckt ner scorebutton
            //Log.d("MenuState", "Score Button Pressed");
            setCurrentState(new SelectLevelState());
        } else {
            exitButton.cancel();
        }
        return true;
    }
}