package io.github.jaklbela;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.jaklbela.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;
    public OrthographicCamera camera;

    private GameScreen gameScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT);

        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
