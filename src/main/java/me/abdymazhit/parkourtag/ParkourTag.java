package me.abdymazhit.parkourtag;

import me.abdymazhit.parkourtag.handlers.game.*;
import me.abdymazhit.parkourtag.handlers.spigot.PlayerJoinHandler;
import me.abdymazhit.parkourtag.handlers.spigot.PlayerQuitHandler;
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

        // spigot events
        getServer().getPluginManager().registerEvents(new PlayerJoinHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitHandler(), this);

        // game events
        getServer().getPluginManager().registerEvents(new PlayerAddHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerRemoveHandler(), this);
        getServer().getPluginManager().registerEvents(new GamePlayerAddHandler(), this);
        getServer().getPluginManager().registerEvents(new GamePlayerRemoveHandler(), this);
        getServer().getPluginManager().registerEvents(new SpectatorAddHandler(), this);
        getServer().getPluginManager().registerEvents(new SpectatorRemoveHandler(), this);
        getServer().getPluginManager().registerEvents(new GameStartHandler(), this);
        getServer().getPluginManager().registerEvents(new RunnerCatchHandler(), this);

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
