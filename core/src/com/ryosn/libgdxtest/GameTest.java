package com.ryosn.libgdxtest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ryosn.libgdxtest.gameclasses.Hero;

public class GameTest extends ApplicationAdapter {
	public static final String TITLE = "My libGDX test";
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 800;
	private SpriteBatch batch;
	private Music music;
	private OrthographicCamera camera;
	private Viewport viewport;
	private TiledMapRenderer mapRenderer;
	private Hero hero;
	private TiledMap map;
	private float stateTime;

	@Override
	public void create() {
		this.setBatch(new SpriteBatch());
		this.setHero(new Hero(new Vector2(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2)));
		this.setMusic(Gdx.audio.newMusic(Gdx.files.internal("Intro Jeu.mp3")));
		this.getMusic().setLooping(true);
		this.getMusic().play();
		this.setCamera(new OrthographicCamera());
		this.setViewport(new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT, this.camera));
		this.getCamera().setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setMap(new TmxMapLoader().load("niveau1.tmx"));
		this.setMapRenderer(new OrthogonalTiledMapRenderer(this.map));
		this.setStateTime(0f);
	}

	@Override
	public void render() {
		this.setStateTime(this.getStateTime() + Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.getMapRenderer().setView(this.camera);
		this.getMapRenderer().render();

		this.getBatch().begin();
		this.getBatch().draw(this.getHero().getCurrentTextureRegion(this.getStateTime()), this.getHero().getX(),
				this.getHero().getY());
		this.getBatch().end();

		/*
		 * this.getCamera().translate(this.getHero().getX(), this.getHero().getY());
		 * this.getCamera().position.set(0, 0, 0); this.getCamera().update();
		 */
		this.manageUserInteraction();

	}

	private void manageUserInteraction() {
		boolean moving = false;

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.getHero().moveRight();
			moving = true;
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.getHero().moveLeft();
			moving = true;
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			this.getHero().moveUp();
			moving = true;
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.getHero().moveDown();
			moving = true;
		}

		if (!moving) {
			this.resetStateTime();
		}

		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			this.getCamera().unproject(touchPos);
			this.getHero().setPosition(touchPos.x - (64 / 2), touchPos.y - (64 / 2));
		}
	}

	@Override
	public void dispose() {
		this.getBatch().dispose();
		this.getHero().disposeAtlas();
		this.getMap().dispose();
		this.getMusic().dispose();
	}

	private void resetStateTime() {
		this.setStateTime(0f);
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

	private Viewport getViewport() {
		return this.viewport;
	}

	private void setViewport(final Viewport viewport) {
		this.viewport = viewport;
	}

	private TiledMapRenderer getMapRenderer() {
		return this.mapRenderer;
	}

	private void setMapRenderer(final TiledMapRenderer mapRenderer) {
		this.mapRenderer = mapRenderer;
	}

	private TiledMap getMap() {
		return this.map;
	}

	private void setMap(final TiledMap map) {
		this.map = map;
	}

	private float getStateTime() {
		return this.stateTime;
	}

	private void setStateTime(final float stateTime) {
		this.stateTime = stateTime;
	}
}
