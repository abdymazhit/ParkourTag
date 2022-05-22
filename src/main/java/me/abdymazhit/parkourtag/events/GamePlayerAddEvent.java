package me.abdymazhit.parkourtag.events;

import me.abdymazhit.parkourtag.custom.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;

/**
 * Called when the game player is added
 */
public class GamePlayerAddEvent extends Event {

    /**
     * Represents a list of handlers
     */
    private static final HandlerList handlers = new HandlerList();
    /**
     * Represents the game player
     */
    private final Player player;
    /**
     * Represents the player's team
     */
    private final Team team;

    /**
     * @param player Game player
     * @param team Player's team
     */
    public GamePlayerAddEvent(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    /**
     * Gets a list of handlers
     * @return a list of handlers
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets a list of handlers
     * @return a list of handlers
     */
    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Gets the game player
     * @return the game player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the player's team
     * @return the player's team
     */
    public Team getTeam() {
        return team;
    }
}
