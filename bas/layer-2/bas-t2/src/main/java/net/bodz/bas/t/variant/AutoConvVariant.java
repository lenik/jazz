package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.*;

// @GeneratedBy(cg.VariantMapGenerator.class)
public abstract class AutoConvVariant
        implements
            IVariant {

    @Override
    public byte getByte() {
        Object value = getScalar();
        if (value == null)
            return 0;
        return ByteVarConverter.INSTANCE.from(value);
    }

    @Override
    public byte getByte(byte defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return ByteVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public short getShort() {
        Object value = getScalar();
        if (value == null)
            return 0;
        return ShortVarConverter.INSTANCE.from(value);
    }

    @Override
    public short getShort(short defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return ShortVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt() {
        Object value = getScalar();
        if (value == null)
            return 0;
        return IntegerVarConverter.INSTANCE.from(value);
    }

    @Override
    public int getInt(int defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return IntegerVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong() {
        Object value = getScalar();
        if (value == null)
            return 0;
        return LongVarConverter.INSTANCE.from(value);
    }

    @Override
    public long getLong(long defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return LongVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public float getFloat() {
        Object value = getScalar();
        if (value == null)
            return 0;
        return FloatVarConverter.INSTANCE.from(value);
    }

    @Override
    public float getFloat(float defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return FloatVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble() {
        Object value = getScalar();
        if (value == null)
            return 0;
        return DoubleVarConverter.INSTANCE.from(value);
    }

    @Override
    public double getDouble(double defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return DoubleVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public boolean getBoolean() {
        Object value = getScalar();
        if (value == null)
            return false;
        return BooleanVarConverter.INSTANCE.from(value);
    }

    @Override
    public boolean getBoolean(boolean defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BooleanVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public char getChar() {
        Object value = getScalar();
        if (value == null)
            return 0;
        return CharacterVarConverter.INSTANCE.from(value);
    }

    @Override
    public char getChar(char defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return CharacterVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger() {
        Object value = getScalar();
        return BigIntegerVarConverter.INSTANCE.from(value);
    }

    @Override
    public BigInteger getBigInteger(BigInteger defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BigIntegerVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal() {
        Object value = getScalar();
        return BigDecimalVarConverter.INSTANCE.from(value);
    }

    @Override
    public BigDecimal getBigDecimal(BigDecimal defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BigDecimalVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate() {
        Object value = getScalar();
        return DateVarConverter.INSTANCE.from(value);
    }

    @Override
    public Date getDate(Date defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return DateVarConverter.INSTANCE.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

}
