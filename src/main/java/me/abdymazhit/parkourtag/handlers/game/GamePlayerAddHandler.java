package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.custom.*;
import me.abdymazhit.parkourtag.events.GamePlayerAddEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Represents a GamePlayerAddEvent event handler
 */
public class GamePlayerAddHandler implements Listener {

    @EventHandler
    public void onGamePlayerAdd(GamePlayerAddEvent event) {
        Player player = event.getPlayer();
        Team team = event.getTeam();
        team.getTeamBoard().setScoreboard(player);

        if(GameManager.getGameState().equals(GameState.ENDING)) {
            player.teleport(Config.getRunnersLocation());
            return;
        }

        for(Match match : GameManager.getRound().getMatches()) {
            int teamNumber;
            if(match.getFirstTeam().equals(team)) {
                teamNumber = 1;
            } else if(match.getSecondTeam().equals(team)) {
                teamNumber = 2;
            } else {
                continue;
            }

            switch(match.getMatchState()) {
                case SELECTING_HUNTER:
                    if(teamNumber == 1) {
                        player.teleport(Config.getFirstTeamLocation());
                    } else {
                        player.teleport(Config.getSecondTeamLocation());
                    }
                    break;
                case STARTING:
                    PlayerInfo playerInfo = getPlayerInfo(teamNumber, match, player);
                    if(playerInfo == null) {
                        break;
                    }

                    switch(playerInfo.getRole()) {
                        case HUNTER:
                            player.teleport(Config.getHunterLocation());
                            break;
                        case RUNNER:
                            player.teleport(Config.getRunnersLocation());
                            break;
                    }
                    break;
                case GAME:
                    playerInfo = getPlayerInfo(teamNumber, match, player);
                    if(playerInfo == null) {
                        break;
                    }

                    switch(playerInfo.getRole()) {
                        case HUNTER:
                            player.teleport(Config.getHunterLocation());
                            break;
                        case CAUGHT_RUNNER:
                            player.teleport(Config.getRunnersLocation());
                            break;
                    }
                    break;
                case WAITING_FOR_OTHER_MATCHES:
                case ENDING:
                    player.teleport(Config.getRunnersLocation());
                    break;
            }
        }
    }

    /**
     * Gets information about a player
     * @param teamNumber Team number
     * @param match Match the player is playing
     * @param player Player of the match
     * @return Information about a player
     */
    private PlayerInfo getPlayerInfo(int teamNumber, Match match, Player player) {
        PlayerInfo playerInfo = null;
        if(teamNumber == 1) {
            for(PlayerInfo pInfo : match.getFirstTeamInfo().getPlayersInfo()) {
                if(pInfo.getPlayer().equals(player)) {
                    playerInfo = pInfo;
                    break;
                }
            }
        } else {
            for(PlayerInfo pInfo : match.getSecondTeamInfo().getPlayersInfo()) {
                if(pInfo.getPlayer().equals(player)) {
                    playerInfo = pInfo;
                    break;
                }
            }
        }
        return playerInfo;
    }
}
