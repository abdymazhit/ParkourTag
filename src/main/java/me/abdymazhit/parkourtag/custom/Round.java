package me.abdymazhit.parkourtag.custom;

import me.abdymazhit.parkourtag.events.RoundStartEvent;
import org.bukkit.Bukkit;

import java.util.List;

/**
 * Represents a game round
 */
public class Round {

    /**
     * Represents the round number
     */
    public static int number = 0;
    /**
     * Represents the matches of the round
     */
    private final List<Match> matches;

    /**
     * Creates a round
     * @param matches Matches of the round
     */
    public Round(List<Match> matches) {
        this.matches = matches;

        RoundStartEvent event = new RoundStartEvent();
        Bukkit.getPluginManager().callEvent(event);
    }

    /**
     * Returns a list of round matches
     * @return a list of round matches
     */
    public List<Match> getMatches() {
        return matches;
    }
}
