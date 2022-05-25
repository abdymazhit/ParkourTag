package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import me.abdymazhit.parkourtag.custom.Match;
import me.abdymazhit.parkourtag.custom.MatchState;
import me.abdymazhit.parkourtag.custom.PlayerInfo;
import me.abdymazhit.parkourtag.custom.Role;
import me.abdymazhit.parkourtag.events.MatchStartEvent;
import me.abdymazhit.parkourtag.scoreboard.GameBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Represents a MatchStartEvent event handler
 */
public class MatchStartHandler implements Listener {

    @EventHandler
    public void onMatchStart(MatchStartEvent event) {
        for(Match match : GameManager.getRound().getMatches()) {
            match.setMatchState(MatchState.STARTING);

            for(PlayerInfo playerInfo : match.getFirstTeamPlayersInfo()) {
                Player player = playerInfo.getPlayer();

                switch(playerInfo.getRole()) {
                    case HUNTER:
                        // hide teammate runners and enemy hunter for the player
                        for(PlayerInfo pInfo : match.getFirstTeamPlayersInfo()) {
                            if(pInfo.getRole().equals(Role.CAUGHT_RUNNER)) {
                                player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                            }
                        }

                        for(PlayerInfo pInfo : match.getSecondTeamPlayersInfo()) {
                            if(pInfo.getRole().equals(Role.HUNTER)) {
                                player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                                break;
                            }
                        }
                        break;
                    case RUNNER:
                    case CAUGHT_RUNNER:
                        // hide teammate hunter and enemy runners for the player
                        for(PlayerInfo pInfo : match.getFirstTeamPlayersInfo()) {
                            if(pInfo.getRole().equals(Role.HUNTER)) {
                                player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                                break;
                            }
                        }

                        for(PlayerInfo pInfo : match.getSecondTeamPlayersInfo()) {
                            if(pInfo.getRole().equals(Role.CAUGHT_RUNNER)) {
                                player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                            }
                        }
                        break;
                }
            }
            
            for(PlayerInfo playerInfo : match.getSecondTeamPlayersInfo()) {
                Player player = playerInfo.getPlayer();

                switch(playerInfo.getRole()) {
                    case HUNTER:
                        // hide teammate runners and enemy hunter for the player
                        for(PlayerInfo pInfo : match.getSecondTeamPlayersInfo()) {
                            if(pInfo.getRole().equals(Role.CAUGHT_RUNNER)) {
                                player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                            }
                        }

                        for(PlayerInfo pInfo : match.getFirstTeamPlayersInfo()) {
                            if(pInfo.getRole().equals(Role.HUNTER)) {
                                player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                                break;
                            }
                        }
                        break;
                    case RUNNER:
                    case CAUGHT_RUNNER:
                        // hide teammate hunter and enemy runners for the player
                        for(PlayerInfo pInfo : match.getSecondTeamPlayersInfo()) {
                            if(pInfo.getRole().equals(Role.HUNTER)) {
                                player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                                break;
                            }
                        }

                        for(PlayerInfo pInfo : match.getFirstTeamPlayersInfo()) {
                            if(pInfo.getRole().equals(Role.CAUGHT_RUNNER)) {
                                player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                            }
                        }
                        break;
                }
            }
        }

        new BukkitRunnable() {
            int time = Config.getRoundStartTime();

            @Override
            public void run() {
                for(Match match : GameManager.getRound().getMatches()) {
                    GameBoard gameBoard = (GameBoard) match.getFirstTeam().getTeamBoard();
                    gameBoard.setMatchStartStatus(time);

                    gameBoard = (GameBoard) match.getSecondTeam().getTeamBoard();
                    gameBoard.setMatchStartStatus(time);
                }

                time--;
                if(time <= 0) {
                    /// start next step
                }
            }
        }.runTaskTimer(ParkourTag.getInstance(), 20L, 0);
    }
}
