package me.abdymazhit.parkourtag.custom;

/**
 * Represents a player's role
 */
public enum Role {
    /**
     * Represents the role of a hunter who catches runners
     */
    HUNTER,
    /**
     * Represents the role of a runner who must run away from a hunter
     */
    RUNNER,
    /**
     * Represents the role of the caught runner by the hunter
     */
    CAUGHT_RUNNER
}
