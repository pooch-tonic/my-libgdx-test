package com.ryosn.libgdxtest.gameclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Character extends Rectangle {

	private static final long serialVersionUID = 5061775160606657675L;
	private Texture sprite;

	public Character(final Texture sprite) {
		this(sprite, new Vector2(0, 0));
	}

	public Character(final Texture sprite, final Vector2 position) {
		this.setSprite(sprite);
		this.setPosition(position);
		this.setSize(sprite.getWidth(), sprite.getHeight());
	}

	public Texture getSprite() {
		return this.sprite;
	}

	private void setSprite(final Texture sprite) {
		this.sprite = sprite;
	}

}
