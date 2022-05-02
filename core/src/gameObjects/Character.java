package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Character implements ForObject {
    // character variables starts
    public float characterX = 50; // x-axis of character
    public float characterY = 0; // y-axis of character
    public float characterWidth = 108; // character width
    public float characterHeight = 140; // character height is always same
    public float characterSpeed = 200;
    // character variable ends

    // run animation starts
    float runStateTime = 0f; // to calculate state time for running animation
    Animation runAnimation; // to create running animation
    TextureRegion runReg; // for animate every texture
    public int runImgCnt = 9; // number of images for run animation
    public float runFrameDuration = 0.075f; // frame duration of every texture
    // run animation ends


    public Character () {
        createRunAnimation();
    }

    @Override
    public void update() {
        // character movement
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            characterY += characterSpeed * Gdx.graphics.getDeltaTime();
//        }
//        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            characterY -= characterSpeed * Gdx.graphics.getDeltaTime();
//        }
    }

    @Override
    public void render(SpriteBatch batch) {
        renderRunAnimation(batch);
    }

    public void createRunAnimation() {
        Array<TextureRegion> textureRegion = new Array<>(); // to store all the textures.
        for (int i = 1; i <= runImgCnt + 1; i++) {
            if (i == 6) continue; // this image is ignored. because it's not necessary
            Texture texture = new Texture("Run\\r" + i + ".png"); // creating new texture
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // filtering texture
            textureRegion.add(new TextureRegion(texture)); // adding texture to texture region
        }
        runAnimation = new Animation<>(runFrameDuration, textureRegion); // creating animation.
        runStateTime = 0f; // setting state time 0
    }

    /*
    to draw run animation
     */
    public void renderRunAnimation(SpriteBatch batch) {
        runStateTime += Gdx.graphics.getDeltaTime(); // to calculate how long the character is running.
        runStateTime %= (runFrameDuration * runImgCnt); // modded for looping the animation
        runReg = (TextureRegion) runAnimation.getKeyFrame(runStateTime); // setting a texture for specific time period.`

        batch.draw(runReg, characterX, characterY, characterWidth / 2, characterHeight / 2,
                characterWidth,characterHeight,1,1,0); // drawing run animation
    }
}
