package net.bodz.bas.t.variant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.c.primitive.*;
import net.bodz.bas.err.TypeConvertException;

// @GeneratedBy(cg.VariantMapGenerator.class)
public abstract class AbstractTmVariant
        implements IVariant {

    @Override
    public byte getByte() {
        Object value = getScalar();
        return TypeMatrix_byte.fromObject(value);
    }

    @Override
    public byte getByte(byte defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_byte.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public short getShort() {
        Object value = getScalar();
        return TypeMatrix_short.fromObject(value);
    }

    @Override
    public short getShort(short defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_short.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt() {
        Object value = getScalar();
        return TypeMatrix_int.fromObject(value);
    }

    @Override
    public int getInt(int defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_int.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong() {
        Object value = getScalar();
        return TypeMatrix_long.fromObject(value);
    }

    @Override
    public long getLong(long defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_long.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public float getFloat() {
        Object value = getScalar();
        return TypeMatrix_float.fromObject(value);
    }

    @Override
    public float getFloat(float defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_float.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble() {
        Object value = getScalar();
        return TypeMatrix_double.fromObject(value);
    }

    @Override
    public double getDouble(double defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_double.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public boolean getBoolean() {
        Object value = getScalar();
        return TypeMatrix_boolean.fromObject(value);
    }

    @Override
    public boolean getBoolean(boolean defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_boolean.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public char getChar() {
        Object value = getScalar();
        return TypeMatrix_char.fromObject(value);
    }

    @Override
    public char getChar(char defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_char.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger() {
        Object value = getScalar();
        return TypeMatrix_BigInteger.fromObject(value);
    }

    @Override
    public BigInteger getBigInteger(BigInteger defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_BigInteger.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal() {
        Object value = getScalar();
        return TypeMatrix_BigDecimal.fromObject(value);
    }

    @Override
    public BigDecimal getBigDecimal(BigDecimal defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_BigDecimal.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate() {
        Object value = getScalar();
        return TypeMatrix_Date.fromObject(value);
    }

    @Override
    public Date getDate(Date defaultValue) {
        Object value = getScalar();
        if (value == null)
            return defaultValue;
        try {
            return TypeMatrix_Date.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    // @Override
    public <T> T cast(Class<T> type) {
        return null;
    }

}
