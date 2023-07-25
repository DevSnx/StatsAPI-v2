package de.devsnx.statsapi.database.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import de.devsnx.statsapi.StatsAPI;

/**
 * @author DevSnx
 * @since 24.07.2023
 */

public class AsyncHandler {

    private ExecutorService executor;

    public AsyncHandler() {
        this.executor = Executors.newCachedThreadPool();
    }

    public ExecutorService getExecutor() {
        return this.executor;
    }

    public void update(final String statement, DatabaseTyp databaseTyp) {
        if(databaseTyp == DatabaseTyp.MYSQL){
            this.executor.execute(new Runnable() {
                public void run() {
                    StatsAPI.getMySQL().executeUpdate(statement);
                }
            });
        }else if(databaseTyp == DatabaseTyp.SQLLITE){
            this.executor.execute(new Runnable() {
                public void run() {
                    StatsAPI.getSqlLite().executeUpdate(statement);
                }
            });
        }
    }

    public void update(final PreparedStatement statement, DatabaseTyp databaseTyp) {
        if(databaseTyp == DatabaseTyp.MYSQL){
            this.executor.execute(new Runnable() {
                public void run() {
                    StatsAPI.getMySQL().executeUpdate(statement);
                }
            });
        }else if(databaseTyp == DatabaseTyp.SQLLITE){
            this.executor.execute(new Runnable() {
                public void run() {
                    StatsAPI.getSqlLite().executeUpdate(statement);
                }
            });
        }
    }

    public void query(final PreparedStatement statement, final Callback<ResultSet> callback, DatabaseTyp databaseTyp) {
        if(databaseTyp == DatabaseTyp.MYSQL){
            this.executor.execute(new Runnable() {
                public void run() {
                    callback.accept(StatsAPI.getMySQL().executeQuery(statement));
                }
            });
        }else if(databaseTyp == DatabaseTyp.SQLLITE){
            this.executor.execute(new Runnable() {
                public void run() {
                    callback.accept(StatsAPI.getSqlLite().executeQuery(statement));
                }
            });
        }
    }
}