package net.bodz.bas.t.variant;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import net.bodz.bas.err.ParseException;

public class DefaultVariantConvertContext
        implements
            IVariantConvertContext {

    @Override
    public Instant parseInstant(String s)
            throws ParseException {
        try {
            return Instant.parse(s);
        } catch (DateTimeParseException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public ZonedDateTime parseZonedDateTime(String s)
            throws ParseException {
        try {
            return ZonedDateTime.parse(s);
        } catch (DateTimeParseException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public OffsetDateTime parseOffsetDateTime(String s)
            throws ParseException {
        try {
            return OffsetDateTime.parse(s);
        } catch (DateTimeParseException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public LocalDateTime parseLocalDateTime(String s)
            throws ParseException {
        try {
            return LocalDateTime.parse(s);
        } catch (DateTimeParseException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public LocalDate parseLocalDate(String s)
            throws ParseException {
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public LocalTime parseLocalTime(String s)
            throws ParseException {
        try {
            return LocalTime.parse(s);
        } catch (DateTimeParseException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public String formatInstant(Instant instant) {
        return instant.toString();
    }

    @Override
    public String formatZonedDateTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toString();
    }

    @Override
    public String formatOffsetDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toString();
    }

    @Override
    public String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    @Override
    public String formatLocalDate(LocalDate localDate) {
        return localDate.toString();
    }

    @Override
    public String formatLocalTime(LocalTime localTime) {
        return localTime.toString();
    }

}
