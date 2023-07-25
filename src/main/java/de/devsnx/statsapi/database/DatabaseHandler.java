package de.devsnx.statsapi.database;

import de.devsnx.statsapi.StatsAPI;
import de.devsnx.statsapi.database.utils.AsyncDatabase;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author DevSnx
 * @since 25.07.2023
 */

public class DatabaseHandler {

    private static AsyncDatabase database;
    private static Plugin plugin = StatsAPI.getPlugin(StatsAPI.class);

    public static boolean initialize() {
        //MessageUtil.sendConsoleMessage("&cConnecting to database...");

        FileConfiguration config = StatsAPI.getInstance().getConfig();
        String type = config.getString("Database.Type");

        if (type == null) {
            //MessageUtil.sendError("No Type found on database section on config file.");
            Bukkit.getPluginManager().disablePlugin(plugin);
            return false;
        }

        AtomicBoolean valid = new AtomicBoolean(true);
        //MessageUtil.sendConsoleMessage("&cUsing &e" + type + " &cas database.");

        switch (type.toLowerCase()) {
            case "sqlite":
                database = new SQLite(plugin).connect((error, result) -> {
                    if (error != null) {
                        error.printStackTrace();
                        Bukkit.getPluginManager().disablePlugin(plugin);
                        valid.set(false);
                        //MessageUtil.sendError("&7Could not connected to database.");
                        return;
                    }
                    //MessageUtil.sendConsoleMessage("&7Successfully connected to database.");
                });
                break;
            case "mysql":
                String host = config.getString("database.mysql.host"),
                        username = config.getString("database.mysql.username"),
                        password = config.getString("database.mysql.password"),
                        db = config.getString("database.mysql.database");
                Boolean useSSL = config.getBoolean("database.mysql.useSSL", false);

                int port = config.getInt("database.mysql.port");
                database = new MySQL(plugin).connect(host, port, db, username, password, useSSL, (error, result) -> {
                    if (error != null) {
                        error.printStackTrace();
                        Bukkit.getPluginManager().disablePlugin(plugin);
                        valid.set(false);
                        return;
                    }
                    //MessageUtil.sendConsoleMessage("&7Successfully connected to database.");
                });
                break;
            case "mongo":
                // TODO: Connect to mongo database
                break;
            default:
                break;
        }

        if (database instanceof MySQL) ((MySQL) database).startProcess();
        database.createTable((e, r) -> {
            if (e != null) e.printStackTrace();
        });
        return valid.get();
    }

    public static AsyncDatabase getDatabase() {
        return database;
    }

    public static void close() {
        if (database instanceof SQL) {
            SQL sql = (SQL) database;
            sql.queue.add(connection -> {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}