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
    private static GameState gameState = GameState.WAITING;
    /**
     * Represents the game round number
     */
    private static int round = 0;
    /**
     * Represents a list of waiting game players
     */
    private static final List<Player> waitingGamePlayers = new ArrayList<>();
    /**
     * Represents a list of spectators
     */
    private static final List<Player> spectators = new ArrayList<>();
    /**
     * Represents a teams configuration
     */
    private static final Map<Team, TeamConfig> teamsConfig = new HashMap<>();

    /**
     * Setups teams config
     * @param teams List of teams
     */
    public static void setupTeamsConfig(List<Team> teams) {
        for(Team team : teams) {
            teamsConfig.put(team, null);
        }
    }

    /**
     * Sets the game state
     * @param gameState Game state
     */
    public static void setGameState(GameState gameState) {
        if(gameState.equals(GameState.GAME)) {
            waitingGamePlayers.clear();
        }
        GameManager.gameState = gameState;
    }

    /**
     * Starts the next round of the game
     */
    public static void startNextRound() {
        round++;
        for(TeamConfig teamConfig : teamsConfig.values()) {
            teamConfig.clearPlayerRoles();
        }
    }

    /**
     * Gets the game state
     * @return the game state
     */
    public static GameState getGameState() {
        return gameState;
    }

    /**
     * Gets the game round number
     * @return the game round number
     */
    public static int getRound() {
        return round;
    }

    /**
     * Gets a list of waiting game players
     * @return a list of waiting game players
     */
    public static List<Player> getWaitingGamePlayers() {
        return waitingGamePlayers;
    }

    /**
     * Gets a list of spectators
     * @return a list of spectators
     */
    public static List<Player> getSpectators() {
        return spectators;
    }

    /**
     * Gets a teams configuration
     * @return a teams configuration
     */
    public static Map<Team, TeamConfig> getTeamsConfig() {
        return teamsConfig;
    }
}
