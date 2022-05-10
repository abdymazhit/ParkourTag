package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.custom.GameState;
import me.abdymazhit.parkourtag.events.PlayerRemoveEvent;
import me.abdymazhit.parkourtag.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Represents a PlayerRemoveEvent event handler
 */
public class PlayerRemoveHandler implements Listener {

    @EventHandler
    public void onPlayerRemove(PlayerRemoveEvent event) {
        Player player = event.getPlayer();

        if(GameManager.getGameState().equals(GameState.STARTING)) {
            GameManager.getTask().cancel();
            GameManager.setGameState(GameState.WAITING);
            GameManager.getLobbyBoard().setWaitingGameStatus();
        }

        GameManager.removeWaitingGamePlayer(player);
    }
}
