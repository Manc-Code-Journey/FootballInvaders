package com.example.yomd.game.state;

import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.game.model.Ball;
import com.example.yomd.game.model.Cloud;
import com.example.yomd.simpleandroidgdf_monday.Assets;

/**
 * Created by Ann-Marie on 2017-02-23.
 */

public class LevelOneState extends State{

    private Ball ball;
    //ett par konstanter för spelarens bredd och höjd
    private static final int PLAYER_WIDTH = 66;
    private static final int PLAYER_HEIGHT = 92;


    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        renderBall(g);
    }

    private void renderBall(Painter g){
        g.drawImage(Assets.football, (int)ball.getX(), (int)ball.getY(), 100, 60);
    }



    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
