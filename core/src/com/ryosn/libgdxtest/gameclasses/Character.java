package com.ryosn.libgdxtest.gameclasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends Rectangle {
	private static final long serialVersionUID = 5061775160606657675L;
	private static final int WALK_SPEED = 200;
	private static final float WALK_DELTA = 0.25f;
	private TextureAtlas atlas;
	private Animation<TextureRegion> animationCurrent;
	private Animation<TextureRegion> animationIdle;
	private Animation<TextureRegion> animationWalk;

	public Character(final Vector2 position) {
		this.setPosition(position);
		this.setSize(64, 64);
	}

	public TextureRegion getCurrentTextureRegion(final float stateTime) {
		return this.getAnimationCurrent().getKeyFrame(stateTime, true);
	}

	public void disposeAtlas() {
		this.getAtlasWalk().dispose();
	}

	private Animation<TextureRegion> instantiateNewAnimation(final TextureAtlas atlas, final String regionsName) {
		return new Animation<TextureRegion>(WALK_DELTA, atlas.findRegions(regionsName), PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationWalkUp() {
		return this.instantiateNewAnimation(this.getAtlasWalk(), "walkUp");
	}

	public Animation<TextureRegion> getAnimationWalkDown() {
		return this.instantiateNewAnimation(this.getAtlasWalk(), "walkDown");
	}

	public Animation<TextureRegion> getAnimationWalkRight() {
		return this.instantiateNewAnimation(this.getAtlasWalk(), "walkRight");
	}

	public Animation<TextureRegion> getAnimationWalkLeft() {
		return this.instantiateNewAnimation(this.getAtlasWalk(), "walkLeft");
	}

	public void moveUp() {
		this.setAnimationWalk(this.getAnimationWalkUp());
		this.setY((this.getY() + (WALK_SPEED * Gdx.graphics.getDeltaTime())));
	}

	public void moveDown() {
		this.setAnimationWalk(this.getAnimationWalkDown());
		this.setY((this.getY() - (WALK_SPEED * Gdx.graphics.getDeltaTime())));
	}

	public void moveRight() {
		this.setAnimationWalk(this.getAnimationWalkRight());
		this.setX((this.getX() + (WALK_SPEED * Gdx.graphics.getDeltaTime())));
	}

	public void moveLeft() {
		this.setAnimationWalk(this.getAnimationWalkLeft());
		this.setX((this.getX() - (WALK_SPEED * Gdx.graphics.getDeltaTime())));
	}

	public Animation<TextureRegion> getAnimationWalk() {
		return this.animationWalk;
	}

	private void setAnimationWalk(final Animation<TextureRegion> animationWalk) {
		this.animationWalk = animationWalk;
	}

	public TextureAtlas getAtlasWalk() {
		return this.atlas;
	}

	public void setAtlas(final TextureAtlas atlas) {
		this.atlas = atlas;
	}

	public Animation<TextureRegion> getAnimationCurrent() {
		return this.animationCurrent;
	}

	public void setAnimationCurrent(final Animation<TextureRegion> animationCurrent) {
		this.animationCurrent = animationCurrent;
	}

	private Animation<TextureRegion> getAnimationIdle() {
		return this.animationIdle;
	}

	private void setAnimationIdle(final Animation<TextureRegion> animationIdle) {
		this.animationIdle = animationIdle;
	}

}
