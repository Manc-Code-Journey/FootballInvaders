package com.example.yomd.framework.util;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by yomd on 2016-12-05.
 */

public class UIButton {

    //rektangel för bilden på knappen
    private Rect buttonRect;
    //variabel som håller koll på om
    //knappen är netryckt eller inte
    private boolean buttonDown = false;
    //två bilder för knappen
    //ena bilden är när knappen är nertryckt
    //andra bilden är när knappen är uppe
    private Bitmap buttonImage, buttonDownImage;

    //konstruktorn till klassen UIButton
    public UIButton(int left, int top, int rigth,
                    int bottom, Bitmap buttonImage,
                    Bitmap buttonPressedImage){
        //skapa rektangeln för knappen
        buttonRect = new Rect(left,top,rigth,bottom);
        this.buttonImage = buttonImage;
        this.buttonDownImage = buttonPressedImage;
    }

    //metod som ritar upp knappen
    public void render(Painter g){
        //här är en annan variant av if något är sant
        //gör detta annars gör något annat.
        //if skrivs som ? och annars skrivs som :
        Bitmap currentButtonImage = buttonDown ? buttonDownImage : buttonImage;
        //rita bilden
        g.drawImage(currentButtonImage, buttonRect.left,buttonRect.top,
                buttonRect.width(),buttonRect.height());
    }

    //touch händelsen för knappen
    public void onTouchDown(int touchX, int touchY){
        //om vi trycker innanför (contains) knappens rektangel så
        if(buttonRect.contains(touchX,touchY)){
            //blir buttonDown sant
            buttonDown = true;
        }else{
            buttonDown = false;
        }
    }

    //metod som ser till att buttonDown blir falskt
    public void cancel(){
        buttonDown = false;
    }

    //metod för att tala om ifall man tryckt på knappen
    public boolean isPressed(int touchX, int touchY){
        return buttonDown && buttonRect.contains(touchX,touchY);
    }
}
