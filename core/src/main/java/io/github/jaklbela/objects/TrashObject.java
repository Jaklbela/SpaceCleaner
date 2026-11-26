package io.github.jaklbela.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import io.github.jaklbela.GameSettings;

public class TrashObject extends GameObject {
    private static final int paddingHorizontal = 30;
    boolean wasHit;

    public TrashObject(String texturePath, int width, int height, World world) {
        super(texturePath,
            width / 2 + paddingHorizontal + (
                new Random().nextInt(GameSettings.SCR_WIDTH - 2 * paddingHorizontal - width)),
            GameSettings.SCR_HEIGHT + height / 2,
            width, height, GameSettings.SHIP_BIT, world);

        body.setLinearVelocity(new Vector2(0, -GameSettings.TRASH_VELOCITY));
    }

    public boolean isInFrame() {
        return getY() + height / 2 > 0;
    }

    @Override
    public void hit() {
        wasHit = true;
    }
}
