package de.devsnx.statsapi.database;


import de.devsnx.statsapi.StatsAPI;
import de.devsnx.statsapi.database.utils.DatabaseTyp;

/**
 * @author DevSnx
 * @since 24.07.2023
 */
public class SQLManager {

    public SQLManager(DatabaseTyp databaseTyp){
        if(databaseTyp == DatabaseTyp.MYSQL){
            //create Database Tabelle
            StatsAPI.getMySQL().executeUpdate("CREATE TABLE IF NOT EXISTS `StatsAPI` " +
                    "(UUID VARCHAR(100), " +
                    "Name VARCHAR(100), " +
                    "Kills INT, " +
                    "Deaths INT, " +
                    "Wins INT, " +
                    "Games INT, " +
                    "UNIQUE KEY (UUID))");
        }else if(databaseTyp == DatabaseTyp.SQLLITE){
            StatsAPI.getSqlLite().executeUpdate("CREATE TABLE IF NOT EXISTS StatsAPI ("
                    + "UUID VARCHAR(100) PRIMARY KEY,"
                    + "Name VARCHAR(100),"
                    + "Kills INT,"
                    + "Deaths INT,"
                    + "Wins INT,"
                    + "Games INT)"
            );
        }else{
            StatsAPI.getInstance().getLogger().warning("No Databasetyp found!");
        }

    }





}
