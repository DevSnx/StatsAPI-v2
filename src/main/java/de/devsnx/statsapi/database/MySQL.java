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

public class MySQL extends SQL {

    public MySQL(Plugin plugin) {
        super(plugin);
    }

    public AsyncDatabase connect(String host, int port, String database, String user, String password, Boolean useSSL, Callback<String> callback) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            callback.handle(e, null);
            return this;
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database
                    + "?autoReconnect=true&useSSL=" + useSSL, user, password);
            callback.handle(null, null);
        } catch (SQLException e) {
            callback.handle(e, null);
        }
        return this;
    }

    @Override
    public AsyncDatabase connect(String host, int port, String database, String user, String password, Callback<String> callback) {
        return this.connect(host, port, database, user, password, true, callback);
    }
}