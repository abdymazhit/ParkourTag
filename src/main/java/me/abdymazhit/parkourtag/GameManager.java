package me.abdymazhit.parkourtag;

import me.abdymazhit.custom.GameState;
import me.abdymazhit.custom.Team;
import me.abdymazhit.custom.TeamConfig;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the game manager
 */
public class GameManager {

    /**
     * Represents the game state
     */
    private GameState gameState;
    /**
     * Represents the game round number
     */
    private int round;
    /**
     * Represents a list of waiting game players
     */
    private final List<Player> waitingGamePlayers;
    /**
     * Represents a list of spectators
     */
    private final List<Player> spectators;
    /**
     * Represents a team configurations
     */
    private final Map<Team, TeamConfig> teamConfigs;

    /**
     * Creates a game manager
     */
    public GameManager() {
        gameState = GameState.WAITING;
        round = 0;
        waitingGamePlayers = new ArrayList<>();
        spectators = new ArrayList<>();
        teamConfigs = new HashMap<>();
    }

    /**
     * Sets the game state
     * @param gameState the game state
     */
    public void setGameState(GameState gameState) {
        if(gameState.equals(GameState.GAME)) {
            waitingGamePlayers.clear();
        }
        this.gameState = gameState;
    }

    /**
     * Starts the next round of the game
     */
    public void startNextRound() {
        round++;
        for(TeamConfig teamConfig : teamConfigs.values()) {
            teamConfig.clearPlayerRoles();
        }
    }

    /**
     * Gets the game state
     * @return the game state
     */
    public GameState getGameState() {
        return gameState;
    }


    /**
     * Gets the game round number
     * @return the game round number
     */
    public int getRound() {
        return round;
    }

    /**
     * Gets a list of waiting game players
     * @return a list of waiting game players
     */
    public List<Player> getWaitingGamePlayers() {
        return waitingGamePlayers;
    }

    /**
     * Gets a list of spectators
     * @return a list of spectators
     */
    public List<Player> getSpectators() {
        return spectators;
    }

    /**
     * Gets a team configurations
     * @return a team configurations
     */
    public Map<Team, TeamConfig> getTeamConfigs() {
        return teamConfigs;
    }
}
