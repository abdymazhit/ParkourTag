package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.custom.*;
import me.abdymazhit.parkourtag.events.GamePlayerRemoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Represents a GamePlayerRemoveEvent event handler
 */
public class GamePlayerRemoveHandler implements Listener {

    @EventHandler
    public void onGamePlayerRemove(GamePlayerRemoveEvent event) {
        Player player = event.getPlayer();

        for(Match match : GameManager.getRound().getMatches()) {
            if(match.getFirstTeam().getPlayers().contains(player)) {
                TeamInfo teamInfo = match.getFirstTeamInfo();
                if(teamInfo == null) {
                    break;
                }
                catchRunner(match, teamInfo, player);
                break;
            } else if(match.getSecondTeam().getPlayers().contains(player)) {
                TeamInfo teamInfo = match.getSecondTeamInfo();
                if(teamInfo == null) {
                    break;
                }
                catchRunner(match, teamInfo, player);
                break;
            }
        }
    }

    /**
     * Catches the runner
     * @param match Match the runner is playing
     * @param teamInfo Information about team
     * @param player Runner of the match
     */
    private void catchRunner(Match match, TeamInfo teamInfo, Player player) {
        if(!match.getMatchState().equals(MatchState.SELECTING_HUNTER)) {
            for(PlayerInfo playerInfo : teamInfo.getPlayersInfo()) {
                if(!playerInfo.getPlayer().equals(player)) {
                    continue;
                }

                if(playerInfo.getRole().equals(Role.RUNNER)) {
                    // catch runner
                }
                break;
            }
        }
    }
}
