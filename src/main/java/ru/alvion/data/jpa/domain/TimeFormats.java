package ru.alvion.data.jpa.domain;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 */
public class TimeFormats {
    public static final String DD_MM_YYYY_HH_MM = "dd.MM.yyyy HH:mm";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern(TimeFormats.DD_MM_YYYY_HH_MM);
}
