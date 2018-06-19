package com.ryosn.libgdxtest.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.ryosn.libgdxtest.GameTest;

public class HtmlLauncher extends GwtApplication {

	// USE THIS CODE FOR A FIXED SIZE APPLICATION
	@Override
	public GwtApplicationConfiguration getConfig() {
		return new GwtApplicationConfiguration(480, 320);
	}

	@Override
	public ApplicationListener createApplicationListener() {
		return new GameTest();
	}
}