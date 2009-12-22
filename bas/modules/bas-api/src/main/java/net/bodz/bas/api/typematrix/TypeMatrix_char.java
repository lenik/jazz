package net.bodz.bas.api.typematrix;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.api.exceptions.TypeConvertException;

public class TypeMatrix_char {

    public static char fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return (char) 0;
        if (o instanceof Character)
            return ((Character) o).charValue();
        return fromString(o.toString());
    }

    public static char fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return (char) 0;
        if (str.length() == 0)
            return (char) 0;
        return str.charAt(0);
    }

    public static byte toByte(char value) {
        return (byte) value;
    }

    public static short toShort(char value) {
        return (short) value;
    }

    public static int toInt(char value) {
        return (int) value;
    }

    public static long toLong(char value) {
        return (long) value;
    }

    public static float toFloat(char value) {
        return (float) value;
    }

    public static double toDouble(char value) {
        return (double) value;
    }

    public static boolean toBoolean(char value) {
        return value != '\u0000';
    }

    public static char toChar(char value) {
        return (char) (value);
    }

    public static BigInteger toBigInteger(char value) {
        return BigInteger.valueOf((long) value);
    }

    public static BigDecimal toBigDecimal(char value) {
        return new BigDecimal((int) value);
    }

}
