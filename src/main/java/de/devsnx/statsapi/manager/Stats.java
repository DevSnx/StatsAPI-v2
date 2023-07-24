package de.devsnx.statsapi.manager;

import java.util.UUID;

/**
 * @author DevSnx
 * @since 24.07.2023
 */
public class Stats {

    private final UUID uuid;
    private String name;
    private int kills;
    private int deaths;
    private int killstreak;
    private int maxkillstreak;
    private int coins;
    private int elo;
    private int wins;
    private int games;

    public Stats(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
        this.kills = 0;
        this.deaths = 0;
        this.killstreak = 0;
        this.maxkillstreak = 0;
        this.coins = 0;
        this.elo = 0;
        this.wins = 0;
        this.games = 0;
        loadData(uuid);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public int getKillstreak() {
        return this.killstreak;
    }

    public int getMaxkillstreak() {
        return this.maxkillstreak;
    }

    public int getCoins() {
        return this.coins;
    }

    public int getElo() {
        return this.elo;
    }

    public int getWins() {
        return this.wins;
    }

    public int getGames() {
        return this.games;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setKillstreak(int killstreak) {
        this.killstreak = killstreak;
    }

    public void setMaxkillstreak(int maxkillstreak) {
        this.maxkillstreak = maxkillstreak;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setGames(int games) {
        this.games = games;
    }

    private void loadData(UUID uuid){

    }

    private void saveData(UUID uuid){

    }
}
