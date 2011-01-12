package net.bodz.bas.valtype.conv;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.util.exception.TypeConvertException;

public class TypeMatrix_double {

    public static double fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return (double) 0;
        if (o instanceof Number)
            return ((Number) o).doubleValue();
        return fromString(o.toString());
    }

    public static double fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return (double) 0;
        try {
            return Double.parseDouble((String) str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(double value) {
        return (byte) value;
    }

    public static short toShort(double value) {
        return (short) value;
    }

    public static int toInt(double value) {
        return (int) value;
    }

    public static long toLong(double value) {
        return (long) value;
    }

    public static float toFloat(double value) {
        return (float) value;
    }

    public static double toDouble(double value) {
        return (double) value;
    }

    public static boolean toBoolean(double value) {
        if (value == 0 || value == Double.NaN)
            return false;
        return true;
    }

    public static char toChar(double value) {
        return (char) (value);
    }

    public static BigInteger toBigInteger(double value) {
        return BigInteger.valueOf((long) value);
    }

    public static BigDecimal toBigDecimal(double value) {
        return new BigDecimal(value);
    }

}
