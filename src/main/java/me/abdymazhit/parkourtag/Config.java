package me.abdymazhit.parkourtag;

import me.abdymazhit.parkourtag.custom.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

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
     * Represents the world in which the game will take place
     */
    private static World world;
    /**
     * Represents the location where players will spawn at the start of the game to wait for other players
     */
    private static Location lobbyLocation;
    /**
     * Represents the location of the first team, where the teams must meet eyes and choose hunters
     */
    private static Location firstTeamLocation;
    /**
     * Represents the location of the second team, where the teams must meet eyes and choose hunters
     */
    private static Location secondTeamLocation;
    /**
     * Represents the location where the hunter will spawn
     */
    private static Location hunterLocation;
    /**
     * Represents the location where runners will spawn
     */
    private static Location runnersLocation;
    /**
     * Represents a list of game teams
     */
    private static List<Team> teams;
    /**
     * Represents the maximum count of players
     */
    private static int maxPlayerCount;

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

        world = Bukkit.getWorld(Objects.requireNonNull(config.getString("world")));
        lobbyLocation = getLocation(config.getString("lobbyLoc"));
        firstTeamLocation = getLocation(config.getString("firstTeamLoc"));
        secondTeamLocation = getLocation(config.getString("secondTeamLoc"));
        hunterLocation = getLocation(config.getString("hunterLoc"));
        runnersLocation = getLocation(config.getString("runnersLoc"));

        teams = new ArrayList<>();
        for(Map<?, ?> team : config.getMapList("teams")) {
            String name = (String) team.get("name");
            String color = (String) team.get("color");
            teams.add(new Team(name, color));
        }

        maxPlayerCount = teams.size() * playersInTeam;

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

        if(world == null) {
            throw new IllegalArgumentException("The world in which the game will take place must be correct");
        }

        if(lobbyLocation == null) {
            throw new IllegalArgumentException("The location where players will spawn at the start of the game to wait for other players must be correct");
        }

        if(firstTeamLocation == null) {
            throw new IllegalArgumentException("The location of the first team, where the teams must meet eyes and choose hunters must be correct");
        }

        if(secondTeamLocation == null) {
            throw new IllegalArgumentException("The location of the second team, where the teams must meet eyes and choose hunters must be correct");
        }

        if(hunterLocation == null) {
            throw new IllegalArgumentException("The location where the hunter will spawn must be correct");
        }

        if(runnersLocation == null) {
            throw new IllegalArgumentException("The location where runners will spawn must be correct");
        }

        if(teams.size() < 2) {
            throw new IllegalArgumentException("The number of teams must be greater than or equal to 2");
        }
    }

    /**
     * Gets the location from the config file object
     * @param object Config file object
     * @return the location
     */
    private static Location getLocation(Object object) {
        String stringLocation = (String) object;
        String[] locArgs = stringLocation.split(",");

        if(locArgs.length < 3 || locArgs.length == 4 || locArgs.length > 5) {
            return null;
        }

        double x = Double.parseDouble(locArgs[0]);
        double y = Double.parseDouble(locArgs[1]);
        double z = Double.parseDouble(locArgs[2]);

        if (locArgs.length == 5) {
            float yaw = Float.parseFloat(locArgs[3]);
            float pitch = Float.parseFloat(locArgs[4]);
            return new Location(world, x, y, z, yaw, pitch);
        }

        return new Location(world, x, y, z);
    }

    /**
     * Rotates a list of game teams for a round-robin tournament system.
     */
    public static void rotateTeamsList() {
        if(!teams.isEmpty()) {
            Team firstElement = teams.get(0);
            Team lastElement = teams.get(teams.size() - 1);

            List<Team> rotatedArray = new ArrayList<>(Arrays.asList(firstElement, lastElement));
            for(int i = 1; i < teams.size() - 1; i++) {
                rotatedArray.add(teams.get(i));
            }
            teams = rotatedArray;
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
     * Gets the world in which the game will take place
     * @return the world in which the game will take place
     */
    public static World getWorld() {
        return world;
    }

    /**
     * Gets the location where players will spawn at the start of the game to wait for other players
     * @return the location where players will spawn at the start of the game to wait for other players
     */
    public static Location getLobbyLocation() {
        return lobbyLocation;
    }

    /**
     * Gets the location of the first team, where the teams must meet eyes and choose hunters
     * @return the location of the first team, where the teams must meet eyes and choose hunters
     */
    public static Location getFirstTeamLocation() {
        return firstTeamLocation;
    }

    /**
     * Gets the location of the second team, where the teams must meet eyes and choose hunters
     * @return the location of the second team, where the teams must meet eyes and choose hunters
     */
    public static Location getSecondTeamLocation() {
        return secondTeamLocation;
    }

    /**
     * Gets the location where the hunter will spawn
     * @return the location where the hunter will spawn
     */
    public static Location getHunterLocation() {
        return hunterLocation;
    }

    /**
     * Gets the location where runners will spawn
     * @return the location where runners will spawn
     */
    public static Location getRunnersLocation() {
        return runnersLocation;
    }

    /**
     * Gets a list of game teams
     * @return a list of game teams
     */
    public static List<Team> getTeams() {
        return teams;
    }

    /**
     * Gets the maximum count of players
     * @return the maximum count of players
     */
    public static int getMaxPlayerCount() {
        return maxPlayerCount;
    }
}
