package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Item extends AnimatedSprite{
    private ItemType itemType;
    private Vector2 direction;
    private Random random = new Random();
    public Item() {
        super();
    }

    public Item(int x, int y, SpriteBatch batch, String path, int columns, int rows, Vector2 direction, ItemType itemType) {
        super(x, y, batch, path, columns, rows); // columns number of images in the sprite
        this.direction = direction;
        this.itemType = itemType;
        if (random.nextBoolean()){
            direction.x = direction.x * -1;
        }
    }
    public int getX() { return x;}
    public int getY() { return y;}
    public ItemType getItemType() { return itemType; }
     public void moveFruit(){
         this.x += direction.x;
         this.y -= direction.y;
         this.boundingBox.x += direction.x;
         this.boundingBox.y -= direction.y;
         if (this.x >= 1230){
             this.direction.x *= -1;
         }
         if (this.x <= 10){
             this.direction.x *= -1;
         }
     }
     public boolean isOutOfBounds(){
         if (this.x >= 1260 || this.x <= 0 || this.y >= 780 || this.y < 90) {
             return true;
         }
         return false;
     }
    public void update() {
        moveFruit();
    }
}
