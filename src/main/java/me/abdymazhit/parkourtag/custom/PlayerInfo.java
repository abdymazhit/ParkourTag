package me.abdymazhit.parkourtag.custom;

import org.bukkit.entity.Player;

/**
 * Represents information about a player
 */
public class PlayerInfo {

    /**
     * Represents a player
     */
    private final Player player;
    /**
     * Represents a player's role
     */
    private Role role;
    /**
     * Represents the number of player coins earned
     */
    private int coins;

    /**
     * Creates information about a player
     * @param player Player
     * @param role Player role
     */
    public PlayerInfo(Player player, Role role) {
        this.player = player;
        this.role = role;
        coins = 0;
    }

    /**
     * Gets a player
     * @return a player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player's role
     * @param role Player's role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets a player's role
     * @return a player's role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Adds the number of player coins earned
     * @param coins Number of coins
     */
    public void addCoins(int coins) {
        this.coins += coins;
    }

    /**
     * Gets the number of player coins earned
     * @return the number of player coins earned
     */
    public int getCoins() {
        return coins;
    }
}
