package net.bodz.bas.t.variant;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

public class NullVariantMap<K>
        extends NullLookupMap<K, Object>
        implements
            IVariantMap<K> {

    @Override
    public String format(K formatKey, Object... args) {
        return null;
    }

    @Override
    public Object get(Object key, Object defaultValue) {
        return defaultValue;
    }

    @Override
    public Object getScalar(K key) {
        return null;
    }

    @Override
    public Object getScalar(K key, Object defaultValue) {
        return defaultValue;
    }

    @Override
    public Object[] getArray(K key) {
        return null;
    }

    @Override
    public Object[] getArray(K key, Object[] defaultValue) {
        return defaultValue;
    }

    @Override
    public String getString(K key) {
        return null;
    }

    @Override
    public String getString(K key, String defaultString) {
        return defaultString;
    }

    @Override
    public String[] getStringArray(K key) {
        return null;
    }

    @Override
    public String[] getStringArray(K key, String[] defaultValue) {
        return defaultValue;
    }

    @Override
    public Byte getByte(K key) {
        return 0;
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        return defaultValue;
    }

    @Override
    public Byte getByte(K key, Byte defaultValue) {
        return defaultValue;
    }

    @Override
    public Short getShort(K key) {
        return 0;
    }

    @Override
    public short getShort(K key, short defaultValue) {
        return defaultValue;
    }

    @Override
    public Short getShort(K key, Short defaultValue) {
        return defaultValue;
    }

    @Override
    public Integer getInt(K key) {
        return 0;
    }

    @Override
    public int getInt(K key, int defaultValue) {
        return defaultValue;
    }

    @Override
    public Integer getInt(K key, Integer defaultValue) {
        return defaultValue;
    }

    @Override
    public Long getLong(K key) {
        return 0L;
    }

    @Override
    public long getLong(K key, long defaultValue) {
        return defaultValue;
    }

    @Override
    public Long getLong(K key, Long defaultValue) {
        return defaultValue;
    }

    @Override
    public Float getFloat(K key) {
        return Float.NaN;
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        return defaultValue;
    }

    @Override
    public Float getFloat(K key, Float defaultValue) {
        return defaultValue;
    }

    @Override
    public Double getDouble(K key) {
        return Double.NaN;
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        return defaultValue;
    }

    @Override
    public Double getDouble(K key, Double defaultValue) {
        return defaultValue;
    }

    @Override
    public Boolean getBoolean(K key) {
        return false;
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public Boolean getBoolean(K key, Boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public Character getChar(K key) {
        return 0;
    }

    @Override
    public char getChar(K key, char defaultValue) {
        return defaultValue;
    }

    @Override
    public Character getChar(K key, Character defaultValue) {
        return defaultValue;
    }

    @Override
    public BigInteger getBigInteger(K key) {
        return null;
    }

    @Override
    public BigInteger getBigInteger(K key, BigInteger defaultValue) {
        return defaultValue;
    }

    @Override
    public BigDecimal getBigDecimal(K key) {
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
        return defaultValue;
    }

    @Override
    public Date getDate(K key) {
        return null;
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        return defaultValue;
    }

    @Override
    public Date getDate(DateFormat format, K key) {
        return null;
    }

    @Override
    public Date getDate(DateFormat format, K key, Date defaultValue) {
        return defaultValue;
    }

    @Override
    public DateTime getDateTime(DateTimeFormatter format, K key) {
        return null;
    }

    @Override
    public DateTime getDateTime(DateTimeFormatter format, K key, DateTime defaultValue) {
        return defaultValue;
    }

    @Override
    public LocalDate getLocalDate(DateTimeFormatter format, K key) {
        return null;
    }

    @Override
    public LocalDate getLocalDate(DateTimeFormatter format, K key, LocalDate defaultValue) {
        return defaultValue;
    }

    @Override
    public LocalDate getLocalDate(K key) {
        return null;
    }

    @Override
    public LocalDate getLocalDate(K key, LocalDate defaultValue) {
        return defaultValue;
    }

    @Override
    public File getFile(K key) {
        return null;
    }

    @Override
    public File getFile(K key, File defaultValue) {
        return defaultValue;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> type, K key) {
        return null;
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> type, K key, T defaultValue) {
        return defaultValue;
    }

    @Override
    public <T> T getAny(Class<T> type, K key) {
        return null;
    }

    @Override
    public <T> T getAny(Class<T> type, K key, T defaultValue) {
        return defaultValue;
    }

}
