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
    public Byte getByte(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return ByteVarConverter.instance.from(value);
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return ByteVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Short getShort(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return ShortVarConverter.instance.from(value);
    }

    @Override
    public short getShort(K key, short defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return ShortVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Integer getInt(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return IntegerVarConverter.instance.from(value);
    }

    @Override
    public int getInt(K key, int defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return IntegerVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Long getLong(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return LongVarConverter.instance.from(value);
    }

    @Override
    public long getLong(K key, long defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return LongVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Float getFloat(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return FloatVarConverter.instance.from(value);
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return FloatVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Double getDouble(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return DoubleVarConverter.instance.from(value);
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return DoubleVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Boolean getBoolean(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return BooleanVarConverter.instance.from(value);
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BooleanVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Character getChar(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return CharacterVarConverter.instance.from(value);
    }

    @Override
    public char getChar(K key, char defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return CharacterVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return BigIntegerVarConverter.instance.from(value);
    }

    @Override
    public BigInteger getBigInteger(K key, BigInteger defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BigIntegerVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return BigDecimalVarConverter.instance.from(value);
    }

    @Override
    public BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BigDecimalVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate(K key) {
        Object value = getScalar(key);
        if (value == null)
            return null;
        else
            return DateVarConverter.instance.from(value);
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return DateVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

}
