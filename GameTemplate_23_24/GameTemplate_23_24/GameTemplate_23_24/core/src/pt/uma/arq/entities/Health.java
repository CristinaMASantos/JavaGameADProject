package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.lang.reflect.Array;

public class Health extends AnimatedSprite{
        private SpriteBatch batch;
        private int maxHealth;
        private int almostMaxHealth;
        private int almostNoHealth;
        private int minHealth;
        private int noHealth;

        private int[] HealthStagesValues = {maxHealth, almostMaxHealth, almostNoHealth, minHealth, noHealth};
        private String[] healthStagesIMG = {"fullBar.png","almostFull.png", "twoHpBar.png", "oneHpBar.png", "ripBar.png"};

        public Health() {
                super();
        }
        public Health(SpriteBatch batch) {
                this.batch = batch;
                this.maxHealth = 4;
                this.almostMaxHealth = 3;
                this.almostNoHealth = 2;
                this.minHealth = 1;
                this.noHealth = 0;
        }

        public int[] getHealthStagesValues() {
                return HealthStagesValues;
        }

        @Override
        public void update() {
        }
}