package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.events.PlayerAddEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Represents a PlayerAddEvent event handler
 */
public class PlayerAddHandler implements Listener {

    @EventHandler
    public void onPlayerAdd(PlayerAddEvent event) {
        Player player = event.getPlayer();

        player.teleport(Config.getLobbyLocation());

        GameManager.addWaitingGamePlayer(player);
    }
}
