package me.abdymazhit.parkourtag.scoreboard;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.ParkourTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the scoreboard of the game stage ENDING
 */
public class EndBoard extends TeamBoard {

    /**
     * Creates the scoreboard
     * @param team Team
     */
    public EndBoard(me.abdymazhit.parkourtag.custom.Team team) {
        super(team);

        ScoreboardManager scoreboardManager = ParkourTag.getInstance().getServer().getScoreboardManager();
        if(scoreboardManager == null) {
            throw new IllegalArgumentException("Error getting server scoreboard manager");
        }

        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("ParkourTag", "dummy", "EndBoard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§e§lParkour Tag");

        Team statusTeam = scoreboard.registerNewTeam("status");
        statusTeam.addEntry("§c§lGame Ends§r: ");

        if(Config.getTeams().size() >= 4) {
            Team firstTeam = scoreboard.registerNewTeam("first");
            firstTeam.addEntry("§r§r§r§r");
            Team secondTeam = scoreboard.registerNewTeam("second");
            secondTeam.addEntry("§r§r§r");
            Team thirdTeam = scoreboard.registerNewTeam("third");
            thirdTeam.addEntry("§r§r");
            Team forthTeam = scoreboard.registerNewTeam("forth");
            forthTeam.addEntry("§r");
        } else if(Config.getTeams().size() == 3) {
            Team firstTeam = scoreboard.registerNewTeam("first");
            firstTeam.addEntry("§r§r§r");
            Team secondTeam = scoreboard.registerNewTeam("second");
            secondTeam.addEntry("§r§r");
            Team thirdTeam = scoreboard.registerNewTeam("third");
            thirdTeam.addEntry("§r");
        } else if(Config.getTeams().size() == 2) {
            Team firstTeam = scoreboard.registerNewTeam("first");
            firstTeam.addEntry("§r§r");
            Team secondTeam = scoreboard.registerNewTeam("second");
            secondTeam.addEntry("§r");
        }

        List<Score> scores = new ArrayList<>();
        scores.add(objective.getScore("§b§lMap§r: " + Config.getMapName()));
        scores.add(objective.getScore("§c§lGame Ends§r: "));
        scores.add(objective.getScore(" "));
        scores.add(objective.getScore("§b§lGame Coins§r:"));

        if(Config.getTeams().size() >= 4) {
            scores.add(objective.getScore("§r§r§r§r"));
            scores.add(objective.getScore("§r§r§r"));
            scores.add(objective.getScore("§r§r"));
            scores.add(objective.getScore("§r"));
        } else if(Config.getTeams().size() == 3) {
            scores.add(objective.getScore("§r§r§r"));
            scores.add(objective.getScore("§r§r"));
            scores.add(objective.getScore("§r"));
        } else if(Config.getTeams().size() == 2) {
            scores.add(objective.getScore("§r§r"));
            scores.add(objective.getScore("§r"));
        }

        for(int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            score.setScore(i);
        }
    }

    /**
     * Updates the game end counter
     * @param time Time until the game end
     */
    public void updateGameEndCounter(int time) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            Team team = p.getScoreboard().getTeam("status");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'status' team");
            }

            team.setSuffix(String.valueOf(time));
        }
    }

    /**
     * Sets the positions of the top teams
     * @param teams Sorted list of top teams
     */
    public void setTopTeamsPositions(List<me.abdymazhit.parkourtag.custom.Team> teams) {
        if(teams.size() > 3) {
            for(Player p : team.getPlayers()) {
                Team forthTeam = p.getScoreboard().getTeam("forth");
                if(forthTeam == null) {
                    throw new IllegalArgumentException("Error getting player's scoreboard 'forth' team");
                }
                forthTeam.setSuffix("4. " + teams.get(3).getDisplayName() + " " + teams.get(3).getCoins());
            }
        }

        if(teams.size() > 2) {
            for(Player p : team.getPlayers()) {
                Team thirdTeam = p.getScoreboard().getTeam("third");
                if(thirdTeam == null) {
                    throw new IllegalArgumentException("Error getting player's scoreboard 'third' team");
                }
                thirdTeam.setSuffix("3. " + teams.get(2).getDisplayName() + " " + teams.get(2).getCoins());
            }
        }

        for(Player p : team.getPlayers()) {
            Team secondTeam = p.getScoreboard().getTeam("second");
            if(secondTeam == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'second' team");
            }
            secondTeam.setSuffix("2. " + teams.get(1).getDisplayName() + " " + teams.get(1).getCoins());
        }

        for(Player p : team.getPlayers()) {
            Team firstTeam = p.getScoreboard().getTeam("first");
            if(firstTeam == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'first' team");
            }
            firstTeam.setSuffix("1. " + teams.get(0).getDisplayName() + " " + teams.get(0).getCoins());
        }
    }
}
