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
	private int walkSpeed;
	private float idleDelta;
	private float walkDelta;
	private TextureAtlas atlas;
	private Animation<TextureRegion> animationCurrent;

	public Character(final Vector2 position) {
		this.setPosition(position);
		this.setSize(64, 64);
		this.setWalkSpeed(0);
		this.setIdleDelta(0f);
		this.setWalkDelta(0f);
		this.setAtlas(null);
		this.setAnimationCurrent(null);
	}

	public TextureRegion getCurrentTextureRegion(final float stateTime) {
		return this.getAnimationCurrent().getKeyFrame(stateTime, true);
	}

	public void disposeAtlas() {
		this.getAtlas().dispose();
	}

	private Animation<TextureRegion> instantiateNewAnimation(final float delta, final String regionsName) {
		return new Animation<TextureRegion>(delta, this.atlas.findRegions(regionsName), PlayMode.LOOP_PINGPONG);
	}

	public Animation<TextureRegion> getAnimationIdleUp() {
		return this.instantiateNewAnimation(this.idleDelta, "idleUp");
	}

	public Animation<TextureRegion> getAnimationIdleDown() {
		return this.instantiateNewAnimation(this.idleDelta, "idleDown");
	}

	public Animation<TextureRegion> getAnimationIdleRight() {
		return this.instantiateNewAnimation(this.idleDelta, "idleRight");
	}

	public Animation<TextureRegion> getAnimationIdleLeft() {
		return this.instantiateNewAnimation(this.idleDelta, "idleLeft");
	}

	public Animation<TextureRegion> getAnimationWalkUp() {
		return this.instantiateNewAnimation(this.walkDelta, "walkUp");
	}

	public Animation<TextureRegion> getAnimationWalkDown() {
		return this.instantiateNewAnimation(this.walkDelta, "walkDown");
	}

	public Animation<TextureRegion> getAnimationWalkRight() {
		return this.instantiateNewAnimation(this.walkDelta, "walkRight");
	}

	public Animation<TextureRegion> getAnimationWalkLeft() {
		return this.instantiateNewAnimation(this.walkDelta, "walkLeft");
	}

	public void moveUp() {
		this.setAnimationCurrent(this.getAnimationWalkUp());
		this.setY((this.getY() + (this.walkSpeed * Gdx.graphics.getDeltaTime())));
	}

	public void moveDown() {
		this.setAnimationCurrent(this.getAnimationWalkDown());
		this.setY((this.getY() - (this.walkSpeed * Gdx.graphics.getDeltaTime())));
	}

	public void moveRight() {
		this.setAnimationCurrent(this.getAnimationWalkRight());
		this.setX((this.getX() + (this.walkSpeed * Gdx.graphics.getDeltaTime())));
	}

	public void moveLeft() {
		this.setAnimationCurrent(this.getAnimationWalkLeft());
		this.setX((this.getX() - (this.walkSpeed * Gdx.graphics.getDeltaTime())));
	}

	public void idleUp() {
		this.setAnimationCurrent(this.getAnimationIdleUp());
	}

	public void idleDown() {
		this.setAnimationCurrent(this.getAnimationIdleDown());
	}

	public void idleRight() {
		this.setAnimationCurrent(this.getAnimationIdleRight());
	}

	public void idleLeft() {
		this.setAnimationCurrent(this.getAnimationIdleLeft());
	}

	public TextureAtlas getAtlas() {
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

	public int getWalkSpeed() {
		return this.walkSpeed;
	}

	public void setWalkSpeed(final int walkSpeed) {
		this.walkSpeed = walkSpeed;
	}

	public float getIdleDelta() {
		return this.idleDelta;
	}

	public void setIdleDelta(final float idleDelta) {
		this.idleDelta = idleDelta;
	}

	public float getWalkDelta() {
		return this.walkDelta;
	}

	public void setWalkDelta(final float walkDelta) {
		this.walkDelta = walkDelta;
	}

}
