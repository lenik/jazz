package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface IVariantMap<K>
        extends ILookupMap<K, Object> {

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

    // see {@link VariantLookupMapGenerator}

    byte getByte(K key);

    byte getByte(K key, byte defaultValue);

    Byte getByte(K key, Byte defaultValue);

    short getShort(K key);

    short getShort(K key, short defaultValue);

    Short getShort(K key, Short defaultValue);

    int getInt(K key);

    int getInt(K key, int defaultValue);

    Integer getInt(K key, Integer defaultValue);

    long getLong(K key);

    long getLong(K key, long defaultValue);

    Long getLong(K key, Long defaultValue);

    float getFloat(K key);

    float getFloat(K key, float defaultValue);

    Float getFloat(K key, Float defaultValue);

    double getDouble(K key);

    double getDouble(K key, double defaultValue);

    Double getDouble(K key, Double defaultValue);

    boolean getBoolean(K key);

    boolean getBoolean(K key, boolean defaultValue);

    Boolean getBoolean(K key, Boolean defaultValue);

    char getChar(K key);

    char getChar(K key, char defaultValue);

    Character getChar(K key, Character defaultValue);

    BigInteger getBigInteger(K key);

    BigInteger getBigInteger(K key, BigInteger defaultValue);

    BigDecimal getBigDecimal(K key);

    BigDecimal getBigDecimal(K key, BigDecimal defaultValue);

    Date getDate(K key);

    Date getDate(K key, Date defaultValue);

}
