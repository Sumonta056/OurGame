package com.our.game;

import screen.HomeScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainGame extends Game {
	public static final int SCREEN_WIDTH = 1700;
	public static final int SCREEN_HEIGHT = 950;

	public SpriteBatch batch;


	@Override
	public void create () {

		batch = new SpriteBatch();
		this.setScreen(new HomeScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {

		batch.dispose();
	}
}
