package me.abdymazhit.parkourtag.handlers.spigot;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.custom.Team;
import me.abdymazhit.parkourtag.events.SpectatorAddEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

/**
 * Represents a PlayerJoinEvent event handler
 */
public class PlayerJoinHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setGameMode(GameMode.ADVENTURE);
        player.setFireTicks(0);
        player.setNoDamageTicks(200);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(10);
        player.setFlySpeed(0.1f);
        player.setLevel(0);
        player.setExp(0);

        for(PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }

        player.getInventory().clear();
        player.closeInventory();

        Event gameEvent;
        switch(GameManager.getGameState()) {
            case STARTING:
                gameEvent = new SpectatorAddEvent(player);
                Bukkit.getPluginManager().callEvent(gameEvent);
                break;
            case GAME:
            case ENDING:
                boolean isGamePlayer = false;
                for(Team team : Config.getTeams()) {
                    if(team.getPlayers().contains(player)) {
                        isGamePlayer = true;
                        break;
                    }
                }

                if(!isGamePlayer) {
                    gameEvent = new SpectatorAddEvent(player);
                    Bukkit.getPluginManager().callEvent(gameEvent);
                }
                break;
        }
    }
}
