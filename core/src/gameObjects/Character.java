package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character implements ForObject {
    // character variables starts
    public float characterX = 50; // x-axis of character
    public float characterY = 0; // y-axis of character
    public float characterWidth = 108; // character width
    public float characterHeight = 140; // character height is always same
    public float characterSpeed = 200;
    // character variable ends

    public Character () {
        // primarily it is null
    }

    @Override
    public void update() {
        // character movement
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            characterY += characterSpeed * Gdx.graphics.getDeltaTime();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            characterY -= characterSpeed * Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        Texture texture = new Texture("Run\\r4.png");

        batch.draw(texture, characterX, characterY, characterWidth, characterHeight); // drawing character
    }
}
