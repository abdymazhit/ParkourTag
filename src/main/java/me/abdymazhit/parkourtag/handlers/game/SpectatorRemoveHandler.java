package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.events.SpectatorRemoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Represents a SpectatorRemoveEvent event handler
 */
public class SpectatorRemoveHandler implements Listener {

    @EventHandler
    public void onSpectatorRemove(SpectatorRemoveEvent event) {
        Player player = event.getPlayer();
        GameManager.removeSpectator(player);
    }
}
