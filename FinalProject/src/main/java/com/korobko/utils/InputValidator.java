package com.korobko.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vova Korobko
 */
public final class InputValidator {
    private InputValidator() {
    }

    public static boolean nonNullnotEmpty(String... parameters) {
        return Arrays.stream(parameters).allMatch(Objects::nonNull) && !Arrays.stream(parameters).allMatch(String::isEmpty);
    }

    public static boolean isPositiveInteger(String routeNumber) {
        return routeNumber.matches("\\d") && Long.valueOf(routeNumber) <= Integer.MAX_VALUE;
    }
}