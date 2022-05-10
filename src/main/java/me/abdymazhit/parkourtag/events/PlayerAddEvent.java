package me.abdymazhit.parkourtag.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;

/**
 * Called when a player is added as the waiting game player
 */
public class PlayerAddEvent extends Event {

    /**
     * Represents a list of handlers
     */
    private static final HandlerList handlers = new HandlerList();
    /**
     * Represents the waiting game player
     */
    private final Player player;

    /**
     * @param player Waiting game player
     */
    public PlayerAddEvent(Player player) {
        this.player = player;
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
     * Gets the waiting game player
     * @return the waiting game player
     */
    public Player getPlayer() {
        return player;
    }
}
