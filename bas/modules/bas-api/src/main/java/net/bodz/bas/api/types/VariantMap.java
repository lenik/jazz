package net.bodz.bas.api.types;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.api.exceptions.TypeConvertException;
import net.bodz.bas.api.typematrix.TypeMatrix_BigDecimal;
import net.bodz.bas.api.typematrix.TypeMatrix_BigInteger;
import net.bodz.bas.api.typematrix.TypeMatrix_Date;
import net.bodz.bas.api.typematrix.TypeMatrix_boolean;
import net.bodz.bas.api.typematrix.TypeMatrix_byte;
import net.bodz.bas.api.typematrix.TypeMatrix_char;
import net.bodz.bas.api.typematrix.TypeMatrix_double;
import net.bodz.bas.api.typematrix.TypeMatrix_float;
import net.bodz.bas.api.typematrix.TypeMatrix_int;
import net.bodz.bas.api.typematrix.TypeMatrix_long;
import net.bodz.bas.api.typematrix.TypeMatrix_short;

public abstract class VariantMap
        implements SimpleMap {

    public String format(String formatKey, Object... args) {
        String format = getString(formatKey);
        return String.format(format, args);
    }

    public Object getObject(String key) {
        return get(key);
    }

    public Object getObject(String key, Object defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultValue;
        return value;
    }

    public String getString(String key, String defaultString) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultString;
        return value.toString();
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String[] getStringArray(String key, String[] defaultValue) {
        Object value = get(key);
        if (value == null)
            return containsKey(key) ? null : defaultValue;
        if (value instanceof String[])
            return (String[]) value;
        return new String[] { value.toString() };
    }

    public String[] getStringArray(String key) {
        return getStringArray(key, null);
    }

    // see PropertyGetMethodsCG

    public byte getByte(String key) {
        Object value = get(key);
        return TypeMatrix_byte.fromObject(value);
    }

    public byte getByte(String key, byte defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_byte.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public short getShort(String key) {
        Object value = get(key);
        return TypeMatrix_short.fromObject(value);
    }

    public short getShort(String key, short defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_short.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public int getInt(String key) {
        Object value = get(key);
        return TypeMatrix_int.fromObject(value);
    }

    public int getInt(String key, int defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_int.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public long getLong(String key) {
        Object value = get(key);
        return TypeMatrix_long.fromObject(value);
    }

    public long getLong(String key, long defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_long.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public float getFloat(String key) {
        Object value = get(key);
        return TypeMatrix_float.fromObject(value);
    }

    public float getFloat(String key, float defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_float.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public double getDouble(String key) {
        Object value = get(key);
        return TypeMatrix_double.fromObject(value);
    }

    public double getDouble(String key, double defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_double.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key) {
        Object value = get(key);
        return TypeMatrix_boolean.fromObject(value);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_boolean.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public char getChar(String key) {
        Object value = get(key);
        return TypeMatrix_char.fromObject(value);
    }

    public char getChar(String key, char defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_char.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public BigInteger getBigInteger(String key) {
        Object value = get(key);
        return TypeMatrix_BigInteger.fromObject(value);
    }

    public BigInteger getBigInteger(String key, BigInteger defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_BigInteger.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public BigDecimal getBigDecimal(String key) {
        Object value = get(key);
        return TypeMatrix_BigDecimal.fromObject(value);
    }

    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        Object value = get(key);
        if (value == null && !containsKey(key))
            return defaultValue;
        try {
            return TypeMatrix_BigDecimal.fromObject(value);
        } catch (TypeConvertException e) {
            return defaultValue;
        }
    }

    public Date getDate(String key) {
        Object value = get(key);
        return TypeMatrix_Date.fromObject(value);
    }

    public Date getDate(String key, Date defaultValue) {
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
