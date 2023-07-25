package de.devsnx.statsapi;

import de.devsnx.statsapi.database.MySQL;
import de.devsnx.statsapi.database.SQLLite;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author DevSnx
 * @since 24.07.2023
 */
public final class StatsAPI extends JavaPlugin {

    public static StatsAPI instance;
    public static MySQL mySQL;
    public static SQLLite sqlLite;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }

    public static StatsAPI getInstance() {
        return instance;
    }

    public static SQLLite getSqlLite() {
        return sqlLite;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }
}