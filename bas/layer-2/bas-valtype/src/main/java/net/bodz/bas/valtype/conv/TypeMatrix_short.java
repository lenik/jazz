package net.bodz.bas.valtype.conv;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.exceptions.TypeConvertException;

public class TypeMatrix_short {

    public static short fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return (short) 0;
        if (o instanceof Number)
            return ((Number) o).shortValue();
        return fromString(o.toString());
    }

    public static short fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return (short) 0;
        try {
            return Short.parseShort((String) str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(short value) {
        return (byte) value;
    }

    public static short toShort(short value) {
        return (short) value;
    }

    public static int toInt(short value) {
        return (int) value;
    }

    public static long toLong(short value) {
        return (long) value;
    }

    public static float toFloat(short value) {
        return (float) value;
    }

    public static double toDouble(short value) {
        return (double) value;
    }

    public static boolean toBoolean(short value) {
        return value != 0;
    }

    public static char toChar(short value) {
        return (char) (value & 0xffff);
    }

    public static BigInteger toBigInteger(short value) {
        return BigInteger.valueOf(value);
    }

    public static BigDecimal toBigDecimal(short value) {
        return new BigDecimal(value);
    }

}
