package pt.uma.arq.entities;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Player extends AnimatedSprite {
    private float GRAVITY;
    private int JUMP_STRENGTH;
    private boolean isJumping;
    private int score;
    private int health;
    private boolean endGame;

    public Player() {
        super();
        isJumping = false;
    }
    public Player(int x, int y, int speed, SpriteBatch batch, int score) {
        super(x, y, speed, batch, "frog_run.png", 12, 1); // columns number of images in the sprite
        isJumping = false;
        this.health = 4;
    }
    public int getX() { return x;}
    public int getY() { return y;}
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public boolean getEndGame() { return endGame; }
    public void setEndGame(boolean endGame) {this.endGame = endGame; }
    public void gameLost(){
        if (health == 0) setEndGame(true);
    }

    private void movement(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && x > 2) {
            this.boundingBox.x -=3;
            x -= 3;
            animator.setFlip(true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && x < 1212){
            this.boundingBox.x +=3;
            x += 3;
            animator.setFlip(false);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !isJumping) { //if jump happens
            initializeParameters();
            jump();
        }
        applyGravity(); //apply gravity
    }

    private void initializeParameters() {
        Random random = new Random();
        GRAVITY = random.nextFloat() * 11.0f; // Random float between 0.0 and 10.0
        JUMP_STRENGTH = random.nextInt(100) + 1; // Random integer between 1 and 100
    }
    private void jump() {
        this.boundingBox.y += JUMP_STRENGTH;
        y += JUMP_STRENGTH;
        isJumping = true;
    }
    private void applyGravity() {
        this.boundingBox.y -= GRAVITY;
        y -= GRAVITY;
        if (y < 90 && this.boundingBox.y < 90) {
            y = 90; // Ground level
            this.boundingBox.y = 90;
            isJumping = false;
        }
    }
    @Override
    public void update() {
        movement();
        gameLost();
    }
}
