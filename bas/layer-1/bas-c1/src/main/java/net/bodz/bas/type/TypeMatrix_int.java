package net.bodz.bas.type;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.exceptions.TypeConvertException;

public class TypeMatrix_int {

    public static int fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return (int) 0;
        if (o instanceof Number)
            return ((Number) o).intValue();
        return fromString(o.toString());
    }

    public static int fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return (int) 0;
        try {
            return Integer.parseInt((String) str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(int value) {
        return (byte) value;
    }

    public static short toShort(int value) {
        return (short) value;
    }

    public static int toInt(int value) {
        return (int) value;
    }

    public static long toLong(int value) {
        return (long) value;
    }

    public static float toFloat(int value) {
        return (float) value;
    }

    public static double toDouble(int value) {
        return (double) value;
    }

    public static boolean toBoolean(int value) {
        return value != 0;
    }

    public static char toChar(int value) {
        return (char) (value & 0xff);
    }

    public static BigInteger toBigInteger(int value) {
        return BigInteger.valueOf(value);
    }

    public static BigDecimal toBigDecimal(int value) {
        return new BigDecimal(value);
    }

}
