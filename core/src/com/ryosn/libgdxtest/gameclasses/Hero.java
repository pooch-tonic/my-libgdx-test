package com.ryosn.libgdxtest.gameclasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Hero extends Character {

	private static final long serialVersionUID = -1933624957828747537L;

	public Hero() {
		this(new Vector2(0, 0));
	}

	public Hero(final Vector2 position) {
		super(position);
		this.setAtlas(new TextureAtlas(Gdx.files.internal("hero_walk.txt")));
		this.setAnimationCurrent(this.getAnimationWalkDown());
	}

}
