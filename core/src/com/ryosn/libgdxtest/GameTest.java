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
import com.ryosn.libgdxtest.gameclasses.Hero;

public class GameTest extends ApplicationAdapter {
	public static final String TITLE = "My libGDX test";
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 800;
	private SpriteBatch batch;
	private DirectionsEnum lastDirection;
	private Music music;
	private OrthographicCamera camera;
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
		this.getCamera().setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setMap(new TmxMapLoader().load("niveau1.tmx"));
		this.setMapRenderer(new OrthogonalTiledMapRenderer(this.map));
		this.setStateTime(0f);
		this.setLastDireciton(DirectionsEnum.DOWN);
	}

	@Override
	public void render() {
		this.setStateTime(this.getStateTime() + Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.manageCamera();
		this.manageMap();
		this.manageBatch();
		this.manageUserInteraction();

	}

	private void manageCamera() {
		this.getCamera().position.set(this.getHero().getX() + (this.getHero().getWidth() / 2),
				this.getHero().getY() + (this.getHero().getHeight() / 2), 100);
		this.getCamera().update();
		this.getBatch().setProjectionMatrix(this.getCamera().combined);
	}

	private void manageMap() {
		this.getMapRenderer().setView(this.getCamera());
		this.getMapRenderer().render();
		// TiledMapTileLayer layerGround = new TiledMapTileLayer();
		// this.getMapRenderer().renderTileLayer();
	}

	private void manageBatch() {
		this.getBatch().begin();
		this.getBatch().draw(this.getHero().getCurrentTextureRegion(this.getStateTime()), this.getHero().getX(),
				this.getHero().getY());
		this.getBatch().end();
	}

	private void manageUserInteraction() {
		boolean moving = false;

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			this.getHero().moveUp();
			this.setLastDireciton(DirectionsEnum.UP);
			moving = true;
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.getHero().moveDown();
			this.setLastDireciton(DirectionsEnum.DOWN);
			moving = true;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.getHero().moveRight();
			this.setLastDireciton(DirectionsEnum.RIGHT);
			moving = true;
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.getHero().moveLeft();
			this.setLastDireciton(DirectionsEnum.LEFT);
			moving = true;
		}

		if (!moving) {
			switch (this.getLastDirection()) {
			case UP:
				this.getHero().idleUp();
				break;
			case DOWN:
				this.getHero().idleDown();
				break;
			case RIGHT:
				this.getHero().idleRight();
				break;
			case LEFT:
				this.getHero().idleLeft();
				break;
			}
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

	private DirectionsEnum getLastDirection() {
		return this.lastDirection;
	}

	private void setLastDireciton(final DirectionsEnum lastDirection) {
		this.lastDirection = lastDirection;
	}
}
