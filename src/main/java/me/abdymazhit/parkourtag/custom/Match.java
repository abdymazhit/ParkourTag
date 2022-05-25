package me.abdymazhit.parkourtag.custom;

import java.util.ArrayList;
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
     * Represents information about first team players
     */
    private final List<PlayerInfo> firstTeamPlayersInfo;
    /**
     * Represents the second team
     */
    private final Team secondTeam;
    /**
     * Represents information about first team players
     */
    private final List<PlayerInfo> secondTeamPlayersInfo;
    /**
     * Represents the match state
     */
    private MatchState matchState;

    /**
     * Creates a match between two teams
     * @param firstTeam First team
     * @param secondTeam Second team
     */
    public Match(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        firstTeamPlayersInfo = new ArrayList<>();
        secondTeamPlayersInfo = new ArrayList<>();
    }

    /**
     * Gets the first team
     * @return the first team
     */
    public Team getFirstTeam() {
        return firstTeam;
    }

    /**
     * Gets information about first team players
     * @return information about first team players
     */
    public List<PlayerInfo> getFirstTeamPlayersInfo() {
        return firstTeamPlayersInfo;
    }

    /**
     * Gets the second team
     * @return the second team
     */
    public Team getSecondTeam() {
        return secondTeam;
    }

    /**
     * Gets information about second team players
     * @return information about second team players
     */
    public List<PlayerInfo> getSecondTeamPlayersInfo() {
        return secondTeamPlayersInfo;
    }

    /**
     * Sets the match state
     * @param matchState Match state
     */
    public void setMatchState(MatchState matchState) {
        this.matchState = matchState;
    }

    /**
     * Gets the match state
     * @return the match state
     */
    public MatchState getMatchState() {
        return matchState;
    }
}
