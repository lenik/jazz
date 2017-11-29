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
            return ByteVarConverter.INSTANCE.from(value);
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return ByteVarConverter.INSTANCE.from(value);
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
            return ShortVarConverter.INSTANCE.from(value);
    }

    @Override
    public short getShort(K key, short defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return ShortVarConverter.INSTANCE.from(value);
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
            return IntegerVarConverter.INSTANCE.from(value);
    }

    @Override
    public int getInt(K key, int defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return IntegerVarConverter.INSTANCE.from(value);
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
            return LongVarConverter.INSTANCE.from(value);
    }

    @Override
    public long getLong(K key, long defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return LongVarConverter.INSTANCE.from(value);
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
            return FloatVarConverter.INSTANCE.from(value);
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return FloatVarConverter.INSTANCE.from(value);
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
            return DoubleVarConverter.INSTANCE.from(value);
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return DoubleVarConverter.INSTANCE.from(value);
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
            return BooleanVarConverter.INSTANCE.from(value);
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BooleanVarConverter.INSTANCE.from(value);
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
            return CharacterVarConverter.INSTANCE.from(value);
    }

    @Override
    public char getChar(K key, char defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return CharacterVarConverter.INSTANCE.from(value);
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
            return BigIntegerVarConverter.INSTANCE.from(value);
    }

    @Override
    public BigInteger getBigInteger(K key, BigInteger defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BigIntegerVarConverter.INSTANCE.from(value);
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
            return BigDecimalVarConverter.INSTANCE.from(value);
    }

    @Override
    public BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return BigDecimalVarConverter.INSTANCE.from(value);
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
            return DateVarConverter.INSTANCE.from(value);
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        Object value = getScalar(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return DateVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

}
