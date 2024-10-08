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
import java.util.Date;

public interface IVariant {

    String format(Object... args);

    Object get();

    <T> T get(T defaultValue);

    default boolean isNull() {
        return get() == null;
    }

    default boolean isNotNull() {
        return get() != null;
    }

    /**
     * Get the value as a scalar.
     *
     * @return If the value is non-array, the value is returned. Otherwise, return the first element
     *         in the array. If the array is empty, returns <code>null</code>, too.
     */
    Object getScalar();

    /**
     * Get the value as a scalar.
     *
     * @return If the value is non-array, the value is returned. Otherwise, return the first element
     *         in the array. If the array is empty, returns <code>null</code>, too.
     *
     *         If there is no entry for the key, defaultValue is returned.
     */
    Object getScalar(Object defaultValue);

    Object[] getArray();

    Object[] getArray(Object[] defaultValue);

    String getString();

    String getString(String defaultValue);

    String[] getStringArray();

    String[] getStringArray(String[] defaultValue);

    // see {@link VariantLookupMapGenerator}

    byte getByte();

    byte getByte(byte defaultValue);

    Byte getByte(Byte defaultValue);

    short getShort();

    short getShort(short defaultValue);

    Short getShort(Short defaultValue);

    int getInt();

    int getInt(int defaultValue);

    Integer getInt(Integer defaultValue);

    long getLong();

    long getLong(long defaultValue);

    Long getLong(Long defaultValue);

    float getFloat();

    float getFloat(float defaultValue);

    Float getFloat(Float defaultValue);

    double getDouble();

    double getDouble(double defaultValue);

    Double getDouble(Double defaultValue);

    boolean getBoolean();

    boolean getBoolean(boolean defaultValue);

    Boolean getBoolean(Boolean defaultValue);

    char getChar();

    char getChar(char defaultValue);

    Character getChar(Character defaultValue);

    BigInteger getBigInteger();

    BigInteger getBigInteger(BigInteger defaultValue);

    BigDecimal getBigDecimal();

    BigDecimal getBigDecimal(BigDecimal defaultValue);

    Date getDate();

    Date getDate(Date defaultValue);

    Instant getInstant();

    Instant getInstant(Instant defaultValue);

    ZonedDateTime getZonedDateTime();

    ZonedDateTime getZonedDateTime(ZonedDateTime defaultValue);

    OffsetDateTime getOffsetDateTime();

    OffsetDateTime getOffsetDateTime(OffsetDateTime defaultValue);

    LocalDateTime getLocalDateTime();

    LocalDateTime getLocalDateTime(LocalDateTime defaultValue);

    LocalDate getLocalDate();

    LocalDate getLocalDate(LocalDate defaultValue);

    LocalTime getLocalTime();

    LocalTime getLocalTime(LocalTime defaultValue);

    <T extends Enum<T>> T getEnum(Class<T> enumType);

    <T extends Enum<T>> T getEnum(Class<T> enumType, T defaultValue);

    default File getFile() {
        String path = getString();
        return new File(path);
    }

    IVariant NULL = new NullVariant();

}
