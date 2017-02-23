package com.example.yomd.game.state;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.yomd.framework.util.Painter;
import com.example.yomd.game.model.Block;
import com.example.yomd.game.model.Cloud;
import com.example.yomd.game.model.Player;
import com.example.yomd.simpleandroidgdf_monday.Assets;
import com.example.yomd.simpleandroidgdf_monday.GameMainActivity;

import java.util.ArrayList;

/**
 * Created by yomd on 2016-12-05.
 * Denna klass är det som definierar vårt spel
 */

public class PlayState extends State {

    //spelaren
    private Player player;
    //en array av block
    private ArrayList<Block> blocks;
    //lite moln
    private Cloud cloud, cloud2;
    //antal poäng
    private int playerScore = 0;
    //ett par konstanter för att bestämma blockstorlek
    private static final int BLOCK_HEIGHT = 50;
    private static final int BLOCK_WIDTH = 20;
    //hastigheten på blocken
    private int blockSpeed = -200;
    //ett par konstanter för spelarens bredd och höjd
    private static final int PLAYER_WIDTH = 66;
    private static final int PLAYER_HEIGHT = 92;

    private float recentTouchY;

    @Override
    //metod som initerar vårt spel
    public void init() {
        //skapa spelaren
        //Spelarens startposition är i mitten längst ner av skärmen
        //y = spelplanens höjd - 45 - spelarens höjd
        //sen anger vi spelarens bredd och höjd
        player = new Player(GameMainActivity.GAME_WIDTH/2-PLAYER_WIDTH/2,
                GameMainActivity.GAME_HEIGHT - 45 - PLAYER_HEIGHT,
                PLAYER_WIDTH,PLAYER_HEIGHT);
        //skapa ArrayList för att placera alla blocken i
        blocks = new ArrayList<Block>();
        //skapa våra två gulliga moln
        //första molnet startar på x=100 och y=100
        cloud = new Cloud(100,100);
        //andra molnet startar på x=500 och y = 50
        cloud2 = new Cloud(500,50);
        //skapa ett gäng Block och lägg i vår Block array
        for(int i = 0; i < 5; i++){
            //skapa ett Block
            //x= i värdet *200 alltså blir det första varvet
            //i=0 vilket ger 0*200 = 0
            //andra varvet är i=1 vilket ger 1*200 = 200
            //o.s.v.
            Block b = new Block(i * 200, GameMainActivity.GAME_HEIGHT -95,
                    BLOCK_WIDTH, BLOCK_HEIGHT);
            //lägg in detta block i vår Block array som heter blocks
            blocks.add(b);
        }
    }

    @Override
    //metoden som uppdaterar spelet (flyttar på grejer o.s.v.)
    public void update(float delta) {
        //kolla om spelaren inte ! lever
        if(!player.isAlive()){
            //för att vi ska kunna köra vår kod kommentera
            //bort denna rad
            setCurrentState(new GameOverState(playerScore/100));
            //för att bara avsluta appen (tillfälligt bara)
            //System.exit(0);
        }
        //öka poängen med ett
        playerScore += 1;
        //öka hastigheten på blocken varje gång vi når 500 poäng mer
        if(playerScore % 500 == 0 && blockSpeed > -280){
            blockSpeed -= 10;
        }
        //uppdatera molnet så att de rör på sig
        cloud.update(delta);
        cloud2.update(delta);
        //uppdatera animationen för spelaren
        Assets.runAnim.update(delta);
        //uppdatera spelaren
        player.update(delta);
        //metod för att uppdatera alla blocken
        updateBlocks(delta);
    }

    //metod för att uppdatera alla blocken
    private void updateBlocks(float delta){
        //detta är samma sak som en foreach loop
        //när man skriver Block b : blocks
        for(int i = 0; i < blocks.size(); i++){
            //uppdatera blocket
            Block b = blocks.get(i);
            b.update(delta, blockSpeed);

            //kolla om blocket krockar med spelaren
            if(b.isVisible()){
                if(player.isDucked() && Rect.intersects(b.getRect(),
                        player.getDuckRect())){
                    b.onCollide(player);
                }else if(!player.isDucked()&& Rect.intersects(b.getRect(),
                        player.getRect())){
                    b.onCollide(player);
                }//else if
            }//if
        }//for
    }//updateBlocks

    @Override
    //metod som ritar ut allt på spelet
    public void render(Painter g) {
        //bestäm en färg
        g.setColor(Color.rgb(208,244,247));
        //Rita ut denna färg över hela spelplan
        g.fillRect(0,0, GameMainActivity.GAME_WIDTH,
                GameMainActivity.GAME_HEIGHT);
        //rita alla saker i spelet
        renderPlayer(g);
        renderBlocks(g);
        renderSun(g);
        renderClouds(g);
        g.drawImage(Assets.grass, 0, 405);
        renderScore(g);
    }

    //rita ut poängen i spelet
    private void renderScore(Painter g){
        //bestäm font och färg för texten
        g.setFont(Typeface.SANS_SERIF, 25);
        g.setColor(Color.GRAY);
        //skriv ut poängen
        g.drawString("" + playerScore / 100, 20, 30);
    }

    //rita ut spelaren i spelet
    private void renderPlayer(Painter g){
        //är spelaren på backen
        if(player.isGrounded()){
            //duckar spelaren?
            if(player.isDucked()){
                //rita ut spelaren som duckar
                g.drawImage(Assets.duck, (int)player.getX(), (int)player.getY());
            }else {
                Assets.runAnim.render(g,(int)player.getX(), (int)player.getY(),
                        player.getWidth(),player.getHeight());
            }//else
        }//om vi är på backen
        else{
            //spelaren hoppar
            g.drawImage(Assets.jump, (int)player.getX(), (int)player.getY(),
                    player.getWidth(), player.getHeight());
        }//else
    }//slut på metoden

    //metod för att rita ut alla block i spelet
    private void renderBlocks(Painter g){
        for(int i = 0; i < blocks.size(); i++){
            Block b = blocks.get(i);
            //rita bara ut block som är synliga
            if(b.isVisible()){
                g.drawImage(Assets.block, (int)b.getX(), (int)b.getY(),
                        BLOCK_WIDTH, BLOCK_HEIGHT);
            }//if
        }//for
    }//slut på metoden

    //metod som ritar ut en sol i vårat spel
    private void renderSun(Painter g){
        g.setColor(Color.rgb(255,165,0));
        g.fillOval(715, -85, 170, 170);
        g.setColor(Color.YELLOW);
        g.fillOval(725,-75,150,150);
    }

    //rita ut molnen i spelet
    private void renderClouds(Painter g){
        g.drawImage(Assets.cloud1, (int)cloud.getX(), (int)cloud.getY(), 100, 60);
        g.drawImage(Assets.cloud2, (int)cloud2.getX(), (int)cloud2.getY(),100, 60);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if(e.getAction() == MotionEvent.ACTION_DOWN){
            recentTouchY = scaledY;
        }else if(e.getAction() == MotionEvent.ACTION_UP){
            //om den som spelar har dragit fingret mer än 50 pixlar uppåt
            //annars om spelaren dragit fingret mer än 50 pixlar nedåt
            if(scaledY - recentTouchY < -50){
                player.jump();
            }else if(scaledY - recentTouchY > 50){
                player.duck();
            }
        }
        return true;
    }
}
