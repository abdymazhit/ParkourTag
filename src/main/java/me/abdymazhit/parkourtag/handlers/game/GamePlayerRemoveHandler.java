package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.custom.Match;
import me.abdymazhit.parkourtag.custom.MatchState;
import me.abdymazhit.parkourtag.custom.PlayerInfo;
import me.abdymazhit.parkourtag.custom.Role;
import me.abdymazhit.parkourtag.events.GamePlayerRemoveEvent;
import me.abdymazhit.parkourtag.events.RunnerCatchEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

/**
 * Represents a GamePlayerRemoveEvent event handler
 */
public class GamePlayerRemoveHandler implements Listener {

    @EventHandler
    public void onGamePlayerRemove(GamePlayerRemoveEvent event) {
        Player player = event.getPlayer();

        for(Match match : GameManager.getRound().getMatches()) {
            if(match.getMatchState().equals(MatchState.SELECTING_HUNTER)) {
                continue;
            }

            if(match.getFirstTeam().getPlayers().contains(player)) {
                for(PlayerInfo playerInfo : match.getSecondTeamPlayersInfo()) {
                    if(playerInfo.getRole().equals(Role.HUNTER)) {
                        catchRunner(match, match.getFirstTeamPlayersInfo(), player, playerInfo.getPlayer());
                        break;
                    }
                }
                break;
            } else if(match.getSecondTeam().getPlayers().contains(player)) {
                for(PlayerInfo playerInfo : match.getFirstTeamPlayersInfo()) {
                    if(playerInfo.getRole().equals(Role.HUNTER)) {
                        catchRunner(match, match.getSecondTeamPlayersInfo(), player, playerInfo.getPlayer());
                    }
                }
                break;
            }
        }
    }

    /**
     * Catches the runner
     * @param match Match the runner is playing
     * @param playersInfo Information about team players
     * @param runner Runner of the match
     * @param hunter Hunter of the match
     */
    private void catchRunner(Match match, List<PlayerInfo> playersInfo, Player runner, Player hunter) {
        for(PlayerInfo playerInfo : playersInfo) {
            if(!playerInfo.getPlayer().equals(runner)) {
                continue;
            }

            if(playerInfo.getRole().equals(Role.RUNNER)) {
                RunnerCatchEvent event = new RunnerCatchEvent(runner, hunter);
                Bukkit.getPluginManager().callEvent(event);
            }
            break;
        }
    }
}
