package com.example.yomd.framework.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * Created by yomd on 2016-11-07.
 * Klass för att hjälpa oss rita ut saker
 */

public class Painter {
    //canvas är själva ritytan
    private Canvas canvas;
    //Paint är själva verktyget för att rita ut
    // bilder i rektanglar på Canvas
    private Paint paint;
    //Variabler för att rita bilder i rektanglar
    private Rect srcRect;
    private Rect dstRect;
    //Rektangel som kan ta decimaltal(float) för
    //cirkulära bilder
    private RectF dstRectF;

    //konstruktor till klassen Paint
    //Tar en inparameter som är en Canvas
    public Painter(Canvas canvas){
        //tilldela denna rityta till vår Painter klass
        this.canvas = canvas;
        //skapa instanser av paint och alla rektanglar
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        dstRectF = new RectF();
    }

    //metod för att bestämma färgen som vi ritar med
    //en inparameter som är färgen man vill ha
    public void setColor(int color){
        paint.setColor(color);
    }

    //metod som används för att bestämma font och fontstorlek
    public void setFont(Typeface typeface, float textSize){
        paint.setTypeface(typeface);
        paint.setTextSize(textSize);
    }

    //metod som skriver ut text på vår rityta
    //tre inparametrar först själva texten som ska
    //skrivas ut sen var någonstans i x- och y-led.
    public void drawString(String str, int x, int y){
        canvas.drawText(str,x,y,paint);
    }

    //rita ut en rektangel
    //inparametrar är x- och y-position
    //samt bredd och höjd på rektangeln
    public void fillRect(int x, int y, int width, int height){
        dstRect.set(x,y,x+width,y+height);
        //sen bestämmer vi vilken stil rektangeln ska ritas
        //ut i och i detta fall ifylld.
        paint.setStyle(Paint.Style.FILL);
        //rita ut själva rektangeln på vår rityta
        canvas.drawRect(dstRect,paint);
    }

    //metod som ritar ut en bild
    public void drawImage(Bitmap bitmap, int x, int y,
                          int width, int height){
        //rektagel som definierar bildensom den är
        srcRect.set(0,0,bitmap.getWidth(),bitmap.getHeight());
        //rektangel som defenierar den slutliga bildstorleken
        dstRect.set(x,y, x+width,y+height);
        //rita ut detta på ritytan
        canvas.drawBitmap(bitmap,srcRect,dstRect,paint);
    }
    //metod 2 för att rita ut en bild
    //som inte använder bredd och höjd som inparametrar
    //denna används så man inte behöver skala om bilden
    public void drawImage(Bitmap bitmap, int x, int y){
        canvas.drawBitmap(bitmap,x,y,paint);
    }

    //metod för att rita cirkulära objekt
    //eftersom dessa använder pi så blir det decimala tal
    //vi behöver använda float
    public void fillOval(int x, int y, int width, int height){
        //vilken stil ska vi rita med
        //i detta fall ifylld
        paint.setStyle(Paint.Style.FILL);
        //Här bestäms var den ska ritas ut
        dstRectF.set(x,y, x+width,y+height);
        //rita ut den cirkulära ytan
        canvas.drawOval(dstRectF,paint);
    }
}
