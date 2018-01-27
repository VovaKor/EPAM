package com.korobko.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public final class InputValidator {
    private InputValidator() {
    }

    public static boolean nonNullnotEmpty(String... parameters) {
        return Arrays.stream(parameters).allMatch(Objects::nonNull)
                && !Arrays.stream(parameters).allMatch(String::isEmpty);
    }

    public static boolean isPositiveInteger(String routeNumber) {
        return Objects.nonNull(routeNumber)
                && routeNumber.matches("\\d")
                && Long.valueOf(routeNumber) <= Integer.MAX_VALUE;
    }
}