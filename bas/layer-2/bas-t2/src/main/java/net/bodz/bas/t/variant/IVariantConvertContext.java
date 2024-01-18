package net.bodz.bas.t.variant;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import net.bodz.bas.err.ParseException;

public interface IVariantConvertContext {

    Instant parseInstant(String s)
            throws ParseException;

    ZonedDateTime parseZonedDateTime(String s)
            throws ParseException;

    OffsetDateTime parseOffsetDateTime(String s)
            throws ParseException;

    LocalDateTime parseLocalDateTime(String s)
            throws ParseException;

    LocalDate parseLocalDate(String s)
            throws ParseException;

    LocalTime parseLocalTime(String s)
            throws ParseException;

    String formatInstant(Instant instant);

    String formatZonedDateTime(ZonedDateTime zonedDateTime);

    String formatOffsetDateTime(OffsetDateTime offsetDateTime);

    String formatLocalDateTime(LocalDateTime localDateTime);

    String formatLocalDate(LocalDate localDate);

    String formatLocalTime(LocalTime localTime);

}
