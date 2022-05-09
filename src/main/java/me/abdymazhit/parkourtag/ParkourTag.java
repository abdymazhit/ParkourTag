package me.abdymazhit.parkourtag;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents the main class
 */
public class ParkourTag extends JavaPlugin {

    /**
     * Represents a plugin instance
     */
    private static ParkourTag instance;

    /**
     * Called when the plugin is enabled
     */
    @Override
    public void onEnable() {
        instance = this;

        Config.load(this);
        GameManager.setup();

        getLogger().info("ParkourTag plugin successfully enabled!");
    }

    /**
     * Called when the plugin is disabled
     */
    @Override
    public void onDisable() {
        getLogger().info("ParkourTag plugin successfully disabled!");
    }

    /**
     * Gets a plugin instance
     * @return a plugin instance
     */
    public static ParkourTag getInstance() {
        return instance;
    }
}
