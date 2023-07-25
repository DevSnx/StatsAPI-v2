package de.devsnx.statsapi.database.utils;

import java.util.Map;
/**
 * @author DevSnx
 * @since 25.07.2023
 */

public interface AsyncDatabase {

    String table = "statsapi";

    AsyncDatabase connect(String host, int port, String database, String user, String password, Callback<String> callback);

    void createTable(Callback<Boolean> callback);

    default void select(String key, Callback<Map<String, Object>> callback) {
        this.select(key, null, null, callback);
    }

    void select(String key, String whereKey, Object whereValue, Callback<Map<String, Object>> callback);

    void insert(String[] keys, Object[] values, Callback<Object> callback);

    default void update(String[] keys, Object[] values, Callback<Object> callback) {
        this.update(keys, values, keys[0], values[0], callback);
    }

    void update(String[] keys, Object[] values, String whereKey, Object whereValue, Callback<Object> callback);
}