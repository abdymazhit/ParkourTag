package me.abdymazhit.parkourtag.custom;

import me.abdymazhit.parkourtag.scoreboard.EndBoard;
import me.abdymazhit.parkourtag.scoreboard.GameBoard;
import me.abdymazhit.parkourtag.scoreboard.TeamBoard;
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
     * Represents the scoreboard of the team
     */
    private TeamBoard teamBoard;
    /**
     * Represents team coins
     */
    private int coins;

    /**
     * Creates a team with the given parameters
     * @param name Team name
     * @param color Team color
     */
    public Team(String name, String color) {
        this.name = name;
        this.color = color;
        players = new ArrayList<>();
        coins = 0;
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
     * Gets the display name of the team
     * @return the display name of the team
     */
    public String getDisplayName() {
        return color + name;
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

    /**
     * Sets the scoreboard of the game stage GAME
     */
    public void setGameTeamBoard() {
        teamBoard = new GameBoard(this);
    }

    /**
     * Sets the scoreboard of the game stage ENDING
     */
    public void setEndTeamBoard() {
        teamBoard = new EndBoard(this);
    }

    /**
     * Gets the scoreboard of the team
     * @return the scoreboard of the team
     */
    public TeamBoard getTeamBoard() {
        return teamBoard;
    }

    /**
     * Adds team coins
     * @param coins team coins
     */
    public void addCoins(int coins) {
        this.coins += coins;
    }

    /**
     * Gets team coins
     * @return team coins
     */
    public int getCoins() {
        return coins;
    }
}
