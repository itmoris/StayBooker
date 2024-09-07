package com.staybooker.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.Optional;

@UtilityClass
public class ParseUtil {

    public static String parseString(String value, String defaultValue) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .orElse(defaultValue);
    }

    public static LocalDate parseDate(String value, LocalDate defaultValue) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(LocalDate::parse)
                .orElse(defaultValue);
    }

    public static Integer parseInt(String value, Integer defaultValue) {
        return Optional.ofNullable(value)
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .orElse(defaultValue);
    }
}
