package com.example.yomd.game.state;

import android.graphics.Color;
import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.framework.util.UIButton;
import com.example.yomd.game.model.Ball;
import com.example.yomd.game.model.Cloud;
import com.example.yomd.game.model.Goal;
import com.example.yomd.game.model.Life;
import com.example.yomd.simpleandroidgdf_monday.Assets;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by Ann-Marie on 2017-02-23.
 */

public class LevelOneState extends State {

    //tre UIknappar
    private UIButton exitButton;

    private Ball ball;
    //ett par konstanter för spelarens bredd och höjd
    private static final int BALL_WIDTH = 158;
    private static final int BALL_HEIGHT = 85;

    private Goal goal;
    private static final int GOAL_WIDTH = 250;
    private static final int GOAL_HEIGHT = 136;

    private Life life;

    private float touchedX;
    private float touchedY;


    @Override
    public void init() {
        ball = new Ball(GameMainActivity.GAME_WIDTH / 2 - BALL_WIDTH / 2,
                GameMainActivity.GAME_HEIGHT - 45 - BALL_HEIGHT,
                BALL_WIDTH, BALL_HEIGHT);
        goal = new Goal(GameMainActivity.GAME_WIDTH / 2 - GOAL_WIDTH / 2,
                GameMainActivity.GAME_HEIGHT - 300 - GOAL_HEIGHT,
                GOAL_WIDTH, GOAL_HEIGHT);
        life = new Life(GameMainActivity.GAME_WIDTH - GameMainActivity.GAME_WIDTH / 3,
                GameMainActivity.GAME_HEIGHT - 32 - BALL_HEIGHT);

        exitButton = new UIButton(0, 0, 100, 52, Assets.exit, Assets.exit);

    }

    @Override
    public void update(float delta) {
        ball.update(delta);
        if(ball.getX() < 0){
            ball.setVelocities(0,0);
        }
    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.levelplan, 0, 0);
        renderGoal(g);
        renderBall(g);
        exitButton.render(g);
        life.render(g);
    }

    private void renderGoal (Painter g) {
        g.drawImage(Assets.goal1, (int) goal.getX(), (int) goal.getY(), GOAL_WIDTH, GOAL_HEIGHT);
    }
    private void renderBall(Painter g) {
        g.drawImage(Assets.football, (int) ball.getX(), (int) ball.getY(), BALL_WIDTH, BALL_HEIGHT);
    }




    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        //Kolla vad man tryckt på
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            exitButton.onTouchDown(scaledX, scaledY);
            touchedX = scaledX;
            touchedY = scaledY;
        }
        //Reagera när man tryckt klart på skärmen
        if (e.getAction() == MotionEvent.ACTION_UP) {
            ball.setVelocities(scaledX - touchedX, scaledY - touchedY);

            //om playknappen är aktiv och
            //man släpper skärmen
            if (exitButton.isPressed(scaledX, scaledY)) {
                exitButton.cancel();
                //logga att man tryckt ner scorebutton
                //Log.d("MenuState", "Score Button Pressed");
                setCurrentState(new SelectLevelState());
            } else {
                exitButton.cancel();
            }
        }

        return true;
    }

}

