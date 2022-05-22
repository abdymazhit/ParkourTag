package me.abdymazhit.parkourtag;

import me.abdymazhit.parkourtag.custom.GameState;
import me.abdymazhit.parkourtag.custom.Match;
import me.abdymazhit.parkourtag.custom.Round;
import me.abdymazhit.parkourtag.custom.Team;
import me.abdymazhit.parkourtag.events.GameStartEvent;
import me.abdymazhit.parkourtag.scoreboard.LobbyBoard;
import org.bukkit.Bukkit;
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
     * Represents a game round
     */
    private static Round round;
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
                team.getTeamBoard().setTeamScoreboard();
            }
        } else if(gameState.equals(GameState.ENDING)) {
            for(Team team : Config.getTeams()) {
                team.setEndTeamBoard();
                team.getTeamBoard().setTeamScoreboard();
            }
        }
    }

    /**
     * Starts the next round of the game
     */
    public static void startNextRound() {
        Round.number++;
        round = new Round(organizeMatches());
    }

    /**
     * Organize matches between teams
     * @return Matches between teams
     */
    private static List<Match> organizeMatches() {
        List<Team> teams = new ArrayList<>(Config.getTeams());
        if(teams.size() % 2 == 1) {
            teams.add(null);
        }

        List<Match> matches = new ArrayList<>();
        while(teams.size() != 0) {
            Team firstTeam = teams.get(0);
            Team secondTeam = teams.get(teams.size() - 1);
            if(firstTeam != null && secondTeam != null) {
                matches.add(new Match(firstTeam, secondTeam));
            }

            teams.remove(0);
            teams.remove(teams.size() - 1);
        }

        // rotate a list of game teams for next time
        Config.rotateTeamsList();

        return matches;
    }

    /**
     * Gets the game state
     * @return the game state
     */
    public static GameState getGameState() {
        return gameState;
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

        if(waitingGamePlayers.size() == Config.getMaxPlayerCount()) {
            GameStartEvent event = new GameStartEvent();
            Bukkit.getPluginManager().callEvent(event);
        }
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
     * Clears a list of waiting game players
     */
    public static void clearWaitingGamePlayersList() {
        waitingGamePlayers.clear();
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
     * Gets a game round
     * @return a game round
     */
    public static Round getRound() {
        return round;
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
