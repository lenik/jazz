package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class NullVariantMap<K>
        extends NullLookupMap<K, Object>
        implements IVariantMap<K> {

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
    public byte getByte(K key) {
        return 0;
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        return defaultValue;
    }

    public Byte getByte(K key, Byte defaultValue) {
        return defaultValue;
    }

    @Override
    public short getShort(K key) {
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
    public int getInt(K key) {
        return 0;
    }

    @Override
    public int getInt(K key, int defaultValue) {
        return defaultValue;
    }

    public Integer getInt(K key, Integer defaultValue) {
        return defaultValue;
    }

    @Override
    public long getLong(K key) {
        return 0;
    }

    @Override
    public long getLong(K key, long defaultValue) {
        return defaultValue;
    }

    public Long getLong(K key, Long defaultValue) {
        return defaultValue;
    }

    @Override
    public float getFloat(K key) {
        return 0;
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        return defaultValue;
    }

    public Float getFloat(K key, Float defaultValue) {
        return defaultValue;
    }

    @Override
    public double getDouble(K key) {
        return 0;
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        return defaultValue;
    }

    public Double getDouble(K key, Double defaultValue) {
        return defaultValue;
    }

    @Override
    public boolean getBoolean(K key) {
        return false;
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        return defaultValue;
    }

    public Boolean getBoolean(K key, Boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public char getChar(K key) {
        return 0;
    }

    @Override
    public char getChar(K key, char defaultValue) {
        return defaultValue;
    }

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

}
