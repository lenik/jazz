package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.*;

// @GeneratedBy(cg.VariantMapGenerator.class)
public abstract class AutoConvVariantMap<K>
        implements IVariantMap<K> {

    @Override
    public byte getByte(K key) {
        Object value = getScalar(key);
        return ByteVarConverter.instance.fromObject(value);
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return ByteVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public short getShort(K key) {
        Object value = getScalar(key);
        return ShortVarConverter.instance.fromObject(value);
    }

    @Override
    public short getShort(K key, short defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return ShortVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt(K key) {
        Object value = getScalar(key);
        return IntegerVarConverter.instance.fromObject(value);
    }

    @Override
    public int getInt(K key, int defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return IntegerVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong(K key) {
        Object value = getScalar(key);
        return LongVarConverter.instance.fromObject(value);
    }

    @Override
    public long getLong(K key, long defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return LongVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public float getFloat(K key) {
        Object value = getScalar(key);
        return FloatVarConverter.instance.fromObject(value);
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return FloatVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble(K key) {
        Object value = getScalar(key);
        return DoubleVarConverter.instance.fromObject(value);
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return DoubleVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public boolean getBoolean(K key) {
        Object value = getScalar(key);
        return BooleanVarConverter.instance.fromObject(value);
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BooleanVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public char getChar(K key) {
        Object value = getScalar(key);
        return CharacterVarConverter.instance.fromObject(value);
    }

    @Override
    public char getChar(K key, char defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return CharacterVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger(K key) {
        Object value = getScalar(key);
        return BigIntegerVarConverter.instance.fromObject(value);
    }

    @Override
    public BigInteger getBigInteger(K key, BigInteger defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BigIntegerVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal(K key) {
        Object value = getScalar(key);
        return BigDecimalVarConverter.instance.fromObject(value);
    }

    @Override
    public BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BigDecimalVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate(K key) {
        Object value = getScalar(key);
        return DateVarConverter.instance.fromObject(value);
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return DateVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

}
