package io.github.jaklbela.objects;

import com.badlogic.gdx.physics.box2d.World;

public class ShipObject extends GameObject {
    protected ShipObject(String texturePath, int x, int y, int width, int height, World world) {
        super(texturePath, x, y, width, height, world);
    }
}
