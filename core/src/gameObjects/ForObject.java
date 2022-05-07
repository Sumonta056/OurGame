package gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import extra.Collision;

public interface ForObject {
    public void update();

    public void render(SpriteBatch batch);

    public Collision getCollision();
}