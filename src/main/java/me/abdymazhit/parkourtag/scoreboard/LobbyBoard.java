package me.abdymazhit.parkourtag.scoreboard;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the scoreboard of the game stage WAITING, STARTING
 */
public class LobbyBoard extends Board {

    /**
     * Creates the scoreboard
     */
    public LobbyBoard() {
        ScoreboardManager scoreboardManager = ParkourTag.getInstance().getServer().getScoreboardManager();
        if(scoreboardManager == null) {
            throw new IllegalArgumentException("Error getting server scoreboard manager");
        }

        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("ParkourTag", "dummy", "LobbyBoard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§e§lParkour Tag");

        Team statusTeam = scoreboard.registerNewTeam("status");
        statusTeam.addEntry("§l");
        Team playersTeam = scoreboard.registerNewTeam("players");
        playersTeam.addEntry("§e§lPlayers: §r");

        List<Score> scores = new ArrayList<>();
        scores.add(objective.getScore("§b§lMap: §r" + Config.getMapName()));
        scores.add(objective.getScore(""));
        scores.add(objective.getScore("§e§lPlayers: §r"));
        scores.add(objective.getScore("§l"));
        for(int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            score.setScore(i);
        }
    }

    /**
     * Updates the counter for the number of game waiting players
     */
    public void updatePlayersCount() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            Team team = p.getScoreboard().getTeam("players");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'players' team");
            }

            team.setSuffix(GameManager.getWaitingGamePlayers().size() + "/" + Config.getMaxPlayerCount());
        }
    }

    /**
     * Sets the scoreboard of the game status to WAITING
     */
    public void setWaitingGameStatus() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            Team team = p.getScoreboard().getTeam("status");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'status' team");
            }

            team.setSuffix("Waiting...");
        }
    }

    /**
     * Sets the player's scoreboard of the game status to WAITING
     * @param player Player
     */
    public void setWaitingGameStatus(Player player) {
        Team team = player.getScoreboard().getTeam("status");
        if(team == null) {
            throw new IllegalArgumentException("Error getting player's scoreboard 'status' team");
        }

        team.setSuffix("Waiting...");
    }

    /**
     * Sets the scoreboard of the game status to WAITING
     * @param time Time until the game start
     */
    public void setStartingGameStatus(int time) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            Team team = p.getScoreboard().getTeam("status");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'status' team");
            }

            team.setSuffix("§cGame begins: §r" + time);
        }
    }
}
