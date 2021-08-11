package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.*;

// @GeneratedBy(cg.VariantMapGenerator.class)
public abstract class AutoConvVariantList
        implements
            IVariantList {

    boolean checkIndex(int index) {
        return index >= 0 && index < size();
    }

    @Override
    public Byte getByte(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return ByteVarConverter.INSTANCE.from(value);
    }

    @Override
    public byte getByte(int index, byte defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return ByteVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Short getShort(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return ShortVarConverter.INSTANCE.from(value);
    }

    @Override
    public short getShort(int index, short defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return ShortVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Integer getInt(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return IntegerVarConverter.INSTANCE.from(value);
    }

    @Override
    public int getInt(int index, int defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return IntegerVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Long getLong(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return LongVarConverter.INSTANCE.from(value);
    }

    @Override
    public long getLong(int index, long defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return LongVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Float getFloat(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return FloatVarConverter.INSTANCE.from(value);
    }

    @Override
    public float getFloat(int index, float defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return FloatVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Double getDouble(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return DoubleVarConverter.INSTANCE.from(value);
    }

    @Override
    public double getDouble(int index, double defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return DoubleVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Boolean getBoolean(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return BooleanVarConverter.INSTANCE.from(value);
    }

    @Override
    public boolean getBoolean(int index, boolean defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return BooleanVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Character getChar(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return CharacterVarConverter.INSTANCE.from(value);
    }

    @Override
    public char getChar(int index, char defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return CharacterVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return BigIntegerVarConverter.INSTANCE.from(value);
    }

    @Override
    public BigInteger getBigInteger(int index, BigInteger defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return BigIntegerVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return BigDecimalVarConverter.INSTANCE.from(value);
    }

    @Override
    public BigDecimal getBigDecimal(int index, BigDecimal defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return BigDecimalVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate(int index) {
        Object value = getScalar(index);
        if (value == null)
            return null;
        else
            return DateVarConverter.INSTANCE.from(value);
    }

    @Override
    public Date getDate(int index, Date defaultValue) {
        Object value = getScalar(index);
        if (value == null && !checkIndex(index))
            return defaultValue;
        try {
            return DateVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

}
