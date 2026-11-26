package io.github.jaklbela;

public class GameSettings {
    public final static int SCR_WIDTH = 720;
    public final static int SCR_HEIGHT = 1280;

    public final static float STEP_TIME = 1/60f;
    public final static int VELOCITY_ITERATIONS = 6;
    public final static int POSITION_ITERATIONS = 6;
    public final static float SCALE = 0.05f;

    public final static int SHIP_WIDTH = 200;
    public final static int SHIP_HEIGHT = 150;
    public final static float SHIP_FORCE_RATIO  = 10;

    public final static float TRASH_VELOCITY = 20;
    public final static long TRASH_SPAWN_COOLDOWN = 2000;
    public final static int TRASH_WIDTH = 140;
    public final static int TRASH_HEIGHT = 100;

    public final static int BULLET_VELOCITY = 200;
    public final static int BULLET_COOLDOWN = 1000;
    public final static int BULLET_WIDTH = 15;
    public final static int BULLET_HEIGHT = 45;

    public final static short SHIP_BIT = 2;
    public final static short TRASH_BIT = 4;
    public final static short BULLET_BIT = 8;

}
