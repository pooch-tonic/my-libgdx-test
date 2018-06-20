package com.ryosn.libgdxtest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ryosn.libgdxtest.gameclasses.Hero;

public class GameTest extends ApplicationAdapter {
	public static final String TITLE = "My libGDX test";
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	private SpriteBatch batch;
	private Music music;
	private OrthographicCamera camera;
	private Hero hero;

	@Override
	public void create() {
		this.setBatch(new SpriteBatch());
		this.setHero(new Hero(new Vector2(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2)));
		this.setMusic(Gdx.audio.newMusic(Gdx.files.internal("Intro Jeu.mp3")));
		this.getMusic().setLooping(true);
		this.getMusic().play();
		this.setCamera(new OrthographicCamera());
		this.getCamera().setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.getBatch().begin();
		this.getBatch().draw(this.getHero().getSprite(), this.getHero().getX(), this.getHero().getY());
		this.getBatch().end();

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.getHero().setX((this.getHero().getX() - (200 * Gdx.graphics.getDeltaTime())));
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.getHero().setX((this.getHero().getX() + (200 * Gdx.graphics.getDeltaTime())));
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			this.getHero().setY((this.getHero().getY() + (200 * Gdx.graphics.getDeltaTime())));
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.getHero().setY((this.getHero().getY() - (200 * Gdx.graphics.getDeltaTime())));
		}

		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			this.camera.unproject(touchPos);
			this.getHero().setPosition(touchPos.x - (64 / 2), touchPos.y - (64 / 2));
		}

	}

	@Override
	public void dispose() {
		this.batch.dispose();
		this.hero.getSprite().dispose();
	}

	private SpriteBatch getBatch() {
		return this.batch;
	}

	private void setBatch(final SpriteBatch batch) {
		this.batch = batch;
	}

	private Music getMusic() {
		return this.music;
	}

	private void setMusic(final Music music) {
		this.music = music;
	}

	private OrthographicCamera getCamera() {
		return this.camera;
	}

	private void setCamera(final OrthographicCamera camera) {
		this.camera = camera;
	}

	private Hero getHero() {
		return this.hero;
	}

	private void setHero(final Hero hero) {
		this.hero = hero;
	}
}
