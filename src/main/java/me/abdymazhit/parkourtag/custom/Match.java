package me.abdymazhit.parkourtag.custom;

import java.util.List;

/**
 * Represents a match between two teams
 */
public class Match {

    /**
     * Represents the first team
     */
    private final Team firstTeam;
    /**
     * Represents information about the first team
     */
    private TeamInfo firstTeamInfo;
    /**
     * Represents the second team
     */
    private final Team secondTeam;
    /**
     * Represents information about the second team
     */
    private TeamInfo secondTeamInfo;

    /**
     * Creates a match between two teams
     * @param firstTeam First team
     * @param secondTeam Second team
     */
    public Match(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    /**
     * Gets the first team
     * @return the first team
     */
    public Team getFirstTeam() {
        return firstTeam;
    }

    /**
     * Sets information about the first team
     * @param team Team
     * @param playersInfo Information about players
     */
    public void setFirstTeamInfo(Team team, List<PlayerInfo> playersInfo) {
        firstTeamInfo = new TeamInfo(team, playersInfo);
    }

    /**
     * Gets information about the first team
     * @return information about the first team
     */
    public TeamInfo getFirstTeamInfo() {
        return firstTeamInfo;
    }

    /**
     * Gets the second team
     * @return the second team
     */
    public Team getSecondTeam() {
        return secondTeam;
    }

    /**
     * Sets information about the second team
     * @param team Team
     * @param playersInfo Information about players
     */
    public void setSecondTeamInfo(Team team, List<PlayerInfo> playersInfo) {
        secondTeamInfo = new TeamInfo(team, playersInfo);
    }

    /**
     * Gets information about the second team
     * @return information about the second team
     */
    public TeamInfo getSecondTeamInfo() {
        return secondTeamInfo;
    }
}
