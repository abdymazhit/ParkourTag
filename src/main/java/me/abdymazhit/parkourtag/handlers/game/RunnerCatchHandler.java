package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import me.abdymazhit.parkourtag.custom.Match;
import me.abdymazhit.parkourtag.custom.PlayerInfo;
import me.abdymazhit.parkourtag.custom.Role;
import me.abdymazhit.parkourtag.events.RunnerCatchEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

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
                setCaughtRunnerRole(match.getFirstTeamPlayersInfo(), runner);
            } else if(match.getSecondTeam().getPlayers().contains(runner)) {
                setCaughtRunnerRole(match.getSecondTeamPlayersInfo(), runner);
            }
        }

        runner.setAllowFlight(true);
        runner.setFlying(true);

        hunter.hidePlayer(ParkourTag.getInstance(), runner);
    }

    /**
     * Sets the role of the caught runner to the runner
     * @param playersInfo Information about team players
     * @param runner Runner
     */
    private void setCaughtRunnerRole(List<PlayerInfo> playersInfo, Player runner) {
        for(PlayerInfo playerInfo : playersInfo) {
            if(playerInfo.getPlayer().equals(runner)) {
                playerInfo.setRole(Role.CAUGHT_RUNNER);
            } else {
                playerInfo.getPlayer().hidePlayer(ParkourTag.getInstance(), runner);
            }
        }
    }
}
