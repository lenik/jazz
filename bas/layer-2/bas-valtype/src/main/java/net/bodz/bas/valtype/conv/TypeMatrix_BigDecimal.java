package net.bodz.bas.valtype.conv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.err.TypeConvertException;

public class TypeMatrix_BigDecimal {

    public static BigDecimal fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return null;
        if (o instanceof BigDecimal)
            return ((BigDecimal) o);
        if (o instanceof Number)
            return BigDecimal.valueOf(((Number) o).doubleValue());
        return fromString(o.toString());
    }

    public static BigDecimal fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return null;
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(BigDecimal value) {
        if (value == null)
            return 0;
        return value.byteValue();
    }

    public static short toShort(BigDecimal value) {
        if (value == null)
            return 0;
        return value.shortValue();
    }

    public static int toInt(BigDecimal value) {
        if (value == null)
            return 0;
        return value.intValue();
    }

    public static long toLong(BigDecimal value) {
        if (value == null)
            return 0L;
        return value.longValue();
    }

    public static float toFloat(BigDecimal value) {
        if (value == null)
            return 0.0f;
        return value.floatValue();
    }

    public static double toDouble(BigDecimal value) {
        if (value == null)
            return 0.0;
        return value.doubleValue();
    }

    public static boolean toBoolean(BigDecimal value) {
        return value != null && value != BigDecimal.ZERO;
    }

    public static char toChar(BigDecimal value) {
        return (char) (value.intValue() & 0xff);
    }

    public static BigInteger toBigInteger(BigDecimal value) {
        if (value == null)
            return null;
        return value.toBigInteger();
    }

    public static BigDecimal toBigDecimal(BigDecimal value) {
        return value;
    }

    public static Date toDate(BigDecimal value) {
        return new Date(value.longValue());
    }

}
