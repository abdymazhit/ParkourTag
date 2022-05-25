package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
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

        // make spectators invisible to the player
        for(Player spectator : GameManager.getSpectators()) {
            player.hidePlayer(ParkourTag.getInstance(), spectator);
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

            // make players invisible to the player who are not participating in this match
            for(Team t : Config.getTeams()) {
                if(!t.equals(match.getFirstTeam()) && !t.equals(match.getSecondTeam())) {
                    for(Player p : t.getPlayers()) {
                        player.hidePlayer(ParkourTag.getInstance(), p);
                    }
                }
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
                            setInvisiblePlayersForHunter(teamNumber, match, player);
                            player.teleport(Config.getHunterLocation());
                            break;
                        case RUNNER:
                            setInvisiblePlayersForRunner(teamNumber, match, player);
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
                            setInvisiblePlayersForHunter(teamNumber, match, player);
                            player.teleport(Config.getHunterLocation());
                            break;
                        case CAUGHT_RUNNER:
                            setInvisiblePlayersForRunner(teamNumber, match, player);
                            player.teleport(Config.getRunnersLocation());
                            break;
                    }
                    break;
                case WAITING_FOR_OTHER_MATCHES:
                case ENDING:
                    setInvisiblePlayersForHunter(teamNumber, match, player);
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
            for(PlayerInfo pInfo : match.getFirstTeamPlayersInfo()) {
                if(pInfo.getPlayer().equals(player)) {
                    playerInfo = pInfo;
                    break;
                }
            }
        } else {
            for(PlayerInfo pInfo : match.getSecondTeamPlayersInfo()) {
                if(pInfo.getPlayer().equals(player)) {
                    playerInfo = pInfo;
                    break;
                }
            }
        }
        return playerInfo;
    }

    /**
     * Sets invisible players for the hunter
     * @param teamNumber Team number
     * @param match Match the hunter is playing
     * @param player Hunter of the match
     */
    private void setInvisiblePlayersForHunter(int teamNumber, Match match, Player player) {
        if(teamNumber == 1) {
            // make teammates invisible to the player
            for(Player p : match.getFirstTeam().getPlayers()) {
                player.hidePlayer(ParkourTag.getInstance(), p);
            }

            // make only the runners of the enemy team visible to the player
            for(PlayerInfo pInfo : match.getSecondTeamPlayersInfo()) {
                if(!pInfo.getRole().equals(Role.RUNNER)) {
                    player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                }
            }
        } else {
            // make teammates invisible to the player
            for(Player p : match.getSecondTeam().getPlayers()) {
                player.hidePlayer(ParkourTag.getInstance(), p);
            }

            // make only the runners of the enemy team visible to the player
            for(PlayerInfo pInfo : match.getFirstTeamPlayersInfo()) {
                if(!pInfo.getRole().equals(Role.RUNNER)) {
                    player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                }
            }
        }
    }

    /**
     * Sets invisible players for the runner
     * @param teamNumber Team number
     * @param match Match the hunter is playing
     * @param player Runner of the match
     */
    private void setInvisiblePlayersForRunner(int teamNumber, Match match, Player player) {
        if(teamNumber == 1) {
            // make teammate hunter invisible to the player
            for(PlayerInfo pInfo : match.getFirstTeamPlayersInfo()) {
                if(pInfo.getRole().equals(Role.HUNTER)) {
                    player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                    break;
                }
            }

            // make enemy runners invisible to the player
            for(PlayerInfo pInfo : match.getSecondTeamPlayersInfo()) {
                if(pInfo.getRole().equals(Role.RUNNER) || pInfo.getRole().equals(Role.CAUGHT_RUNNER)) {
                    player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                }
            }
        } else {
            // make teammate hunter invisible to the player
            for(PlayerInfo pInfo : match.getSecondTeamPlayersInfo()) {
                if(pInfo.getRole().equals(Role.HUNTER)) {
                    player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                    break;
                }
            }

            // make enemy runners invisible to the player
            for(PlayerInfo pInfo : match.getFirstTeamPlayersInfo()) {
                if(pInfo.getRole().equals(Role.RUNNER) || pInfo.getRole().equals(Role.CAUGHT_RUNNER)) {
                    player.hidePlayer(ParkourTag.getInstance(), pInfo.getPlayer());
                }
            }
        }
    }
}
