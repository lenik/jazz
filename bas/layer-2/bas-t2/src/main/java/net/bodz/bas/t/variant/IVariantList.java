package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface IVariantList
        extends
            ILookupList<Object> {

    String format(int formatIndex, Object... args);

    Object get(int index, Object defaultValue);

    /**
     * Get the value as a scalar.
     *
     * @return If the value is non-array, the value is returned. Otherwise, return the first element
     *         in the array. If the array is empty, returns <code>null</code>, too.
     */
    Object getScalar(int key);

    /**
     * Get the value as a scalar.
     *
     * @return If the value is non-array, the value is returned. Otherwise, return the first element
     *         in the array. If the array is empty, returns <code>null</code>, too.
     *
     *         If there is no entry for the key, defaultValue is returned.
     */
    Object getScalar(int key, Object defaultValue);

    Object[] getArray(int key);

    Object[] getArray(int key, Object[] defaultValue);

    String getString(int key);

    String getString(int key, String defaultString);

    String[] getStringArray(int key);

    String[] getStringArray(int key, String[] defaultValue);

    // see {@link VariantMapGenerator}

    Byte getByte(int key);

    byte getByte(int key, byte defaultValue);

    Byte getByte(int key, Byte defaultValue);

    Short getShort(int key);

    short getShort(int key, short defaultValue);

    Short getShort(int key, Short defaultValue);

    Integer getInt(int key);

    int getInt(int key, int defaultValue);

    Integer getInt(int key, Integer defaultValue);

    Long getLong(int key);

    long getLong(int key, long defaultValue);

    Long getLong(int key, Long defaultValue);

    Float getFloat(int key);

    float getFloat(int key, float defaultValue);

    Float getFloat(int key, Float defaultValue);

    Double getDouble(int key);

    double getDouble(int key, double defaultValue);

    Double getDouble(int key, Double defaultValue);

    Boolean getBoolean(int key);

    boolean getBoolean(int key, boolean defaultValue);

    Boolean getBoolean(int key, Boolean defaultValue);

    Character getChar(int key);

    char getChar(int key, char defaultValue);

    Character getChar(int key, Character defaultValue);

    BigInteger getBigInteger(int key);

    BigInteger getBigInteger(int key, BigInteger defaultValue);

    BigDecimal getBigDecimal(int key);

    BigDecimal getBigDecimal(int key, BigDecimal defaultValue);

    Date getDate(int key);

    Date getDate(int key, Date defaultValue);

}
