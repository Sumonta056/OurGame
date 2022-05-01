package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.our.game.MainGame;

public class HomeScreen implements Screen {

    MainGame game;

    public HomeScreen(MainGame game) {

        this.game = game;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 1, 1);

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            this.dispose();
            //game.setScreen(new MainMenuScreen(game));
            game.setScreen(new GameScreen(game));
        }

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
