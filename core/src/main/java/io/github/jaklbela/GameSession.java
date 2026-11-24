package io.github.jaklbela;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {
    long sessionStartTime;
    long nextTrashSpawnTime;

    public GameSession() {}

    public void startGame() {
        sessionStartTime = TimeUtils.millis();
        nextTrashSpawnTime = sessionStartTime +
            (long) ((GameSettings.TRASH_SPAWN_COOLDOWN) * getTrashPeriodCoolDown());
    }

    public boolean shouldSpawnTrash() {
        if (nextTrashSpawnTime <= TimeUtils.millis()) {
            nextTrashSpawnTime = TimeUtils.millis() +
                (long) (GameSettings.TRASH_SPAWN_COOLDOWN * getTrashPeriodCoolDown());
            return true;
        }
        return false;
    }

    private float getTrashPeriodCoolDown() {
        return (float) Math.exp(-0.001 * (TimeUtils.millis() - sessionStartTime + 1) / 1000);
    }
}
