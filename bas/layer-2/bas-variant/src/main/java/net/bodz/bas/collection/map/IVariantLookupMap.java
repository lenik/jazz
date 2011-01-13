package net.bodz.bas.collection.map;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface IVariantLookupMap<K>
        extends ILookupMap<K, Object> {

    String format(K formatKey, Object... args);

    @Override
    Object get(K key);

    Object get(K key, Object defaultValue);

    String getString(K key, String defaultString);

    String getString(K key);

    String[] getStringArray(K key, String[] defaultValue);

    String[] getStringArray(K key);

    // see {@link VariantLookupMapGenerator}

    byte getByte(K key);

    byte getByte(K key, byte defaultValue);

    short getShort(K key);

    short getShort(K key, short defaultValue);

    int getInt(K key);

    int getInt(K key, int defaultValue);

    long getLong(K key);

    long getLong(K key, long defaultValue);

    float getFloat(K key);

    float getFloat(K key, float defaultValue);

    double getDouble(K key);

    double getDouble(K key, double defaultValue);

    boolean getBoolean(K key);

    boolean getBoolean(K key, boolean defaultValue);

    char getChar(K key);

    char getChar(K key, char defaultValue);

    BigInteger getBigInteger(K key);

    BigInteger getBigInteger(K key, BigInteger defaultValue);

    BigDecimal getBigDecimal(K key);

    BigDecimal getBigDecimal(K key, BigDecimal defaultValue);

    Date getDate(K key);

    Date getDate(K key, Date defaultValue);

}
