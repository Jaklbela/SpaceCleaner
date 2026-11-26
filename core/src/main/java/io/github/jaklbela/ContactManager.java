package io.github.jaklbela;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import io.github.jaklbela.objects.GameObject;

public class ContactManager {

    World world;

    public ContactManager(World world) {
        this.world = world;

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                int cDefA = fixA.getFilterData().categoryBits;
                int cDefB = fixB.getFilterData().categoryBits;

                if (cDefA == GameSettings.TRASH_BIT && cDefB == GameSettings.BULLET_BIT ||
                 cDefB == GameSettings.TRASH_BIT && cDefA == GameSettings.BULLET_BIT ||
                 cDefA == GameSettings.TRASH_BIT && cDefB == GameSettings.SHIP_BIT ||
                cDefB == GameSettings.TRASH_BIT && cDefA == GameSettings.SHIP_BIT) {

                    ((GameObject) fixA.getUserData()).hit();
                    ((GameObject) fixB.getUserData()).hit();
                }
            }

            @Override
            public void endContact(Contact contact) {}

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}
        });
    }
}
