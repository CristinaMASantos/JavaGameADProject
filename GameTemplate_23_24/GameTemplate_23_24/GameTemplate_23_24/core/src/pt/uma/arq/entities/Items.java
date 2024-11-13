package pt.uma.arq.entities;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import pt.uma.arq.game.Game;

public class Items{

    private SpriteBatch batch;
    private ArrayList<Item> itemsList;
    private Random random = new Random();
    private String[] fruits = {"melon.png","apple.png", "pineapple.png", "razor_disc.png", "spiked_stone.png", "square_stone.png"};
    private int[] itemsColumns = {17, 17, 17, 8, 4, 4};
    private ItemType[] itemTypes = {ItemType.Fruit, ItemType.Fruit, ItemType.Fruit, ItemType.Trap, ItemType.Trap, ItemType.Trap};
    private ArrayList<Item> bulletList;
    public Items(){
        itemsList = new ArrayList<Item>();
    }
    public Items(SpriteBatch batch){
        this.batch = batch;
        itemsList = new ArrayList<Item>();
        bulletList = new ArrayList<Item>();
    }
    public void spawnFruitsAndTraps(){
        int randomIndex = random.nextInt(6);
        Vector2 direction = new Vector2(random.nextInt(3) + 1, random.nextInt(3) + 1);
        Item item = new Item(random.nextInt(1260) , 740, batch, fruits[randomIndex], itemsColumns[randomIndex], 1, direction, itemTypes[randomIndex]);
        item.create();
        itemsList.add(item);
    }
    public void spawnBullets(Player player){
        Vector2 direction = new Vector2(0, -4);
        Item item = new Item(player.getX(), player.getY(), batch, "laser-bolts.png", 2, 1, direction, ItemType.Bullet);
        item.create();
        bulletList.add(item);
    }
    public void iteratorBullets(){
        // ITERATOR
        Iterator<Item> it = bulletList.iterator();
        while(it.hasNext()){
            Item item = it.next();
            if (item.collided || item.isOutOfBounds()) {
                it.remove();
            }
        }
    }
    public void iteratorItems(){
        // ITERATOR
        Iterator<Item> it = itemsList.iterator();
        while(it.hasNext()){
            Item item = it.next();
            if (item.collided || item.isOutOfBounds()) {
                it.remove();
            }
        }
    }

    public void handleCollisions(Player player) {
        for (Item item : itemsList) {
            if (player.collidedWith(item)) {
                if (item.getItemType() == ItemType.Fruit) {
                    item.collided = true;
                    player.setScore(player.getScore() + 5);
                } else if(item.getItemType() == ItemType.Trap) {
                    item.collided = true;
                    player.setHealth(player.getHealth() -1);
                }
            }
            for (Item bullet : bulletList){
                if (bullet.collidedWith(item)){
                    if (item.getItemType() == ItemType.Fruit) {
                        item.collided = true;
                        bullet.collided = true;
                        player.setScore(player.getScore() + 5);
                    } else if(item.getItemType() == ItemType.Trap) {
                        item.collided = true;
                        bullet.collided = true;

                    }
                }
            }
        }
    }
    public void renderItems(){
        for (Item item: itemsList) {
            item.render();
        }
        iteratorItems();
    }
    public void updateBullets(){
        for (Item item : bulletList) {
            item.update();
        }
    }
    public void updateItems(){
        for (Item item : itemsList) {
            item.update();
        }
    }
    public void renderBullets(){
        for (Item item: bulletList) {
            item.render();
        }
        iteratorBullets();
    }
    public void create(){
        spawnFruitsAndTraps();
    }
    public void update() {
        updateItems();
        updateBullets();
    }
    public void render(){
        renderItems();
        renderBullets();
    }
}