package net.bodz.bas.valtype.conv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.err.TypeConvertException;

public class TypeMatrix_BigInteger {

    public static BigInteger fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return null;
        if (o instanceof BigInteger)
            return ((BigInteger) o);
        if (o instanceof Number)
            return BigInteger.valueOf(((Number) o).longValue());
        return fromString(o.toString());
    }

    public static BigInteger fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return null;
        try {
            return new BigInteger(str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(BigInteger value) {
        if (value == null)
            return 0;
        return value.byteValue();
    }

    public static short toShort(BigInteger value) {
        if (value == null)
            return 0;
        return value.shortValue();
    }

    public static int toInt(BigInteger value) {
        if (value == null)
            return 0;
        return value.intValue();
    }

    public static long toLong(BigInteger value) {
        if (value == null)
            return 0L;
        return value.longValue();
    }

    public static float toFloat(BigInteger value) {
        if (value == null)
            return 0.0f;
        return value.floatValue();
    }

    public static double toDouble(BigInteger value) {
        if (value == null)
            return 0.0;
        return value.doubleValue();
    }

    public static boolean toBoolean(BigInteger value) {
        return value != null && value != BigInteger.ZERO;
    }

    public static char toChar(BigInteger value) {
        return (char) (value.intValue() & 0xff);
    }

    public static BigInteger toBigInteger(BigInteger value) {
        return value;
    }

    public static BigDecimal toBigDecimal(BigInteger value) {
        return new BigDecimal(value);
    }

    public static Date toDate(BigInteger value) {
        return new Date(value.longValue());
    }

}
