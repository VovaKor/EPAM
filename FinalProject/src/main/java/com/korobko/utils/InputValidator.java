package com.korobko.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vova Korobko
 */
public final class InputValidator {
    private InputValidator() {
    }

    public static boolean isNullOrEmpty(String ... parameters) {
        return Arrays.stream(parameters).anyMatch(Objects::isNull) || Arrays.stream(parameters).anyMatch(String::isEmpty);
    }

    public static boolean nonInteger(String routeNumber) {
        return routeNumber.matches("\\D") || Long.valueOf(routeNumber) > Integer.MAX_VALUE;
    }
}
