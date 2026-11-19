package io.github.jaklbela.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.jaklbela.GameResources;
import io.github.jaklbela.GameSettings;
import io.github.jaklbela.Main;
import io.github.jaklbela.objects.ShipObject;

public class GameScreen extends ScreenAdapter {
    Main mainClass;
    ShipObject ship;

    public GameScreen(Main mainClass) {
        this.mainClass = mainClass;

        ship = new ShipObject(
            GameResources.SHIP_TEXTURE,
            GameSettings.SCR_WIDTH / 2,150,
            GameSettings.SHIP_WIDTH,
            GameSettings.SHIP_HEIGHT,
            mainClass.world);
    }

    @Override
    public void render(float delta) {
        mainClass.stepWorld();
        handleInput();
        draw();
    }

    private void draw() {
        mainClass.camera.update();
        mainClass.batch.setProjectionMatrix(mainClass.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        mainClass.batch.begin();
        ship.draw(mainClass.batch);
        mainClass.batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            mainClass.touch = mainClass.camera.unproject(
                new Vector3(Gdx.input.getX(),
                    Gdx.input.getY(), 0)
            );
            ship.move(mainClass.touch);
        }
    }

}
