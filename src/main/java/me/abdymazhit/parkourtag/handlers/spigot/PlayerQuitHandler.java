package me.abdymazhit.parkourtag.handlers.spigot;

import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.events.SpectatorRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Represents a PlayerQuitEvent event handler
 */
public class PlayerQuitHandler implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(GameManager.getSpectators().contains(player)) {
            SpectatorRemoveEvent spectatorRemoveEvent = new SpectatorRemoveEvent(player);
            Bukkit.getPluginManager().callEvent(spectatorRemoveEvent);
        }
    }
}
