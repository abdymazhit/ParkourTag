package me.abdymazhit.custom;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team configuration
 */
public class TeamConfig {

    /**
     * Represents the hunter
     */
    private Player hunter;
    /**
     * Represents a list of runners
     */
    private List<Player> runners;
    /**
     * Represents a list of caught runners
     */
    private final List<Player> caughtRunners;

    /**
     * Creates a team configuration
     */
    public TeamConfig() {
        runners = new ArrayList<>();
        caughtRunners = new ArrayList<>();
    }

    /**
     * Sets the roles of team players
     * @param hunter Hunter
     * @param runners List of runners
     */
    public void setPlayerRoles(Player hunter, List<Player> runners) {
        this.hunter = hunter;
        this.runners = runners;
    }

    /**
     * Sets the role of the caught runner
     * @param player Caught runner
     */
    public void setCaughtRunnerRole(Player player) {
        runners.remove(player);
        caughtRunners.add(player);
    }

    /**
     * Clears team player roles
     */
    public void clearPlayerRoles() {
        hunter = null;
        runners.clear();
        caughtRunners.clear();
    }

    /**
     * Gets a hunter
     * @return a hunter
     */
    public Player getHunter() {
        return hunter;
    }

    /**
     * Gets a list of runners
     * @return a list of runners
     */
    public List<Player> getRunners() {
        return runners;
    }

    /**
     * Gets a list of caught runners
     * @return a list of caught runners
     */
    public List<Player> getCaughtRunners() {
        return caughtRunners;
    }
}
