package net.bodz.bas.commons.nls_2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.commons.exceptions.TypeConvertException;
import net.bodz.bas.commons.typematrix.TypeMatrix_BigDecimal;
import net.bodz.bas.commons.typematrix.TypeMatrix_BigInteger;
import net.bodz.bas.commons.typematrix.TypeMatrix_Date;
import net.bodz.bas.commons.typematrix.TypeMatrix_boolean;
import net.bodz.bas.commons.typematrix.TypeMatrix_byte;
import net.bodz.bas.commons.typematrix.TypeMatrix_char;
import net.bodz.bas.commons.typematrix.TypeMatrix_double;
import net.bodz.bas.commons.typematrix.TypeMatrix_float;
import net.bodz.bas.commons.typematrix.TypeMatrix_int;
import net.bodz.bas.commons.typematrix.TypeMatrix_long;
import net.bodz.bas.commons.typematrix.TypeMatrix_short;

public abstract class VariantLookupMap<K>
        implements ILookupMap<K, Object> {

    public String format(K formatKey, Object... args) {
        String format = getString(formatKey);
        return String.format(format, args);
    }

    public Object getObject(K key) {
        return get(key);
    }

    public Object getObject(K key, Object defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultValue;
        return value;
    }

    public String getString(K key, String defaultString) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultString;
        return value.toString();
    }

    public String getString(K key) {
        return getString(key, null);
    }

    public String[] getStringArray(K key, String[] defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultValue;
        if (value instanceof String[])
            return (String[]) value;
        return new String[] { value.toString() };
    }

    public String[] getStringArray(K key) {
        return getStringArray(key, null);
    }

    // see PropertyGetMethodsCG

    public byte getByte(K key) {
        Object value = get(key);
        return TypeMatrix_byte.fromObject(value);
    }

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

    public short getShort(K key) {
        Object value = get(key);
        return TypeMatrix_short.fromObject(value);
    }

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

    public int getInt(K key) {
        Object value = get(key);
        return TypeMatrix_int.fromObject(value);
    }

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

    public long getLong(K key) {
        Object value = get(key);
        return TypeMatrix_long.fromObject(value);
    }

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

    public float getFloat(K key) {
        Object value = get(key);
        return TypeMatrix_float.fromObject(value);
    }

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

    public double getDouble(K key) {
        Object value = get(key);
        return TypeMatrix_double.fromObject(value);
    }

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

    public boolean getBoolean(K key) {
        Object value = get(key);
        return TypeMatrix_boolean.fromObject(value);
    }

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

    public char getChar(K key) {
        Object value = get(key);
        return TypeMatrix_char.fromObject(value);
    }

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

    public BigInteger getBigInteger(K key) {
        Object value = get(key);
        return TypeMatrix_BigInteger.fromObject(value);
    }

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

    public BigDecimal getBigDecimal(K key) {
        Object value = get(key);
        return TypeMatrix_BigDecimal.fromObject(value);
    }

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

    public Date getDate(K key) {
        Object value = get(key);
        return TypeMatrix_Date.fromObject(value);
    }

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
