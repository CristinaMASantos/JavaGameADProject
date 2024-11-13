package pt.uma.arq.game;

import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import pt.uma.arq.entities.*;

import java.util.Random;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private BackgroundManagement backgroundManagement;
    private BitmapFont font;
    private Player player;
    private Player Death;
    private Items items;
    private Timer timer;
    private Health health;
    private long bulletShooting = System.currentTimeMillis();
    private long gameTimeStart = System.currentTimeMillis();
    private long gameTime;
    private long currentGameTime;
    private long bulletShootingCooldown;

    //private Item spriteTest;
    //private Vector2 direction;

    public void endGame(){
        if (player.getEndGame()){
            BitmapFront.drawText(10, 780, " Game Over ", batch); //shows time in seconds
        }else {
            player.update();
            player.render();

            currentGameTime = System.currentTimeMillis();
            if (bulletShootingCooldown < 1000){
                bulletShootingCooldown = currentGameTime - bulletShooting;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && bulletShootingCooldown >= 1000) {
                items.spawnBullets(player);
                bulletShooting = currentGameTime;
                bulletShootingCooldown = 1;
            }
            items.render();
            items.update();
        }
    }

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(1280, 800);
        batch = new SpriteBatch();

        player = new Player(100, 90, 3, batch, 0);
        player.create();

        //Vector2 direction = new Vector2(0, -4);
        //spriteTest = new Item(player.getX(), player.getY(), batch, "laser-bolts.png", 2, 1, direction, ItemType.Bullet);
        //spriteTest.create();

        items = new Items(batch);
        timer = new Timer();
        Timer.schedule(new Timer.Task(){
            @Override
            public void run(){
                items.create();
            }
        },1, 2, -1);

        //death = new Death(300, 300, 3, batch);
        //death.create();

        font = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);
        backgroundManagement = new BackgroundManagement(batch);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        backgroundManagement.render();

        items.handleCollisions(player);

        gameTime = (currentGameTime - gameTimeStart)/1000; //Milliseconds

        //BitmapFront.drawText(10, 780, "BoundingBox: " + player.getBoundingBox(), batch);
        BitmapFront.drawText(10,780, "Game Time: "+ gameTime +" seconds",batch);
        BitmapFront.drawText(10, 750, "Score: " + player.getScore(), batch);
        BitmapFront.drawText(10, 720, "Life: " + player.getHealth(), batch);

        endGame();

        //spriteTest.render();
        //spriteTest.update();

        //death.render();
        //death.update();

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
