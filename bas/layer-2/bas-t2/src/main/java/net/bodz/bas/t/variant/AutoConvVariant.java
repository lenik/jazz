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
        return ByteVarConverter.instance.fromObject(value);
    }

    @Override
    public byte getByte(byte defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return ByteVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public short getShort() {
        Object value = getScalar();
        return ShortVarConverter.instance.fromObject(value);
    }

    @Override
    public short getShort(short defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return ShortVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt() {
        Object value = getScalar();
        return IntegerVarConverter.instance.fromObject(value);
    }

    @Override
    public int getInt(int defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return IntegerVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong() {
        Object value = getScalar();
        return LongVarConverter.instance.fromObject(value);
    }

    @Override
    public long getLong(long defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return LongVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public float getFloat() {
        Object value = getScalar();
        return FloatVarConverter.instance.fromObject(value);
    }

    @Override
    public float getFloat(float defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return FloatVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble() {
        Object value = getScalar();
        return DoubleVarConverter.instance.fromObject(value);
    }

    @Override
    public double getDouble(double defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return DoubleVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public boolean getBoolean() {
        Object value = getScalar();
        return BooleanVarConverter.instance.fromObject(value);
    }

    @Override
    public boolean getBoolean(boolean defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BooleanVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public char getChar() {
        Object value = getScalar();
        return CharacterVarConverter.instance.fromObject(value);
    }

    @Override
    public char getChar(char defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return CharacterVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger() {
        Object value = getScalar();
        return BigIntegerVarConverter.instance.fromObject(value);
    }

    @Override
    public BigInteger getBigInteger(BigInteger defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BigIntegerVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal() {
        Object value = getScalar();
        return BigDecimalVarConverter.instance.fromObject(value);
    }

    @Override
    public BigDecimal getBigDecimal(BigDecimal defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return BigDecimalVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate() {
        Object value = getScalar();
        return DateVarConverter.instance.fromObject(value);
    }

    @Override
    public Date getDate(Date defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return DateVarConverter.instance.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

}
