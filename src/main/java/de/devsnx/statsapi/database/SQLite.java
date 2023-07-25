package de.devsnx.statsapi.database;

import de.devsnx.statsapi.database.utils.AsyncDatabase;
import de.devsnx.statsapi.database.utils.Callback;
import org.bukkit.plugin.Plugin;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author DevSnx
 * @since 25.07.2023
 */
public class SQLite extends SQL {

    private Plugin plugin;

    public SQLite(Plugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    public AsyncDatabase connect(Callback<String> callback) {
        return connect(null, 0, null, null, null, callback);
    }

    @Override
    public AsyncDatabase connect(String host, int port, String database, String user, String password, Callback<String> callback) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            callback.handle(e, null);
            return this;
        }

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + "/SQLite.db");
            callback.handle(null, null);
        } catch (SQLException e) {
            callback.handle(e, null);
        }
        return this;
    }
}