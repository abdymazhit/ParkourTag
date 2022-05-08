package me.abdymazhit.parkourtag;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents the main class
 */
public class ParkourTag extends JavaPlugin {

    /**
     * Called when the plugin is enabled
     */
    @Override
    public void onEnable() {
        Config.load(this);
        GameManager.setupTeamsConfig(Config.getTeams());

        getLogger().info("ParkourTag plugin successfully enabled!");
    }

    /**
     * Called when the plugin is disabled
     */
    @Override
    public void onDisable() {
        getLogger().info("ParkourTag plugin successfully disabled!");
    }
}
