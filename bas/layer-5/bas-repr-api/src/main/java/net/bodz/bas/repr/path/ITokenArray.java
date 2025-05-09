package net.bodz.bas.repr.path;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

public interface ITokenArray {

    /**
     * Whether there is a trailing slash.
     *
     * @return <code>true</code> if there is a trailing slash.
     * @see net.bodz.bas.vfs.path.IPath#isEntered()
     */
    boolean isEntered();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean isNotEmpty() {
        return size() > 0;
    }

    String get(int index);

    default String getFirst() {
        return get(0);
    }

    default String getLast() {
        return get(size() - 1);
    }

    @NotNull
    default String[] toArray() {
        int n = size();
        String[] array = new String[n];
        for (int i = 0; i < n; i++)
            array[i] = get(i);
        return array;
    }

    @NotNull
    String getString(int index)
            throws ParseException;

    String getString(int index, String fallback);

    char getChar(int index)
            throws ParseException;

    char getChar(int index, char fallback);

    byte getByte(int index)
            throws ParseException;

    byte getByte(int index, byte fallback);

    short getShort(int index)
            throws ParseException;

    short getShort(int index, short fallback);

    int getInt(int index)
            throws ParseException;

    int getInt(int index, int fallback);

    long getLong(int index)
            throws ParseException;

    long getLong(int index, long fallback);

    float getFloat(int index)
            throws ParseException;

    float getFloat(int index, float fallback);

    double getDouble(int index)
            throws ParseException;

    double getDouble(int index, double fallback);

    boolean getBoolean(int index)
            throws ParseException;

    boolean getBoolean(int index, boolean fallback);

    <E extends Enum<E>> E get(Class<E> enumType, int index)
            throws ParseException;

    <E extends Enum<E>> E get(Class<E> enumType, int index, E fallback);

    BigInteger getBigInteger(int index)
            throws ParseException;

    BigInteger getBigInteger(int index, BigInteger fallback);

    BigDecimal getBigDecimal(int index)
            throws ParseException;

    BigDecimal getBigDecimal(int index, BigDecimal fallback);

    // ZonedDateTime
    default ZonedDateTime getZonedDateTime(int index)
            throws ParseException {
        return getZonedDateTime(index, (IDateTimeParseOptions) null);
    }

    ZonedDateTime getZonedDateTime(int index, IDateTimeParseOptions options)
            throws ParseException;

    default ZonedDateTime getZonedDateTime(int index, ZonedDateTime fallback) {
        return getZonedDateTime(index, null, fallback);
    }

    ZonedDateTime getZonedDateTime(int index, IDateTimeParseOptions options, ZonedDateTime fallback);


    // OffsetDateTime
    default OffsetDateTime getOffsetDateTime(int index)
            throws ParseException {
        return getOffsetDateTime(index, (IDateTimeParseOptions) null);
    }

    OffsetDateTime getOffsetDateTime(int index, IDateTimeParseOptions options)
            throws ParseException;

    default OffsetDateTime getOffsetDateTime(int index, OffsetDateTime fallback) {
        return getOffsetDateTime(index, null, fallback);
    }

    OffsetDateTime getOffsetDateTime(int index, IDateTimeParseOptions options, OffsetDateTime fallback);


    // OffsetTime
    default OffsetTime getOffsetTime(int index)
            throws ParseException {
        return getOffsetTime(index, (IDateTimeParseOptions) null);
    }

    OffsetTime getOffsetTime(int index, IDateTimeParseOptions options)
            throws ParseException;

    default OffsetTime getOffsetTime(int index, OffsetTime fallback) {
        return getOffsetTime(index, null, fallback);
    }

    OffsetTime getOffsetTime(int index, IDateTimeParseOptions options, OffsetTime fallback);


    // LocalDateTime
    default LocalDateTime getLocalDateTime(int index)
            throws ParseException {
        return getLocalDateTime(index, (IDateTimeParseOptions) null);
    }

    LocalDateTime getLocalDateTime(int index, IDateTimeParseOptions options)
            throws ParseException;

    default LocalDateTime getLocalDateTime(int index, LocalDateTime fallback) {
        return getLocalDateTime(index, null, fallback);
    }

    LocalDateTime getLocalDateTime(int index, IDateTimeParseOptions options, LocalDateTime fallback);


    // LocalDate
    default LocalDate getLocalDate(int index)
            throws ParseException {
        return getLocalDate(index, (IDateTimeParseOptions) null);
    }

    LocalDate getLocalDate(int index, IDateTimeParseOptions options)
            throws ParseException;

    default LocalDate getLocalDate(int index, LocalDate fallback) {
        return getLocalDate(index, null, fallback);
    }

    LocalDate getLocalDate(int index, IDateTimeParseOptions options, LocalDate fallback);


    // LocalTime
    default LocalTime getLocalTime(int index)
            throws ParseException {
        return getLocalTime(index, (IDateTimeParseOptions) null);
    }

    LocalTime getLocalTime(int index, IDateTimeParseOptions options)
            throws ParseException;

    default LocalTime getLocalTime(int index, LocalTime fallback) {
        return getLocalTime(index, null, fallback);
    }

    LocalTime getLocalTime(int index, IDateTimeParseOptions options, LocalTime fallback);


    // Instant
    default Instant getInstant(int index)
            throws ParseException {
        return getInstant(index, (IDateTimeParseOptions) null);
    }

    Instant getInstant(int index, IDateTimeParseOptions options)
            throws ParseException;

    default Instant getInstant(int index, Instant fallback) {
        return getInstant(index, null, fallback);
    }

    Instant getInstant(int index, IDateTimeParseOptions options, Instant fallback);


}
