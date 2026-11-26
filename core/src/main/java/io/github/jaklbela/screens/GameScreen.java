package io.github.jaklbela.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import io.github.jaklbela.GameResources;
import io.github.jaklbela.GameSession;
import io.github.jaklbela.GameSettings;
import io.github.jaklbela.Main;
import io.github.jaklbela.objects.BulletObject;
import io.github.jaklbela.objects.ShipObject;
import io.github.jaklbela.objects.TrashObject;

public class GameScreen extends ScreenAdapter {
    Main mainClass;
    ShipObject ship;
    GameSession gameSession;
    ArrayList<TrashObject> trashArray;
    ArrayList<BulletObject> bulletArray;

    public GameScreen(Main mainClass) {
        this.mainClass = mainClass;
        gameSession = new GameSession();

        trashArray = new ArrayList<>();
        bulletArray = new ArrayList<>();

        ship = new ShipObject(
            GameResources.SHIP_TEXTURE,
            GameSettings.SCR_WIDTH / 2,150,
            GameSettings.SHIP_WIDTH,
            GameSettings.SHIP_HEIGHT,
            mainClass.world);
    }

    @Override
    public void show() {
        gameSession.startGame();
    }

    @Override
    public void render(float delta) {
        mainClass.stepWorld();
        handleInput();

        if (gameSession.shouldSpawnTrash()) {
            TrashObject trashObject = new TrashObject(
                GameResources.TRASH_TEXTURE,
                GameSettings.TRASH_WIDTH, GameSettings.TRASH_HEIGHT,
                mainClass.world);
            trashArray.add(trashObject);
        }

        if (ship.needToShoot()) {
            BulletObject bullet = new BulletObject(
                GameResources.BULLET_TEXTURE,
                ship.getX(), ship.getY() + ship.height / 2,
                GameSettings.BULLET_WIDTH,
                GameSettings.BULLET_HEIGHT,
                mainClass.world);
            bulletArray.add(bullet);
        }

        updateTrash();
        updateBullet();

        draw();
    }

    private void draw() {
        mainClass.camera.update();
        mainClass.batch.setProjectionMatrix(mainClass.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        mainClass.batch.begin();
        ship.draw(mainClass.batch);
        for (TrashObject trash : trashArray) trash.draw(mainClass.batch);
        for (BulletObject bullet : bulletArray) bullet.draw(mainClass.batch);
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

    private void updateTrash() {
        for (int i = 0; i < trashArray.size(); i++) {
            if (!trashArray.get(i).isInFrame()) {
                mainClass.world.destroyBody(trashArray.get(i).body);
                trashArray.remove(i--);
            }
        }
    }

    private void updateBullet() {
        for (int i = 0; i < bulletArray.size(); i++) {
            if (bulletArray.get(i).hasToBeDestroyed()) {
                mainClass.world.destroyBody(bulletArray.get(i).body);
                bulletArray.remove(i--);
            }
        }
    }

}
