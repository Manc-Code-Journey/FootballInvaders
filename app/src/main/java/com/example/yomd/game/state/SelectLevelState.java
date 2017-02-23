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
public class SelectLevelState extends State{


    //3 UIknappar
    private UIButton level1, level2, level3;

    @Override
    public void init() {
        //skapa de två knapparna
        level1 = new UIButton(316,227,484,286,
                Assets.selectlevel,Assets.selectlevel);
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
        g.drawImage(Assets.levelplan,0,0);
        renderSelect(g);
        }

    //rita ut poängen i spelet
    private void renderSelect (Painter g){
        //bestäm font och färg för texten
        g.setFont(Typeface.SANS_SERIF, 60);
        g.setColor(Color.BLACK);
        //skriv ut poängen
        g.drawString("Select level" , 250, 60);

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
