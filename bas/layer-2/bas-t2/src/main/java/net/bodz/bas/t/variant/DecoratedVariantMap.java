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
import java.util.Set;

import net.bodz.bas.t.model.AbstractDecorator;
import net.bodz.bas.t.tuple.QualifiedName;

public class DecoratedVariantMap<K>
        extends AbstractDecorator<IVariantMap<K>>
        implements
            IVariantMap<K> {

    private static final long serialVersionUID = 1L;

    public DecoratedVariantMap(IVariantMap<K> _orig) {
        super(_orig);
    }

    @Override
    public Set<K> keySet() {
        return getWrapped().keySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return getWrapped().containsKey(key);
    }

    @Override
    public String format(K formatKey, Object... args) {
        return getWrapped().format(formatKey, args);
    }

    @Override
    public Object get(Object key) {
        return getWrapped().get(key);
    }

    @Override
    public Object get(Object key, Object defaultValue) {
        return getWrapped().get(key, defaultValue);
    }

    @Override
    public Object getScalar(K key) {
        return getWrapped().getScalar(key);
    }

    @Override
    public Object getScalar(K key, Object defaultValue) {
        return getWrapped().getScalar(key, defaultValue);
    }

    @Override
    public Object[] getArray(K key) {
        return getWrapped().getArray(key);
    }

    @Override
    public Object[] getArray(K key, Object[] defaultValue) {
        return getWrapped().getArray(key, defaultValue);
    }

    @Override
    public String getString(K key) {
        return getWrapped().getString(key);
    }

    @Override
    public String getString(K key, String defaultString) {
        return getWrapped().getString(key, defaultString);
    }

    @Override
    public String[] getStringArray(K key) {
        return getWrapped().getStringArray(key);
    }

    @Override
    public String[] getStringArray(K key, String[] defaultValue) {
        return getWrapped().getStringArray(key, defaultValue);
    }

    @Override
    public Byte getByte(K key) {
        return getWrapped().getByte(key);
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        return getWrapped().getByte(key, defaultValue);
    }

    @Override
    public Byte getByte(K key, Byte defaultValue) {
        return getWrapped().getByte(key, defaultValue);
    }

    @Override
    public Short getShort(K key) {
        return getWrapped().getShort(key);
    }

    @Override
    public short getShort(K key, short defaultValue) {
        return getWrapped().getShort(key, defaultValue);
    }

    @Override
    public Short getShort(K key, Short defaultValue) {
        return getWrapped().getShort(key, defaultValue);
    }

    @Override
    public Integer getInt(K key) {
        return getWrapped().getInt(key);
    }

    @Override
    public int getInt(K key, int defaultValue) {
        return getWrapped().getInt(key, defaultValue);
    }

    @Override
    public Integer getInt(K key, Integer defaultValue) {
        return getWrapped().getInt(key, defaultValue);
    }

    @Override
    public Long getLong(K key) {
        return getWrapped().getLong(key);
    }

    @Override
    public long getLong(K key, long defaultValue) {
        return getWrapped().getLong(key, defaultValue);
    }

    @Override
    public Long getLong(K key, Long defaultValue) {
        return getWrapped().getLong(key, defaultValue);
    }

    @Override
    public Float getFloat(K key) {
        return getWrapped().getFloat(key);
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        return getWrapped().getFloat(key, defaultValue);
    }

    @Override
    public Float getFloat(K key, Float defaultValue) {
        return getWrapped().getFloat(key, defaultValue);
    }

    @Override
    public Double getDouble(K key) {
        return getWrapped().getDouble(key);
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        return getWrapped().getDouble(key, defaultValue);
    }

    @Override
    public Double getDouble(K key, Double defaultValue) {
        return getWrapped().getDouble(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(K key) {
        return getWrapped().getBoolean(key);
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        return getWrapped().getBoolean(key, defaultValue);
    }

    @Override
    public Boolean getBoolean(K key, Boolean defaultValue) {
        return getWrapped().getBoolean(key, defaultValue);
    }

    @Override
    public Character getChar(K key) {
        return getWrapped().getChar(key);
    }

    @Override
    public char getChar(K key, char defaultValue) {
        return getWrapped().getChar(key, defaultValue);
    }

    @Override
    public Character getChar(K key, Character defaultValue) {
        return getWrapped().getChar(key, defaultValue);
    }

    @Override
    public BigInteger getBigInteger(K key) {
        return getWrapped().getBigInteger(key);
    }

    @Override
    public BigInteger getBigInteger(K key, BigInteger defaultValue) {
        return getWrapped().getBigInteger(key, defaultValue);
    }

    @Override
    public BigDecimal getBigDecimal(K key) {
        return getWrapped().getBigDecimal(key);
    }

    @Override
    public BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
        return getWrapped().getBigDecimal(key, defaultValue);
    }

    @Override
    public QualifiedName getQName(K key) {
        return getWrapped().getQName(key);
    }

    @Override
    public QualifiedName getQName(K key, QualifiedName defaultQName) {
        return getWrapped().getQName(key, defaultQName);
    }

    @Override
    public Date getDate(K key) {
        return getWrapped().getDate(key);
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        return getWrapped().getDate(key, defaultValue);
    }

    @Override
    public Date getDate(DateTimeFormatter format, K key) {
        return getWrapped().getDate(format, key);
    }

    @Override
    public Date getDate(DateTimeFormatter format, K key, Date defaultValue) {
        return getWrapped().getDate(format, key, defaultValue);
    }

    // Instant

    @Override
    public Instant getInstant(DateTimeFormatter format, K key) {
        return getWrapped().getInstant(format, key);
    }

    @Override
    public Instant getInstant(DateTimeFormatter format, K key, Instant defaultValue) {
        return getWrapped().getInstant(format, key, defaultValue);
    }

    @Override
    public Instant getInstant(K key) {
        return getWrapped().getInstant(key);
    }

    @Override
    public Instant getInstant(K key, Instant defaultValue) {
        return getWrapped().getInstant(key, defaultValue);
    }

    // ZonedDateTime

    @Override
    public ZonedDateTime getZonedDateTime(DateTimeFormatter format, K key) {
        return getWrapped().getZonedDateTime(format, key);
    }

    @Override
    public ZonedDateTime getZonedDateTime(DateTimeFormatter format, K key, ZonedDateTime defaultValue) {
        return getWrapped().getZonedDateTime(format, key, defaultValue);
    }

    @Override
    public ZonedDateTime getZonedDateTime(K key) {
        return getWrapped().getZonedDateTime(key);
    }

    @Override
    public ZonedDateTime getZonedDateTime(K key, ZonedDateTime defaultValue) {
        return getWrapped().getZonedDateTime(key, defaultValue);
    }

    // OffsetDateTime

    @Override
    public OffsetDateTime getOffsetDateTime(DateTimeFormatter format, K key) {
        return getWrapped().getOffsetDateTime(format, key);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(DateTimeFormatter format, K key, OffsetDateTime defaultValue) {
        return getWrapped().getOffsetDateTime(format, key, defaultValue);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(K key) {
        return getWrapped().getOffsetDateTime(key);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(K key, OffsetDateTime defaultValue) {
        return getWrapped().getOffsetDateTime(key, defaultValue);
    }

    // LocalDateTime

    @Override
    public LocalDateTime getLocalDateTime(DateTimeFormatter format, K key) {
        return getWrapped().getLocalDateTime(format, key);
    }

    @Override
    public LocalDateTime getLocalDateTime(DateTimeFormatter format, K key, LocalDateTime defaultValue) {
        return getWrapped().getLocalDateTime(format, key, defaultValue);
    }

    @Override
    public LocalDateTime getLocalDateTime(K key) {
        return getWrapped().getLocalDateTime(key);
    }

    @Override
    public LocalDateTime getLocalDateTime(K key, LocalDateTime defaultValue) {
        return getWrapped().getLocalDateTime(key, defaultValue);
    }

    // LocalDate

    @Override
    public LocalDate getLocalDate(DateTimeFormatter format, K key) {
        return getWrapped().getLocalDate(format, key);
    }

    @Override
    public LocalDate getLocalDate(DateTimeFormatter format, K key, LocalDate defaultValue) {
        return getWrapped().getLocalDate(format, key, defaultValue);
    }

    @Override
    public LocalDate getLocalDate(K key) {
        return getWrapped().getLocalDate(key);
    }

    @Override
    public LocalDate getLocalDate(K key, LocalDate defaultValue) {
        return getWrapped().getLocalDate(key, defaultValue);
    }

    // LocalTime

    @Override
    public LocalTime getLocalTime(DateTimeFormatter format, K key) {
        return getWrapped().getLocalTime(format, key);
    }

    @Override
    public LocalTime getLocalTime(DateTimeFormatter format, K key, LocalTime defaultValue) {
        return getWrapped().getLocalTime(format, key, defaultValue);
    }

    @Override
    public LocalTime getLocalTime(K key) {
        return getWrapped().getLocalTime(key);
    }

    @Override
    public LocalTime getLocalTime(K key, LocalTime defaultValue) {
        return getWrapped().getLocalTime(key, defaultValue);
    }

    @Override
    public File getFile(K key) {
        return getWrapped().getFile(key);
    }

    @Override
    public File getFile(K key, File defaultValue) {
        return getWrapped().getFile(key, defaultValue);
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> type, K key) {
        return getWrapped().getEnum(type, key);
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> type, K key, T defaultValue) {
        return getWrapped().getEnum(type, key, defaultValue);
    }

    @Override
    public <T extends Enum<T>> T[] getEnumArray(Class<T> type, K key) {
        return getWrapped().getEnumArray(type, key);
    }

    @Override
    public <T extends Enum<T>> T[] getEnumArray(Class<T> type, K key, T[] defaultValue) {
        return getWrapped().getEnumArray(type, key, defaultValue);
    }

    @Override
    public <T extends Enum<T>> List<T> getEnumList(Class<T> type, K key) {
        return getWrapped().getEnumList(type, key);
    }

    @Override
    public <T extends Enum<T>> List<T> getEnumList(Class<T> type, K key, List<T> defaultValue) {
        return getWrapped().getEnumList(type, key, defaultValue);
    }

    @Override
    public <T> T getAny(Class<T> type, K key) {
        return getWrapped().getAny(type, key);
    }

    @Override
    public <T> T getAny(Class<T> type, K key, T defaultValue) {
        return getWrapped().getAny(type, key, defaultValue);
    }

}
