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
    private UIButton selectlevelbutton;

    @Override
    public void init() {
        //skapa de två knapparna
        selectlevelbutton = new UIButton(196,247,424,306,
                 Assets.selectlevel,Assets.selectlevel);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        //Rita upp welcome bilden på skärmen
        //Hämta bilden från Assets och
        //x- och y-värdet för övre vänstra hörnet är (0,0)
        g.drawImage(Assets.menu,0,0);
        //rita ut de två knapparna
        selectlevelbutton.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        //Kolla vad man tryckt på
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            selectlevelbutton.onTouchDown(scaledX,scaledY);
        }
        //Reagera när man tryckt klart på skärmen
        if(e.getAction() == MotionEvent.ACTION_UP){
            //om playknappen är aktiv och
            //man släpper skärmen
            if(selectlevelbutton.isPressed(scaledX,scaledY)){
                selectlevelbutton.cancel();
                //se till att vi går till spelläget
                //att spelet startar!
                setCurrentState(new SelectLevelState());
            }else{
                selectlevelbutton.cancel();
            }
        }
        return true;
    }
}
