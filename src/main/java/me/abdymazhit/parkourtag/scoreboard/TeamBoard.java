package me.abdymazhit.parkourtag.scoreboard;

import me.abdymazhit.parkourtag.custom.Team;
import org.bukkit.entity.Player;

/**
 * Represents the scoreboard of each team
 */
public class TeamBoard extends Board {

    /**
     * Represents the team
     */
    public Team team;

    /**
     * Creates the scoreboard of each team
     * @param team Team
     */
    public TeamBoard(Team team) {
        this.team = team;
    }

    /**
     * Sets the scoreboard to the team
     */
    public void setTeamScoreboard() {
        for(Player p : team.getPlayers()) {
            setScoreboard(p);
        }
    }
}
