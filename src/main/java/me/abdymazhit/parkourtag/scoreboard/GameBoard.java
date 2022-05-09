package me.abdymazhit.parkourtag.scoreboard;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the scoreboard of the game stage GAME
 */
public class GameBoard extends TeamBoard {

    /**
     * Creates the scoreboard
     * @param team Team
     */
    public GameBoard(me.abdymazhit.parkourtag.custom.Team team) {
        super(team);

        ScoreboardManager scoreboardManager = ParkourTag.getInstance().getServer().getScoreboardManager();
        if(scoreboardManager == null) {
            throw new IllegalArgumentException("Error getting server scoreboard manager");
        }

        scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("ParkourTag", "dummy", "GameBoard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§e§lParkour Tag");

        Team roundTeam = scoreboard.registerNewTeam("round");
        roundTeam.addEntry("§a§lRound: §r");
        Team statusTeam = scoreboard.registerNewTeam("status");
        statusTeam.addEntry("§c§l");

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

        Team ourTeam = scoreboard.registerNewTeam("our");
        ourTeam.addEntry("§a§lA§r: ");
        Team enemyTeam = scoreboard.registerNewTeam("enemy");
        enemyTeam.addEntry("§c§lB§r: ");

        List<Score> scores = new ArrayList<>();
        scores.add(objective.getScore("§b§lMap: §r" + Config.getMapName()));
        scores.add(objective.getScore("§a§lRound: §r"));
        scores.add(objective.getScore("§c§l"));
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

        scores.add(objective.getScore(""));
        scores.add(objective.getScore("§b§lRunners alive§r:"));
        scores.add(objective.getScore("§a§lA§r: "));
        scores.add(objective.getScore("§c§lB§r: "));
        for(int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            score.setScore(i);
        }
    }

    /**
     * Updates the round counter
     */
    public void updateRoundCounter() {
        for(Player p : team.getPlayers()) {
            Team team = p.getScoreboard().getTeam("round");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'round' team");
            }

            team.setSuffix(GameManager.getRound() + "/" + (Config.getTeams().size() - 1));
        }
    }

    /**
     * Sets the hunter selection status
     * @param time Time until hunter selection ends
     */
    public void setHunterSelectionStatus(int time) {
        for(Player p : team.getPlayers()) {
            Team team = p.getScoreboard().getTeam("status");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'status' team");
            }

            team.setSuffix("Select Hunter§r: " + time);
        }
    }

    /**
     * Sets the match start status
     * @param time Time until the match starts
     */
    public void setMatchStartStatus(int time) {
        for(Player p : team.getPlayers()) {
            Team team = p.getScoreboard().getTeam("status");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'status' team");
            }

            team.setSuffix("Match starts§r: " + time);
        }
    }

    /**
     * Sets the game status
     * @param time Time until the end of the round
     */
    public void setGameStatus(int time) {
        for(Player p : team.getPlayers()) {
            Team team = p.getScoreboard().getTeam("status");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'status' team");
            }

            team.setSuffix("Time Left§r: " + time);
        }
    }

    /**
     * Updates the positions of the top teams
     * @param teams Sorted list of top teams
     */
    public void updateTopTeamsPositions(List<me.abdymazhit.parkourtag.custom.Team> teams) {
        if(teams.size() > 4) {
            me.abdymazhit.parkourtag.custom.Team first = teams.get(0);
            int secondTeamPlace;
            me.abdymazhit.parkourtag.custom.Team second;
            int thirdTeamPlace;
            me.abdymazhit.parkourtag.custom.Team third;
            int forthTeamPlace;
            me.abdymazhit.parkourtag.custom.Team forth;

            if(first == team) {
                secondTeamPlace = 2;
                second = teams.get(1);

                thirdTeamPlace = 3;
                third = teams.get(2);

                forthTeamPlace = 4;
                forth = teams.get(3);
            } else {
                int currentTeamPlace = 0;
                for(int i = 0; i < teams.size(); i++) {
                    me.abdymazhit.parkourtag.custom.Team t = teams.get(i);
                    if(t == team) {
                        currentTeamPlace = i + 1;
                        break;
                    }
                }

                if(currentTeamPlace == 2 || currentTeamPlace == 3) {
                    secondTeamPlace = 2;
                    second = teams.get(1);

                    thirdTeamPlace = 3;
                    third = teams.get(2);

                    forthTeamPlace = 4;
                    forth = teams.get(3);
                } else if(currentTeamPlace == teams.size()){
                    secondTeamPlace = teams.size() - 2;
                    second = teams.get(teams.size() - 3);

                    thirdTeamPlace = teams.size() - 1;
                    third = teams.get(teams.size() - 2);

                    forthTeamPlace = currentTeamPlace;
                    forth = teams.get(currentTeamPlace - 1);
                } else {
                    secondTeamPlace = currentTeamPlace - 1;
                    second = teams.get(currentTeamPlace - 2);

                    thirdTeamPlace = currentTeamPlace;
                    third = teams.get(currentTeamPlace - 1);

                    forthTeamPlace = currentTeamPlace + 1;
                    forth = teams.get(currentTeamPlace);
                }
            }

            for(Player p : team.getPlayers()) {
                Team firstTeam = p.getScoreboard().getTeam("first");
                if(firstTeam == null) {
                    throw new IllegalArgumentException("Error getting player's scoreboard 'first' team");
                }
                firstTeam.setSuffix("1. " + first.getDisplayName() + " " + first.getCoins());

                Team secondTeam = p.getScoreboard().getTeam("second");
                if(secondTeam == null) {
                    throw new IllegalArgumentException("Error getting player's scoreboard 'second' team");
                }
                secondTeam.setSuffix(secondTeamPlace + ". " + second.getDisplayName() + " " + second.getCoins());

                Team thirdTeam = p.getScoreboard().getTeam("third");
                if(thirdTeam == null) {
                    throw new IllegalArgumentException("Error getting player's scoreboard 'third' team");
                }
                thirdTeam.setSuffix(thirdTeamPlace + ". " + third.getDisplayName() + " " + third.getCoins());

                Team forthTeam = p.getScoreboard().getTeam("forth");
                if(forthTeam == null) {
                    throw new IllegalArgumentException("Error getting player's scoreboard 'forth' team");
                }
                forthTeam.setSuffix(forthTeamPlace + ". " + forth.getDisplayName() + " " + forth.getCoins());
            }
        } else {
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

    /**
     * Updates the counter of alive runners of ours
     * @param aliveRunnersCount Number of alive runners
     */
    public void updateOurTeamAliveRunnersCounter(int aliveRunnersCount) {
        for(Player p : team.getPlayers()) {
            Team team = p.getScoreboard().getTeam("our");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'our' team");
            }

            team.setSuffix(aliveRunnersCount + "/" + (Config.getPlayersInTeam() - 1));
        }
    }

    /**
     * Updates the counter of alive runners of enemies
     * @param aliveRunnersCount Number of alive runners
     */
    public void updateEnemyTeamRunnersCounter(int aliveRunnersCount) {
        for(Player p : team.getPlayers()) {
            Team team = p.getScoreboard().getTeam("enemy");
            if(team == null) {
                throw new IllegalArgumentException("Error getting player's scoreboard 'enemy' team");
            }

            team.setSuffix(aliveRunnersCount + "/" + (Config.getPlayersInTeam() - 1));
        }
    }
}
