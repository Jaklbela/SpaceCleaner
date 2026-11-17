package io.github.jaklbela.objects;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import io.github.jaklbela.GameSettings;

public class GameObject {
    public int width;
    public int height;
    public Body body;
    protected Texture texture;

    protected GameObject(String texturePath, int x, int y,
                         int width, int height, World world) {
        this.width = width;
        this.height = height;
        texture = new Texture(texturePath);
        body = createBody(x, y, world);
    }

    public void draw (SpriteBatch batch) {
    }

    public int getX() {
        return (int) (body.getPosition().x / GameSettings.SCALE);
    }

    public int getY() {
        return (int) (body.getPosition().y / GameSettings.SCALE);
    }

    public void setX(int x) {
        body.setTransform(x * GameSettings.SCALE, body.getPosition().y, 0);
    }

    public void setY(int y) {
        body.setTransform(body.getPosition().x,  y * GameSettings.SCALE, 0);
    }

    private Body createBody(float x, float y, World world) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(Math.max(width, height) * GameSettings.SCALE / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 1f;

        body.createFixture(fixtureDef);
        circleShape.dispose();

        body.setTransform(x * GameSettings.SCALE, y * GameSettings.SCALE, 0);
        return body;
    }

}
