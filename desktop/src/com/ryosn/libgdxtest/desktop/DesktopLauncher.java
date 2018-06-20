package com.ryosn.libgdxtest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.ryosn.libgdxtest.GameTest;

public class DesktopLauncher {

	public static void main(final String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = GameTest.TITLE;
		config.width = GameTest.WINDOW_WIDTH;
		config.height = GameTest.WINDOW_HEIGHT;
		config.initialBackgroundColor = new Color(0, 0, 0, 1);
		new LwjglApplication(new GameTest(), config);
	}
}
