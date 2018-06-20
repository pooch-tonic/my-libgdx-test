package com.ryosn.libgdxtest.gameclasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Hero extends Character {

	private static final long serialVersionUID = -1933624957828747537L;

	public Hero() {
		super(new Texture(Gdx.files.internal("hero_idle_1d.png")));
	}

	public Hero(final Vector2 position) {
		super(new Texture(Gdx.files.internal("hero_idle_1d.png")), position);
	}

}
