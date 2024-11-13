package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Death extends AnimatedSprite{

    public Death(){
        super();
    }

    public Death(int x, int y, int speed, SpriteBatch batch){
        super(x, y, 3, batch, "attacking.png", 6, 2); //columns number of images in the sprite
    }
    @Override
    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            x-= 3;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            x+= 3;
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            y+= 3;
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            y-= 3;
    }


}