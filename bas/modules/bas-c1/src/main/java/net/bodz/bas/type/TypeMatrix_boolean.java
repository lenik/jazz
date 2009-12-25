package net.bodz.bas.type;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.exceptions.TypeConvertException;

public class TypeMatrix_boolean {

    public static boolean fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return (boolean) false;
        if (o instanceof Boolean)
            return ((Boolean) o).booleanValue();
        return fromString(o.toString());
    }

    public static boolean fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return (boolean) false;
        try {
            return Boolean.parseBoolean((String) str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(boolean value) {
        return value ? (byte) -1 : 0;
    }

    public static short toShort(boolean value) {
        return value ? (short) -1 : 0;
    }

    public static int toInt(boolean value) {
        return value ? (int) -1 : 0;
    }

    public static long toLong(boolean value) {
        return value ? -1L : 0L;
    }

    public static float toFloat(boolean value) {
        return value ? -1.0f : 0.0f;
    }

    public static double toDouble(boolean value) {
        return value ? -1.0 : 0.0;
    }

    public static boolean toBoolean(boolean value) {
        return value;
    }

    public static char toChar(boolean value) {
        return value ? '\uFFFF' : '\u0000';
    }

    public static BigInteger toBigInteger(boolean value) {
        return BigInteger.valueOf(value ? -1L : 0L);
    }

    public static BigDecimal toBigDecimal(boolean value) {
        return new BigDecimal(value ? -1.0 : 0.0);
    }

}
