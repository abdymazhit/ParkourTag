package me.abdymazhit.parkourtag;

import me.abdymazhit.parkourtag.custom.GameState;
import me.abdymazhit.parkourtag.custom.Team;
import me.abdymazhit.parkourtag.scoreboard.LobbyBoard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

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
     * Represents the scoreboard of the game stage WAITING, STARTING
     */
    private static LobbyBoard lobbyBoard;
    /**
     * Represents the game time task
     */
    private static BukkitTask task;

    /**
     * Setups the game manager
     */
    public static void setup() {
        lobbyBoard = new LobbyBoard();
    }

    /**
     * Sets the game state
     * @param gameState Game state
     */
    public static void setGameState(GameState gameState) {
        GameManager.gameState = gameState;
        if(gameState.equals(GameState.GAME)) {
            lobbyBoard = null;
            for(Team team : Config.getTeams()) {
                team.setGameTeamBoard();
            }
        } else if(gameState.equals(GameState.ENDING)) {
            for(Team team : Config.getTeams()) {
                team.setEndTeamBoard();
            }
        }
    }

    /**
     * Starts the next round of the game
     */
    public static void startNextRound() {
        round++;
        for(Team team : Config.getTeams()) {
            team.clearPlayerRoles();
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
     * Adds a player to a list of waiting game players
     * @param player Player
     */
    public static void addWaitingGamePlayer(Player player) {
        removeSpectator(player);
        waitingGamePlayers.add(player);
        lobbyBoard.setScoreboard(player);
        lobbyBoard.updatePlayersCount();
    }

    /**
     * Gets a list of waiting game players
     * @return a list of waiting game players
     */
    public static List<Player> getWaitingGamePlayers() {
        return waitingGamePlayers;
    }

    /**
     * Removes a player from a list of waiting game players
     * @param player Player
     */
    public static void removeWaitingGamePlayer(Player player) {
        waitingGamePlayers.remove(player);
        lobbyBoard.updatePlayersCount();
    }

    /**
     * Adds a player to a list of spectators
     * @param player Player
     */
    public static void addSpectator(Player player) {
        removeWaitingGamePlayer(player);
        spectators.add(player);
    }

    /**
     * Gets a list of spectators
     * @return a list of spectators
     */
    public static List<Player> getSpectators() {
        return spectators;
    }

    /**
     * Removes a player from the list of spectators
     * @param player Player
     */
    public static void removeSpectator(Player player) {
        spectators.remove(player);
    }

    /**
     * Gets the scoreboard of the game stage WAITING, STARTING
     * @return the scoreboard of the game stage WAITING, STARTING
     */
    public static LobbyBoard getLobbyBoard() {
        return lobbyBoard;
    }

    /**
     * Sets the game time task
     * @param task the game time task
     */
    public static void setTask(BukkitTask task) {
        GameManager.task = task;
    }

    /**
     * Gets the game time task
     * @return the game time task
     */
    public static BukkitTask getTask() {
        return task;
    }
}
