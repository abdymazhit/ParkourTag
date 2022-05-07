package me.abdymazhit.custom;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team
 */
public class Team {

    /**
     * Represents the team name
     */
    private final String name;
    /**
     * Represents the team color
     */
    private final String color;
    /**
     * Represents a list of team players
     */
    private final List<Player> players;

    /**
     * Creates a team with the given parameters
     * @param name Team name
     * @param color Team color
     */
    public Team(String name, String color) {
        this.name = name;
        this.color = color;
        players = new ArrayList<>();
    }

    /**
     * Gets the team name
     * @return the team name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the team color
     * @return the team color
     */
    public String getColor() {
        return color;
    }

    /**
     * Adds a player to the team
     * @param player Player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Gets a list of team players
     * @return a list of team players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Removes a player from the team
     * @param player Player
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }
}
