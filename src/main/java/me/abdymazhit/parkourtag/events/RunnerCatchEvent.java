package me.abdymazhit.parkourtag.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;

/**
 * Called when a hunter catches a runner
 */
public class RunnerCatchEvent extends Event {

    /**
     * Represents a list of handlers
     */
    private static final HandlerList handlers = new HandlerList();
    /**
     * Represents a runner
     */
    private final Player runner;
    /**
     * Represents the hunter
     */
    private final Player hunter;

    /**
     * @param runner Runner
     * @param hunter Hunter
     */
    public RunnerCatchEvent(Player runner, Player hunter) {
        this.runner = runner;
        this.hunter = hunter;
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
     * Gets a runner
     * @return a runner
     */
    public Player getRunner() {
        return runner;
    }

    /**
     * Gets the hunter
     * @return the hunter
     */
    public Player getHunter() {
        return hunter;
    }
}
