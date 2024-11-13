package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {
    private int scoreValue;

    public int getScoreValue() {
        return scoreValue;
    }

    public void increaseScore() {
        this.scoreValue += 10;
    }

    public void update() {
    }
}
