package com.example.yomd.simpleandroidgdf_monday;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.yomd.framework.util.InputHandler;
import com.example.yomd.framework.util.Painter;
import com.example.yomd.game.state.LoadState;
import com.example.yomd.game.state.State;

/**
 * Created by yomd on 2016-11-07.
 */

public class GameView extends SurfaceView implements Runnable {

    //Variabler för spelet
    private Bitmap gameImage;
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    private Painter graphics;

    //Trådar används för att köra olika
    //saker "parallellt" i datorns processor
    private Thread gameThread;
    //variabler för att kolla om tråden körs eller inte
    //volatile variabel syns för andra
    //threads trådar processer i processorn
    private volatile boolean running = false;
    //variabel för att kolla vilken state spelet befinner
    //sig i.
    private volatile State currentState;
    //variabel för input från användaren
    private InputHandler inputHandler;

    //konstruktor som tar innehållet i appen
    //bredden och höjden på spelet som
    //inparametrar
    public GameView(Context context, int gameWidth,
                    int gameHeight) {
        //anropa föräldraklassens konstruktor
        //med innehållet i appen
        super(context);
        //Bitmappen (bilden) får spelets bredd och höjd
        //Konfigurationen är RGB_565 vilket betyder att bilden
        //inte är transparent
        gameImage = Bitmap.createBitmap(gameWidth, gameHeight,
                Bitmap.Config.RGB_565);
        //Bilden ska täcka hela spelskärmen
        gameImageSrc = new Rect(0, 0, gameImage.getWidth(),
                gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage);
        graphics = new Painter(gameCanvas);
        //för att ha koll när SurfaceView ritas om
        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("GameView", "Surface Created");
                initInput();
                if (currentState == null) {
                    setCurrentState(new LoadState());
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("GameView", "Surface Destroyed");
                pauseGame();
            }
        });
    }

    //metod för att bestämma spelets läge just nu
    public void setCurrentState(State newState) {
        //Java Virtual Machine gör en garbage collection
        //för att rensa minnet från sådant vi inte anväder.
        System.gc();
        //initiera aktuellt spelläge
        newState.init();
        currentState = newState;
        //tala om för inputHandler vilket läge vi befinner
        //oss i
        inputHandler.setCurrentState(currentState);
    }

    //metod för hantera input i spelet
    private void initInput() {
        //om det inte finns någon InputHandler
        if (inputHandler == null) {
            //skapa en inputHandler instans
            inputHandler = new InputHandler();
        }
        //sätt igång touch lyssnaren
        setOnTouchListener(inputHandler);
    }

    //metod för att initiera spelet
    private void initGame() {
        //sätt boolean variabeln som talar
        //om ifall vi kör tråden till true
        running = true;
        //skapa instansen i denna klass och
        //ge tråden namnet Game Thread
        gameThread = new Thread(this, "Game Thread");
        //kör tråden!!
        gameThread.start();
    }

    //metod för att pausa spelet
    private void pauseGame() {
        //Sätt boolean variabeln som talr
        //om ifall vi kör tråden till false
        running = false;
        //Så länge tråden körs
        while (gameThread.isAlive()) {
            try {
                //join gör så att denna tråd kan
                //vänta på en annan tråd
                //vår tråd är pausad
                gameThread.join();
                break;
            } catch (InterruptedException e) {

            }//catch
        }//while
    }//metoden pauseGame

    //metod för att rita ut saker i spelet
    //tar en inparameter som är long vilket
    // är en variant av inte fast rymmer större
    //tal. Delta talar hur långt tiden har gått
    private void updateAndRender(long delta) {
        //f betyder float och man skriver ut
        //detta för att veta att det är ett
        //decimaltal.
        currentState.update(delta / 1000f);
        currentState.render(graphics);
        //anropa klassen renderGameImage
        //för att rita om skärmen
        renderGameImage();
    }

    //metod för att rita på skärmen
    private void renderGameImage() {
        //lockCanvas för att vi kan ritra på ytan
        Canvas screen = getHolder().lockCanvas();
        //kolla om vi har en rityta
        if (screen != null) {
            //rita ut den nya skärmen
            screen.getClipBounds(gameImageDst);
            //ritar ut
            screen.drawBitmap(gameImage, gameImageSrc,
                    gameImageDst, null);
            //här talar vi om att vi ritat klart
            getHolder().unlockCanvasAndPost(screen);
        }
    }


    public void run() {
        //två variabler för att hålla koll på tiden
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;
        //så länge tråden körs
        while(running){
            //System.nanoTime ger ett extremt exakt
            //tid just nu
            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis +
                    updateDurationMillis;
            //anropa metoden updateAndRender för att
            //rita om spelet
            updateAndRender(deltaMillis);
            //ger tidskillnad mellan nuvarande exakta tid
            //System.nanotime och den tidigare tiden vi
            //sparade innan vi uppdaterade och ritade
            //om skärmen. L betyder long
            updateDurationMillis = (System.nanoTime()-
            beforeUpdateRender)/1000000L;
            //ger det största värdet mellan
            //2 milliskeunder och värdet
            //17 - updateDurationMillis
            sleepDurationMillis = Math.max(2,
                    17 - updateDurationMillis);
            //tråden ska pausas med minst 2 millisekunder
            //detta måste göras inom try catch block
            try{
                Thread.sleep(sleepDurationMillis);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
