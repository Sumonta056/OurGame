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
        ScreenUtils.clear(1, 1, 1, 1);

        character.update(); // updating character object


        game.batch.begin(); // beginning of SpriteBatch


        character.render(game.batch); // rendering character


        game.batch.end(); // ending of SpriteBatch
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
