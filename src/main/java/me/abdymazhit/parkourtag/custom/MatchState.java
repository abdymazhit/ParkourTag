package me.abdymazhit.parkourtag.custom;

/**
 * Represents the match state
 */
public enum MatchState {
    /**
     * Represents the selection state of a hunter
     */
    SELECTING_HUNTER,
    /**
     * Represents the start state of the round
     */
    STARTING,
    /**
     * Represents a game progression state
     */
    GAME,
    /**
     * Represents the state of waiting for the end of games of other matches
     */
    WAITING_FOR_OTHER_MATCHES,
    /**
     * Represents the end round state
     */
    ENDING,
}
