package de.devsnx.statsapi;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author DevSnx
 * @since 24.07.2023
 */
public final class StatsAPI extends JavaPlugin {

    public static StatsAPI statsAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        statsAPI = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        statsAPI = null;
    }

    public static StatsAPI getStatsAPI() {
        return statsAPI;
    }
}