package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import me.abdymazhit.parkourtag.events.SpectatorAddEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Represents a SpectatorAddHandler event handler
 */
public class SpectatorAddHandler implements Listener {

    @EventHandler
    public void onSpectatorAdd(SpectatorAddEvent event) {
        Player player = event.getPlayer();

        player.setAllowFlight(true);
        player.setFlying(true);

        player.teleport(Config.getLobbyLocation());

        GameManager.addSpectator(player);

        // make the spectator invisible for all players
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(ParkourTag.getInstance(), player);
        }
    }
}
