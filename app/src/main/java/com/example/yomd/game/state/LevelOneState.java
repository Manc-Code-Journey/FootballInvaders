package com.example.yomd.game.state;

import android.graphics.Color;
import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.game.model.Ball;
import com.example.yomd.game.model.Cloud;
import com.example.yomd.simpleandroidgdf_monday.Assets;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

/**
 * Created by Ann-Marie on 2017-02-23.
 */

public class LevelOneState extends State{

    private Ball ball;
    //ett par konstanter för spelarens bredd och höjd
    private static final int BALL_WIDTH = 158;
    private static final int BALL_HEIGHT = 85;


    @Override
    public void init() {
    ball = new Ball(GameMainActivity.GAME_WIDTH / 2 - BALL_WIDTH / 2,
            GameMainActivity.GAME_HEIGHT - 45 - BALL_HEIGHT,
            BALL_WIDTH,BALL_HEIGHT);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.fotbollsplanmeny, 0, 0);
        renderBall(g);
    }

    private void renderBall(Painter g){
        g.drawImage(Assets.football, (int)ball.getX(), (int)ball.getY(), BALL_WIDTH, BALL_HEIGHT);
    }



    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

}
