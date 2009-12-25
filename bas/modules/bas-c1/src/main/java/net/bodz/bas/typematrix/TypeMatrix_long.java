package net.bodz.bas.typematrix;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.exceptions.TypeConvertException;

public class TypeMatrix_long {

    public static long fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return (long) 0;
        if (o instanceof Number)
            return ((Number) o).longValue();
        return fromString(o.toString());
    }

    public static long fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return (long) 0;
        try {
            return Long.parseLong((String) str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(long value) {
        return (byte) value;
    }

    public static short toShort(long value) {
        return (short) value;
    }

    public static int toInt(long value) {
        return (int) value;
    }

    public static long toLong(long value) {
        return (long) value;
    }

    public static float toFloat(long value) {
        return (float) value;
    }

    public static double toDouble(long value) {
        return (double) value;
    }

    public static boolean toBoolean(long value) {
        return value != 0;
    }

    public static char toChar(long value) {
        return (char) (value & 0xff);
    }

    public static BigInteger toBigInteger(long value) {
        return BigInteger.valueOf(value);
    }

    public static BigDecimal toBigDecimal(long value) {
        return new BigDecimal(value);
    }

    public static Date toDate(long value) {
        return new Date(value);
    }

}
