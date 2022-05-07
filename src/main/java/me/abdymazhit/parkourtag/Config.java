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

        teams = new ArrayList<>();
        for(Map<?, ?> team : config.getMapList("teams")) {
            String color = (String) team.get("color");
            String name = (String) team.get("name");
            teams.add(new Team(color, name));
        }

        if(playersInTeam < 2) {
            throw new IllegalArgumentException("The number of players in a team must be greater than or equal to 2");
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
     * Gets a list of game teams
     * @return a list of game teams
     */
    public static List<Team> getTeams() {
        return teams;
    }
}
