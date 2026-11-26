package io.github.jaklbela.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import io.github.jaklbela.GameSettings;

public class BulletObject extends GameObject {
    public boolean wasHit;

    public BulletObject(String texturePath, int x, int y, int width, int height, World world) {
        super(texturePath, x, y, width, height, GameSettings.BULLET_BIT, world);

        body.setLinearVelocity(new Vector2(0, GameSettings.BULLET_VELOCITY));
        body.setBullet(true);
    }

    public boolean hasToBeDestroyed() {
        return getY() - height / 2 > GameSettings.SCR_HEIGHT;
    }

    @Override
    public void hit() {
        wasHit = true;
    }
}
