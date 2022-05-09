package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.our.game.MainGame;
import gameObjects.Character;
import gameObjects.Monster;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {

    MainGame game;

    Character character; // character object
    Random rand; // for taking random value

    // monster starts
    ArrayList<Monster> monsters;
    public float monsterMinLaunchTime = 1f;
    public float monsterMaxLaunchTime = 4f;
    public float monsterLaunchTime = 0f;
    // monster ends


    public GameScreen(MainGame game) {
        this.game = game;
        rand = new Random();

        character = new Character(); // creating character object
        monsters = new ArrayList<>(); // for storing Monster objects
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

        updateMonster();

    }

    public void updateCharacter() {

        character.update();
    }

    public void updateMonster() {

        monsterLaunchTime -= Gdx.graphics.getDeltaTime();
        if (monsterLaunchTime <= 0) {
            monsterLaunchTime = rand.nextFloat() * (monsterMaxLaunchTime - monsterMinLaunchTime) + monsterMinLaunchTime;
            monsters.add(new Monster());
        }

        ArrayList<Monster> monsterToRemove = new ArrayList<>();
        for (Monster monster : monsters) {
            monster.update();
            if (monster.remove) {
                monsterToRemove.add(monster);
            }
        }
        monsters.removeAll(monsterToRemove);
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

        renderMonster();

    }

    public void renderBackground() {
        ScreenUtils.clear(1, 1, 1, 1);
    }

    public void renderCharacter() {
        character.render(game.batch);
    }

    public void renderMonster() {
        for (Monster monster : monsters) {
            monster.render(game.batch);
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
