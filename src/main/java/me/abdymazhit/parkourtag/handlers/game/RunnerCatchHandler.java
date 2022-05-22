package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.custom.Match;
import me.abdymazhit.parkourtag.custom.PlayerInfo;
import me.abdymazhit.parkourtag.custom.Role;
import me.abdymazhit.parkourtag.custom.TeamInfo;
import me.abdymazhit.parkourtag.events.RunnerCatchEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Represents a RunnerCatchEvent event handler
 */
public class RunnerCatchHandler implements Listener {

    @EventHandler
    public void onRunnerCatch(RunnerCatchEvent event) {
        Player runner = event.getRunner();
        Player hunter = event.getHunter();

        for(Match match : GameManager.getRound().getMatches()) {
            if(match.getFirstTeam().getPlayers().contains(runner)) {
                setCaughtRunnerRole(match.getFirstTeamInfo(), runner);
            } else if(match.getSecondTeam().getPlayers().contains(runner)) {
                setCaughtRunnerRole(match.getSecondTeamInfo(), runner);
            }
        }

        runner.setAllowFlight(true);
        runner.setFlying(true);
    }

    /**
     * Sets the role of the caught runner to the player
     * @param teamInfo Information about player's team
     * @param player Player
     */
    private void setCaughtRunnerRole(TeamInfo teamInfo, Player player) {
        for(PlayerInfo playerInfo : teamInfo.getPlayersInfo()) {
            if(playerInfo.getPlayer().equals(player)) {
                playerInfo.setRole(Role.CAUGHT_RUNNER);
            }
        }
    }
}
