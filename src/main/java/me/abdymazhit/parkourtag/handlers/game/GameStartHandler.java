package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import me.abdymazhit.parkourtag.custom.GameState;
import me.abdymazhit.parkourtag.events.GameStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Represents a GameStartEvent event handler
 */
public class GameStartHandler implements Listener {

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        GameManager.setGameState(GameState.STARTING);

        GameManager.setTask(new BukkitRunnable() {
            int time = Config.getGameStartTime();

            @Override
            public void run() {
                GameManager.getLobbyBoard().setStartingGameStatus(time);

                time--;
                if(time <= 0) {
                    GameManager.clearWaitingGamePlayersList();
                    GameManager.startNextRound();
                }
            }
        }.runTaskTimer(ParkourTag.getInstance(), 20L, 0));
    }
}
