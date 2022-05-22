package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import me.abdymazhit.parkourtag.custom.Match;
import me.abdymazhit.parkourtag.custom.Team;
import me.abdymazhit.parkourtag.events.RoundStartEvent;
import me.abdymazhit.parkourtag.scoreboard.GameBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
            GameBoard gameBoard = (GameBoard) match.getFirstTeam().getTeamBoard();
            gameBoard.updateRoundCounter();
            gameBoard.updateTopTeamsPositions(sortedTeams);
            gameBoard.updateOurTeamAliveRunnersCounter(Config.getPlayersInTeam() - 1);
            gameBoard.updateEnemyTeamAliveRunnersCounter(Config.getPlayersInTeam() - 1);

            for(Player player : match.getFirstTeam().getPlayers()) {
                // makes non-match players invisible to participating players
                for(Team t : Config.getTeams()) {
                    if(!t.equals(match.getFirstTeam()) && !t.equals(match.getSecondTeam())) {
                        for(Player p : t.getPlayers()) {
                            player.hidePlayer(ParkourTag.getInstance(), p);
                        }
                    }
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

                player.teleport(Config.getSecondTeamLocation());
            }
        }
    }
}