package de.devsnx.statsapi.database.utils;

import de.devsnx.statsapi.database.MySQL;
import de.devsnx.statsapi.database.SQLLite;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 * @author DevSnx
 * @since 24.07.2023
 */
public class StayAliveTask extends Thread {
    private MySQL mySQL;
    private SQLLite sqlLite;
    private boolean active;

    public StayAliveTask(DatabaseTyp databaseTyp) {
        this.mySQL = mySQL;
        this.active = true;
    }

    public MySQL getManager() {
        return this.mySQL;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void run() {
        while (this.active) {
            try {
                Connection conn = this.mySQL.getConnection();
                PreparedStatement st = conn.prepareStatement("/* ping */ SELECT 1");
                st.executeQuery();
                Thread.sleep(300000L);
            } catch (Exception e) {
                setActive(false);
            }
        }
    }
}