package net.bodz.bas.t.variant;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import net.bodz.bas.t.tuple.QualifiedName;

public interface IVariantMap<K>
        extends
            ILookupMap<K, Object> {

    String format(K formatKey, Object... args);

    Object get(Object key, Object defaultValue);

    /**
     * Get the value as a scalar.
     *
     * @return If the value is non-array, the value is returned. Otherwise, return the first element
     *         in the array. If the array is empty, returns <code>null</code>, too.
     */
    Object getScalar(K key);

    /**
     * Get the value as a scalar.
     *
     * @return If the value is non-array, the value is returned. Otherwise, return the first element
     *         in the array. If the array is empty, returns <code>null</code>, too.
     *
     *         If there is no entry for the key, defaultValue is returned.
     */
    Object getScalar(K key, Object defaultValue);

    Object[] getArray(K key);

    Object[] getArray(K key, Object[] defaultValue);

    String getString(K key);

    String getString(K key, String defaultString);

    String[] getStringArray(K key);

    String[] getStringArray(K key, String[] defaultValue);

    // see {@link VariantMapGenerator}

    Byte getByte(K key);

    byte getByte(K key, byte defaultValue);

    Byte getByte(K key, Byte defaultValue);

    Short getShort(K key);

    short getShort(K key, short defaultValue);

    Short getShort(K key, Short defaultValue);

    Integer getInt(K key);

    int getInt(K key, int defaultValue);

    Integer getInt(K key, Integer defaultValue);

    Long getLong(K key);

    long getLong(K key, long defaultValue);

    Long getLong(K key, Long defaultValue);

    Float getFloat(K key);

    float getFloat(K key, float defaultValue);

    Float getFloat(K key, Float defaultValue);

    Double getDouble(K key);

    double getDouble(K key, double defaultValue);

    Double getDouble(K key, Double defaultValue);

    Boolean getBoolean(K key);

    boolean getBoolean(K key, boolean defaultValue);

    Boolean getBoolean(K key, Boolean defaultValue);

    Character getChar(K key);

    char getChar(K key, char defaultValue);

    Character getChar(K key, Character defaultValue);

    BigInteger getBigInteger(K key);

    BigInteger getBigInteger(K key, BigInteger defaultValue);

    BigDecimal getBigDecimal(K key);

    BigDecimal getBigDecimal(K key, BigDecimal defaultValue);

    QualifiedName getQName(K key);

    QualifiedName getQName(K key, QualifiedName defaultQName);

    // Date
    Date getDate(DateTimeFormatter format, K key);

    Date getDate(DateTimeFormatter format, K key, Date defaultValue);

    Date getDate(K key);

    Date getDate(K key, Date defaultValue);

//    DateTime getDateTime(DateTimeFormatter format, K key);
//
//    DateTime getDateTime(DateTimeFormatter format, K key, DateTime defaultValue);

    // Instant

    Instant getInstant(DateTimeFormatter format, K key);

    Instant getInstant(DateTimeFormatter format, K key, Instant defaultValue);

    Instant getInstant(K key);

    Instant getInstant(K key, Instant defaultValue);

    // ZonedDateTime

    ZonedDateTime getZonedDateTime(DateTimeFormatter format, K key);

    ZonedDateTime getZonedDateTime(DateTimeFormatter format, K key, ZonedDateTime defaultValue);

    ZonedDateTime getZonedDateTime(K key);

    ZonedDateTime getZonedDateTime(K key, ZonedDateTime defaultValue);

    // OffsetDateTime

    OffsetDateTime getOffsetDateTime(DateTimeFormatter format, K key);

    OffsetDateTime getOffsetDateTime(DateTimeFormatter format, K key, OffsetDateTime defaultValue);

    OffsetDateTime getOffsetDateTime(K key);

    OffsetDateTime getOffsetDateTime(K key, OffsetDateTime defaultValue);

    // LocalDateTime

    LocalDateTime getLocalDateTime(DateTimeFormatter format, K key);

    LocalDateTime getLocalDateTime(DateTimeFormatter format, K key, LocalDateTime defaultValue);

    LocalDateTime getLocalDateTime(K key);

    LocalDateTime getLocalDateTime(K key, LocalDateTime defaultValue);

    // LocalDate

    LocalDate getLocalDate(DateTimeFormatter format, K key);

    LocalDate getLocalDate(DateTimeFormatter format, K key, LocalDate defaultValue);

    LocalDate getLocalDate(K key);

    LocalDate getLocalDate(K key, LocalDate defaultValue);

    // LocalTime

    LocalTime getLocalTime(DateTimeFormatter format, K key);

    LocalTime getLocalTime(DateTimeFormatter format, K key, LocalTime defaultValue);

    LocalTime getLocalTime(K key);

    LocalTime getLocalTime(K key, LocalTime defaultValue);

    File getFile(K key);

    File getFile(K key, File defaultValue);

    <T extends Enum<T>> T getEnum(Class<T> type, K key);

    <T extends Enum<T>> T getEnum(Class<T> type, K key, T defaultValue);

    <T extends Enum<T>> T[] getEnumArray(Class<T> type, K key);

    <T extends Enum<T>> T[] getEnumArray(Class<T> type, K key, T[] defaultValue);

    <T extends Enum<T>> List<T> getEnumList(Class<T> type, K key);

    <T extends Enum<T>> List<T> getEnumList(Class<T> type, K key, List<T> defaultValue);

    <T> T getAny(Class<T> type, K key);

    <T> T getAny(Class<T> type, K key, T defaultValue);

}
