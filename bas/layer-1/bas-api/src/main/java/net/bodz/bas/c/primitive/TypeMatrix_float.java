package net.bodz.bas.c.primitive;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.err.TypeConvertException;

public class TypeMatrix_float {

    public static float fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return (float) 0;
        if (o instanceof Number)
            return ((Number) o).floatValue();
        return fromString(o.toString());
    }

    public static float fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return (float) 0;
        try {
            return Float.parseFloat((String) str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(float value) {
        return (byte) value;
    }

    public static short toShort(float value) {
        return (short) value;
    }

    public static int toInt(float value) {
        return (int) value;
    }

    public static long toLong(float value) {
        return (long) value;
    }

    public static float toFloat(float value) {
        return (float) value;
    }

    public static double toDouble(float value) {
        return (double) value;
    }

    public static boolean toBoolean(float value) {
        if (value == 0.0f || value == Float.NaN)
            return false;
        return true;
    }

    public static char toChar(float value) {
        return (char) (value);
    }

    public static BigInteger toBigInteger(float value) {
        return BigInteger.valueOf((long) value);
    }

    public static BigDecimal toBigDecimal(float value) {
        return new BigDecimal(value);
    }

}
