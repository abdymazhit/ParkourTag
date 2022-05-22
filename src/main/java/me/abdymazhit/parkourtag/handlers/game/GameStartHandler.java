package me.abdymazhit.parkourtag.handlers.game;

import me.abdymazhit.parkourtag.Config;
import me.abdymazhit.parkourtag.GameManager;
import me.abdymazhit.parkourtag.ParkourTag;
import me.abdymazhit.parkourtag.custom.GameState;
import me.abdymazhit.parkourtag.custom.Team;
import me.abdymazhit.parkourtag.events.GameStartEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Represents a GameStartEvent event handler
 */
public class GameStartHandler implements Listener {

    @EventHandler
    public void onGameStart(GameStartEvent event) {
        GameManager.setGameState(GameState.STARTING);

        GameManager.setTask(new BukkitRunnable() {
            int time = Config.getGameStartTime();

            @Override
            public void run() {
                GameManager.getLobbyBoard().setStartingGameStatus(time);

                time--;
                if(time <= 0) {
                    // auto team selection for players who haven't chosen a team yet
                    for(Player player : GameManager.getWaitingGamePlayers()) {
                        boolean hasTeam = false;
                        for(Team team : Config.getTeams()) {
                            if(team.getPlayers().contains(player)) {
                                hasTeam = true;
                                break;
                            }
                        }

                        if(!hasTeam) {
                            for(Team team : Config.getTeams()) {
                                if(team.getPlayers().size() < Config.getPlayersInTeam()) {
                                    team.addPlayer(player);
                                    break;
                                }
                            }
                        }
                    }
                    GameManager.clearWaitingGamePlayersList();

                    GameManager.startNextRound();
                }
            }
        }.runTaskTimer(ParkourTag.getInstance(), 20L, 0));
    }
}
