package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import me.abdymazhit.parkourtag.custom.*;
import me.abdymazhit.parkourtag.events.MatchStartEvent;
import me.abdymazhit.parkourtag.events.RoundStartEvent;
import me.abdymazhit.parkourtag.scoreboard.GameBoard;
import me.abdymazhit.parkourtag.scoreboard.TeamBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a RoundStartEvent event handler
 */
public class RoundStartHandler implements Listener {

    @EventHandler
    public void onRoundStart(RoundStartEvent event) {
        List<Team> sortedTeams = new ArrayList<>(Config.getTeams());
        sortedTeams.sort(Comparator.comparing(Team::getCoins));
        Collections.reverse(sortedTeams);

        for(Match match : GameManager.getRound().getMatches()) {
            match.setMatchState(MatchState.SELECTING_HUNTER);

            updatePlayersScoreboard(match.getFirstTeam().getTeamBoard(), sortedTeams);
            updatePlayersScoreboard(match.getSecondTeam().getTeamBoard(), sortedTeams);

            for(Player player : match.getFirstTeam().getPlayers()) {
                // makes non-match players invisible to participating players
                for(Team t : Config.getTeams()) {
                    if(!t.equals(match.getFirstTeam()) && !t.equals(match.getSecondTeam())) {
                        for(Player p : t.getPlayers()) {
                            player.hidePlayer(ParkourTag.getInstance(), p);
                        }
                    }
                }
                for(Player p : match.getSecondTeam().getPlayers()) {
                    player.showPlayer(ParkourTag.getInstance(), p);
                }

                player.teleport(Config.getFirstTeamLocation());
            }

            for(Player player : match.getSecondTeam().getPlayers()) {
                // makes non-match players invisible to participating players
                for(Team t : Config.getTeams()) {
                    if(!t.equals(match.getFirstTeam()) && !t.equals(match.getSecondTeam())) {
                        for(Player p : t.getPlayers()) {
                            player.hidePlayer(ParkourTag.getInstance(), p);
                        }
                    }
                }
                for(Player p : match.getFirstTeam().getPlayers()) {
                    player.showPlayer(ParkourTag.getInstance(), p);
                }

                player.teleport(Config.getSecondTeamLocation());
            }
        }

        new BukkitRunnable() {
            int time = Config.getHunterSelectionTime();

            @Override
            public void run() {
                for(Match match : GameManager.getRound().getMatches()) {
                    GameBoard gameBoard = (GameBoard) match.getFirstTeam().getTeamBoard();
                    gameBoard.setHunterSelectionStatus(time);

                    gameBoard = (GameBoard) match.getSecondTeam().getTeamBoard();
                    gameBoard.setHunterSelectionStatus(time);
                }

                time--;
                if(time <= 0) {
                    // auto select hunter
                    for(Match match : GameManager.getRound().getMatches()) {
                        if(match.getFirstTeamPlayersInfo().isEmpty()) {
                            int index = (int) (Math.random() * Config.getPlayersInTeam());
                            for(int i = 0; i < match.getFirstTeam().getPlayers().size(); i++) {
                                Player player = match.getFirstTeam().getPlayers().get(i);
                                if(i == index) {
                                    match.getFirstTeamPlayersInfo().add(new PlayerInfo(player, Role.HUNTER));
                                } else {
                                    match.getFirstTeamPlayersInfo().add(new PlayerInfo(player, Role.RUNNER));
                                }
                            }
                        }

                        if(match.getSecondTeamPlayersInfo().isEmpty()) {
                            int index = (int) (Math.random() * Config.getPlayersInTeam());
                            for(int i = 0; i < match.getSecondTeam().getPlayers().size(); i++) {
                                Player player = match.getSecondTeam().getPlayers().get(i);
                                if(i == index) {
                                    match.getSecondTeamPlayersInfo().add(new PlayerInfo(player, Role.HUNTER));
                                } else {
                                    match.getSecondTeamPlayersInfo().add(new PlayerInfo(player, Role.RUNNER));
                                }
                            }
                        }
                    }

                    MatchStartEvent event = new MatchStartEvent();
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }.runTaskTimer(ParkourTag.getInstance(), 20L, 0);
    }

    /**
     * Sets up the team scoreboard for the new round
     * @param teamBoard Team scoreboard
     * @param sortedTeams Sorted list of top teams
     */
    private void updatePlayersScoreboard(TeamBoard teamBoard, List<Team> sortedTeams) {
        GameBoard gameBoard = (GameBoard) teamBoard;
        gameBoard.updateRoundCounter();
        gameBoard.updateTopTeamsPositions(sortedTeams);
        gameBoard.updateOurTeamAliveRunnersCounter(Config.getPlayersInTeam() - 1);
        gameBoard.updateEnemyTeamAliveRunnersCounter(Config.getPlayersInTeam() - 1);
    }
}