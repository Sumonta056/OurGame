package com.our.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.our.game.MainGame;
import jdk.tools.jmod.Main;


public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("OurGame");
		config.setWindowedMode(MainGame.SCREEN_WIDTH,MainGame.SCREEN_HEIGHT);
		config.setResizable(false); // screen will not be resizable.
		new Lwjgl3Application(new MainGame(), config);
	}
}
