package me.abdymazhit.parkourtag.custom;

import java.util.List;

/**
 * Represents information about a team
 */
public class TeamInfo {

    /**
     * Represents a team
     */
    private final Team team;
    /**
     * Represents information about players
     */
    private final List<PlayerInfo> playersInfo;

    /**
     * Creates information about a team
     * @param team Team
     * @param playersInfo information about players
     */
    public TeamInfo(Team team, List<PlayerInfo> playersInfo) {
        this.team = team;
        this.playersInfo = playersInfo;
    }

    /**
     * Gets a team
     * @return a team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Gets information about players
     * @return information about players
     */
    public List<PlayerInfo> getPlayersInfo() {
        return playersInfo;
    }
}
