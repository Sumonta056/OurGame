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
    public float characterWidth = 100; // character width
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

    // jump animation starts
    public float jumpStateTime; // to calculate state time
    public float jumpTime = 2f; // the total time duration for jumping
    Animation jumpAnimation; // to create jump animation
    TextureRegion jumpReg; // to animate every texture
    public int jumpImgCnt = 10; // num of images for jump animation
    public float jumpFrameDuration = jumpTime / jumpImgCnt; // frame duration for every texture
    public float jumpMaxHeight = Gdx.graphics.getHeight() / 2f - characterHeight - 5f; // the maximum height
    // the character will go while jumping.
    public float characterJumpSpeed = jumpMaxHeight / (jumpTime / 2); // character jump speed (fps)
    public int jumpDirection = 0; // is he going upward or downward? 0 means upward. 1 means downward.
    public boolean jumpDelay = false; // is jumping executing or not? (other actions will be frozen while its true).
    // jump animation ends



    public Character () {
        createRunAnimation();
    }

    @Override
    public void update() {
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

    /*
to create jump animation
 */
    public void createJumpAnimation() {
        Array<TextureRegion> textureRegion = new Array<>(); // to store all the textures
        for (int i = 1; i <= jumpImgCnt; i++) {
            Texture texture = new Texture("Jump\\jump" + i + ".png"); // creating new texture
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // filtering texture
            textureRegion.add(new TextureRegion(texture)); // adding texture to texture region
        }
        jumpAnimation = new Animation<>(jumpFrameDuration, textureRegion); // creating animation
        jumpStateTime = 0f; // setting state time 0
    }

    /*
    to draw run animation
     */
    public void renderJumpAnimation(SpriteBatch batch) {
        jumpStateTime += Gdx.graphics.getDeltaTime(); // to calculate how long the character is jumping

        if (jumpDirection == 0) {
            // if jumpDirection is 0, the character will go upward.

            characterY += characterJumpSpeed * Gdx.graphics.getDeltaTime();
        }
        else {
            // the character will go downward. (jumpDirection is 1)

            characterY -= characterJumpSpeed * Gdx.graphics.getDeltaTime();
        }

        if (characterY >= jumpMaxHeight) {
            // if the character reached higher position the direction will be changed.

            jumpDirection = 1; // direction is set to 1.
        }

        if (jumpStateTime >= jumpTime) {
            // if jumpTime is crossed for one jump, the character will be moved to initial position, and
            // it will be ready for new action

            jumpDelay = false; // jump is finished so it is false now
            characterY = 0; // setting to initial position
        }

        jumpReg = (TextureRegion) jumpAnimation.getKeyFrame(jumpStateTime); // setting a texture for specific time period
        batch.draw(jumpReg, characterX, characterY, characterWidth / 2, characterHeight / 2,
                characterWidth,characterHeight,1,1,0); // drawing animation.
    }
}
