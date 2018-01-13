package com.korobko.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public final class DateTime {
    private DateTime() {
    }

    public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        if (Objects.isNull(timestamp)) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }
}
