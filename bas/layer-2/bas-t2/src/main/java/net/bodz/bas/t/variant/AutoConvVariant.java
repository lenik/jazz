package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.*;

// @GeneratedBy(cg.VariantMapGenerator.class)
public abstract class AutoConvVariant
        implements IVariant {

    @Override
    public byte getByte() {
        Object value = getScalar();
        return ByteVarConverter.instance.from(value);
    }

    @Override
    public byte getByte(byte defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return ByteVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public short getShort() {
        Object value = getScalar();
        return ShortVarConverter.instance.from(value);
    }

    @Override
    public short getShort(short defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return ShortVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt() {
        Object value = getScalar();
        return IntegerVarConverter.instance.from(value);
    }

    @Override
    public int getInt(int defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return IntegerVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong() {
        Object value = getScalar();
        return LongVarConverter.instance.from(value);
    }

    @Override
    public long getLong(long defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return LongVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public float getFloat() {
        Object value = getScalar();
        return FloatVarConverter.instance.from(value);
    }

    @Override
    public float getFloat(float defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return FloatVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble() {
        Object value = getScalar();
        return DoubleVarConverter.instance.from(value);
    }

    @Override
    public double getDouble(double defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return DoubleVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public boolean getBoolean() {
        Object value = getScalar();
        return BooleanVarConverter.instance.from(value);
    }

    @Override
    public boolean getBoolean(boolean defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BooleanVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public char getChar() {
        Object value = getScalar();
        return CharacterVarConverter.instance.from(value);
    }

    @Override
    public char getChar(char defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return CharacterVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger() {
        Object value = getScalar();
        return BigIntegerVarConverter.instance.from(value);
    }

    @Override
    public BigInteger getBigInteger(BigInteger defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BigIntegerVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal() {
        Object value = getScalar();
        return BigDecimalVarConverter.instance.from(value);
    }

    @Override
    public BigDecimal getBigDecimal(BigDecimal defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BigDecimalVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate() {
        Object value = getScalar();
        return DateVarConverter.instance.from(value);
    }

    @Override
    public Date getDate(Date defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return DateVarConverter.instance.from(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

}
