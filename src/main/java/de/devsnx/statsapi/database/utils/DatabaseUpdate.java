package de.devsnx.statsapi.database.utils;

import de.devsnx.statsapi.StatsAPI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DevSnx
 * @since 24.07.2023
 */
public abstract class DatabaseUpdate {

    private boolean update;
    private boolean ready;
    private boolean forceUpdate;
    private List<ReadyExecutor> readyExecutors;

    public DatabaseUpdate() {
        this.readyExecutors = new ArrayList();
        this.forceUpdate = false;
    }

    public List<ReadyExecutor> getReadyExecutors() {
        return this.readyExecutors;
    }

    public void addReadyExecutor(ReadyExecutor exec) {
        if (this.ready) {
            exec.ready();
            return;
        }
        this.readyExecutors.add(exec);
    }

    public boolean isUpdate() {
        return this.update;
    }

    public boolean isReady() {
        return this.ready;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
        if (ready) {
            for (ReadyExecutor exec : this.readyExecutors) {
                exec.ready();
            }
            this.readyExecutors.clear();
        }
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public boolean isForceUpdate() {
        return this.forceUpdate;
    }

    public void addToUpdater(DatabaseTyp databaseTyp) {
        if(databaseTyp == DatabaseTyp.MYSQL){
            StatsAPI.getMySQL().getUpdater().addToUpdater(this);
        }else if(databaseTyp == DatabaseTyp.SQLLITE){
            StatsAPI.getSqlLite().getUpdater().addToUpdater(this);
        }
    }

    public void removeFromUpdater(DatabaseTyp databaseTyp) {
        if(databaseTyp == DatabaseTyp.MYSQL){
            StatsAPI.getMySQL().getUpdater().removeFromUpdater(this);
        }else if(databaseTyp == DatabaseTyp.SQLLITE){
            StatsAPI.getSqlLite().getUpdater().removeFromUpdater(this);
        }
    }

    public abstract void saveData();

    public abstract void saveDataAsync();

    public abstract void loadData();

    public abstract void loadDataAsync();

    public static abstract interface ReadyExecutor {
        public abstract void ready();
    }
}