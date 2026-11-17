package io.github.jaklbela.objects;

import com.badlogic.gdx.physics.box2d.World;

public class BulletObject extends GameObject {
    protected BulletObject(String texturePath, int x, int y, int width, int height, World world) {
        super(texturePath, x, y, width, height, world);
    }
}
