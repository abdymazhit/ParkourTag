package me.abdymazhit.custom;

/**
 * Represents a team
 */
public class Team {

    /**
     * Represents the team name
     */
    private final String name;
    /**
     * Represents the team color
     */
    private final String color;

    /**
     * Creates a team with the given parameters
     * @param name Team name
     * @param color Team color
     */
    public Team(String name, String color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Gets the team name
     * @return the team name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the team color
     * @return the team color
     */
    public String getColor() {
        return color;
    }
}
