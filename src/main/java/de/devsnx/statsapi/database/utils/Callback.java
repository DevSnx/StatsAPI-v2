package de.devsnx.statsapi.database.utils;

import javax.annotation.Nullable;

/**
 * @author DevSnx
 * @since 25.07.2023
 */

public interface Callback<T> {

    void handle(@Nullable Exception error, @Nullable T result);

}