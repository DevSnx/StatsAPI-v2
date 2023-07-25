package de.devsnx.statsapi;

import de.devsnx.statsapi.database.DatabaseHandler;
import de.devsnx.statsapi.listener.PlayerJoinListener;
import de.devsnx.statsapi.listener.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author DevSnx
 * @since 24.07.2023
 */
public final class StatsAPI extends JavaPlugin {

    public static StatsAPI instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.saveDefaultConfig();
        if (!DatabaseHandler.initialize()) return;
    }

    private void loadEvents(){
        PluginManager load = Bukkit.getPluginManager();
        load.registerEvents(new PlayerJoinListener(), this);
        load.registerEvents(new PlayerQuitListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DatabaseHandler.close();
        instance = null;
    }

    public static StatsAPI getInstance() {
        return instance;
    }

}