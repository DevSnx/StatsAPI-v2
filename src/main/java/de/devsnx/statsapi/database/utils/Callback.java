package de.devsnx.statsapi.database.utils;

/**
 * @author DevSnx
 * @since 24.07.2023
 */
public abstract interface Callback<T> {
    public abstract void accept(T paramT);
}