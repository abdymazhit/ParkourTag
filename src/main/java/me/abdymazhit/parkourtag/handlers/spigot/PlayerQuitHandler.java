package me.abdymazhit.parkourtag.handlers.spigot;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.custom.Team;
import me.abdymazhit.parkourtag.events.GamePlayerRemoveEvent;
import me.abdymazhit.parkourtag.events.PlayerRemoveEvent;
import me.abdymazhit.parkourtag.events.SpectatorRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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

        Event gameEvent = null;
        if(GameManager.getWaitingGamePlayers().contains(player)) {
            gameEvent = new PlayerRemoveEvent(player);
        } else if(GameManager.getSpectators().contains(player)) {
            gameEvent = new SpectatorRemoveEvent(player);
        } else {
            for(Team team : Config.getTeams()) {
                if(team.getPlayers().contains(player)) {
                    gameEvent = new GamePlayerRemoveEvent(player, team);
                    break;
                }
            }
        }

        if(gameEvent != null) {
            Bukkit.getPluginManager().callEvent(gameEvent);
        }
    }
}
