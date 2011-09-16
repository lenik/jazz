package net.bodz.bas.variant.map;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.cdata.typematrix.TypeMatrix_BigDecimal;
import net.bodz.bas.cdata.typematrix.TypeMatrix_BigInteger;
import net.bodz.bas.cdata.typematrix.TypeMatrix_Date;
import net.bodz.bas.cdata.typematrix.TypeMatrix_boolean;
import net.bodz.bas.cdata.typematrix.TypeMatrix_byte;
import net.bodz.bas.cdata.typematrix.TypeMatrix_char;
import net.bodz.bas.cdata.typematrix.TypeMatrix_double;
import net.bodz.bas.cdata.typematrix.TypeMatrix_float;
import net.bodz.bas.cdata.typematrix.TypeMatrix_int;
import net.bodz.bas.cdata.typematrix.TypeMatrix_long;
import net.bodz.bas.cdata.typematrix.TypeMatrix_short;
import net.bodz.bas.err.TypeConvertException;

/**
 * see {@link cg.VariantLookupMapGenerator}
 */
public abstract class AbstractVariantLookupMap_MatrixImpl<K>
        implements IVariantLookupMap<K> {

    @Override
    public byte getByte(K key) {
        Object value = get(key);
        return TypeMatrix_byte.fromObject(value);
    }

    @Override
    public byte getByte(K key, byte defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_byte.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public short getShort(K key) {
        Object value = get(key);
        return TypeMatrix_short.fromObject(value);
    }

    @Override
    public short getShort(K key, short defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_short.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public int getInt(K key) {
        Object value = get(key);
        return TypeMatrix_int.fromObject(value);
    }

    @Override
    public int getInt(K key, int defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_int.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public long getLong(K key) {
        Object value = get(key);
        return TypeMatrix_long.fromObject(value);
    }

    @Override
    public long getLong(K key, long defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_long.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public float getFloat(K key) {
        Object value = get(key);
        return TypeMatrix_float.fromObject(value);
    }

    @Override
    public float getFloat(K key, float defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_float.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public double getDouble(K key) {
        Object value = get(key);
        return TypeMatrix_double.fromObject(value);
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_double.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public boolean getBoolean(K key) {
        Object value = get(key);
        return TypeMatrix_boolean.fromObject(value);
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_boolean.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public char getChar(K key) {
        Object value = get(key);
        return TypeMatrix_char.fromObject(value);
    }

    @Override
    public char getChar(K key, char defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_char.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigInteger getBigInteger(K key) {
        Object value = get(key);
        return TypeMatrix_BigInteger.fromObject(value);
    }

    @Override
    public BigInteger getBigInteger(K key, BigInteger defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_BigInteger.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public BigDecimal getBigDecimal(K key) {
        Object value = get(key);
        return TypeMatrix_BigDecimal.fromObject(value);
    }

    @Override
    public BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_BigDecimal.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    @Override
    public Date getDate(K key) {
        Object value = get(key);
        return TypeMatrix_Date.fromObject(value);
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_Date.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

}
