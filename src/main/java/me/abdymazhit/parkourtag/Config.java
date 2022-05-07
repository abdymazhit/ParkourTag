package me.abdymazhit.parkourtag;

import me.abdymazhit.custom.Team;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the game configuration
 */
public class Config {

    /**
     * Represents the map name
     */
    private static String mapName;
    /**
     * Represents the number of players on each team
     */
    private static int playersInTeam;
    /**
     * Represents the time until the game starts when there are enough players
     */
    private static int gameStartTime;
    /**
     * Represents the hunter selection time before each round
     */
    private static int hunterSelectionTime;
    /**
     * Represents the time until the round starts
     */
    private static int roundStartTime;
    /**
     * Represents the time of each round
     */
    private static int roundTime;
    /**
     * Represents a list of game teams
     */
    private static List<Team> teams;

    /**
     * Loads game configuration from config file
     * @param plugin JavaPlugin
     */
    public static void load(JavaPlugin plugin) {
        FileConfiguration config = plugin.getConfig();
        plugin.saveDefaultConfig();

        mapName = config.getString("mapName");
        playersInTeam = config.getInt("playersInTeam");

        gameStartTime = config.getInt("gameStartTime");
        hunterSelectionTime = config.getInt("hunterSelectionTime");
        roundStartTime = config.getInt("roundStartTime");
        roundTime = config.getInt("roundTime");

        teams = new ArrayList<>();
        for(Map<?, ?> team : config.getMapList("teams")) {
            String color = (String) team.get("color");
            String name = (String) team.get("name");
            teams.add(new Team(color, name));
        }

        if(playersInTeam < 2) {
            throw new IllegalArgumentException("The number of players in a team must be greater than or equal to 2");
        }

        if(gameStartTime < 1) {
            throw new IllegalArgumentException("The time until the game starts when there are enough players must be greater than or equal to 1");
        }

        if(hunterSelectionTime < 1) {
            throw new IllegalArgumentException("The hunter selection time before each round must be greater than or equal to 1");
        }

        if(roundStartTime < 1) {
            throw new IllegalArgumentException("The time until the round starts must be greater than or equal to 1");
        }

        if(roundTime < 1) {
            throw new IllegalArgumentException("The time of each round must be greater than or equal to 1");
        }

        if(teams.size() < 2) {
            throw new IllegalArgumentException("The number of teams must be greater than or equal to 2");
        }
    }

    /**
     * Gets the map name
     * @return the map name
     */
    public static String getMapName() {
        return mapName;
    }

    /**
     * Gets the number of players on each team
     * @return the number of players on each team
     */
    public static int getPlayersInTeam() {
        return playersInTeam;
    }

    /**
     * Gets the time until the game starts when there are enough players
     * @return the time until the game starts when there are enough players
     */
    public static int getGameStartTime() {
        return gameStartTime;
    }

    /**
     * Gets the hunter selection time before each round
     * @return the hunter selection time before each round
     */
    public static int getHunterSelectionTime() {
        return hunterSelectionTime;
    }

    /**
     * Gets the time until the round starts
     * @return the time until the round starts
     */
    public static int getRoundStartTime() {
        return roundStartTime;
    }

    /**
     * Gets the time of each round
     * @return the time of each round
     */
    public static int getRoundTime() {
        return roundTime;
    }

    /**
     * Gets a list of game teams
     * @return a list of game teams
     */
    public static List<Team> getTeams() {
        return teams;
    }
}
