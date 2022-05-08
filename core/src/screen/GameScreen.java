package screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.our.game.MainGame;
import gameObjects.Character;

public class GameScreen implements Screen {

    MainGame game;

    Character character; // character object

    public GameScreen(MainGame game) {
        this.game = game;

        character = new Character(); // creating character object
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateObjects();

        detectCollision();


        game.batch.begin();

        renderObjects();

        game.batch.end();
    }

    /*
    this method will be used to update all objects
     */
    public void updateObjects() {
        updateCharacter();
    }

    public void updateCharacter() {
        character.update();
    }

    /*
    this method will be used to detect all kind of collision
     */
    public void detectCollision() {

    }

    /*
    this method will be used to draw all object on screen
     */
    public void renderObjects() {
        renderBackground();

        renderCharacter();
    }

    public void renderBackground() {
        ScreenUtils.clear(1, 1, 1, 1);
    }

    public void renderCharacter() {
        character.render(game.batch);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
