package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Character implements ForObject {
    //Collision collision; // for tracking collision of character with other objects

    // character variables starts
    public float characterX = 50; // x-axis of character
    public float characterY = 0; // y-axis of character
    public float characterWaterWidth = 100; // character width when in water
    public float characterAirWidth = 70; // character width when in air
    public float characterWidth = characterWaterWidth; // initially character is in water
    public float characterHeight = 140; // character height is always same
    // character variable ends

    // water, air divider starts
    public boolean inWater = true; // is the character in water or not? (initially in water)
    public boolean inAir = false; // is the character in air or not?
    public float waterMinHeight = 0f; // the minimum height the character can go when in water
    public float waterMaxHeight = Gdx.graphics.getHeight() / 2f - characterHeight; // the maximum height the character
    // can go when in water.
    public float airMinHeight = Gdx.graphics.getHeight() / 2f; // the minimum height the character can go when in water.
    public float airMaxHeight = Gdx.graphics.getHeight() - characterHeight; // the maximum height the character can
    // go when in air.
    public float safeDistanceFromAir = 6f; // to avoid unwanted collision with air objects
    public float safeDistanceFromWater = 6f; // to avoid unwanted collision with water objects
    // water, air divider ends

    // run animation starts
    float runStateTime = 0f; // to calculate state time for running animation
    Animation runAnimation; // to create running animation
    TextureRegion runReg; // for animate every texture
    public int runImgCnt = 10; // number of images for run animation
    public float runFrameDuration = 0.075f; // frame duration of every texture
    // run animation ends

    // jump animation starts
    public float jumpStateTime; // to calculate state time
    public float jumpTime = 2f; // the total time duration for jumping
    Animation jumpAnimation; // to create jump animation
    TextureRegion jumpReg; // to animate every texture
    public int jumpImgCnt = 10; // num of images for jump animation
    public float jumpFrameDuration = jumpTime / jumpImgCnt; // frame duration for every texture
    public float jumpMaxHeight = Gdx.graphics.getHeight() / 2f - characterHeight - safeDistanceFromAir; // the maximum height
    // the character will go while jumping.
    public float characterJumpSpeed = jumpMaxHeight / (jumpTime / 2); // character jump speed (fps)
    public int jumpDirection = 0; // is he going upward or downward? 0 means upward. 1 means downward.
    public boolean jumpDelay = false; // is jumping executing or not? (other actions will be frozen while its true).
    // jump animation ends

    // fly animation starts
    float flyStateTime = 0f; // to calculate state time for fly animation
    Animation flyAnimation; // for creating fly animation
    TextureRegion flyReg; // used to animate every texture
    public int flyImgCnt = 5; // num of images for fly animation
    public float flyFrameDuration = 0.2f; // frame duration for every fly image
    public float characterFlySpeed = 250; // character up and down speed when flying (fps)
    // fly animation ends


    /*
    default constructor
     */
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

    /*
    to create run animation
     */
    public void createRunAnimation() {
        Array<TextureRegion> textureRegion = new Array<>(); // to store all the textures.
        for (int i = 1; i <= runImgCnt; i++) {
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
    to draw jump animation
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

    /*
    to create fly animation
    */
    public void createFlyAnimation() {
        Array<TextureRegion> textureRegion = new Array<>(); // to store all the textures.
        for (int i = 1; i <= flyImgCnt; i++) {
            Texture texture = new Texture("Fly\\fly" + i + ".png"); // creating new texture
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // filtering texture
            textureRegion.add(new TextureRegion(texture));// adding texture to texture region
        }
        flyAnimation = new Animation<>(flyFrameDuration, textureRegion); // creating animation
        flyStateTime = 0f; // setting state time 0
    }

    /*
    to draw fly animation
     */
    public void renderFlyAnimation(SpriteBatch batch) {
        flyStateTime += Gdx.graphics.getDeltaTime(); // to calculate how long character is flying
        flyStateTime %= (flyFrameDuration * (flyImgCnt)); // modded for looping fly animation
        flyReg = (TextureRegion) flyAnimation.getKeyFrame(flyStateTime); // setting a texture for a specific time period

        batch.draw(flyReg, characterX, characterY, characterWidth / 2, characterHeight / 2,
                characterWidth,characterHeight,1,1,0); // drawing fly animation
    }
}
