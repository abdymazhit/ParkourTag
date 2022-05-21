package me.abdymazhit.parkourtag.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;

/**
 * Called when the game starts
 */
public class GameStartEvent extends Event {

    /**
     * Represents a list of handlers
     */
    private static final HandlerList handlers = new HandlerList();

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
}
