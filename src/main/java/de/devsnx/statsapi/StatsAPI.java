package de.devsnx.statsapi;

import org.bukkit.plugin.java.JavaPlugin;

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
