package me.abdymazhit.parkourtag.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Represents the scoreboard of the game
 */
public class Board {

    /**
     * Represents the scoreboard
     */
    public Scoreboard scoreboard;

    /**
     * Sets the scoreboard for the player
     * @param player Player
     */
    public void setScoreboard(Player player) {
        player.setScoreboard(scoreboard);
    }
}
