package net.bodz.bas.c.primitive;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.err.TypeConvertException;

public class TypeMatrix_boolean {

    public static boolean fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return false;
        if (o instanceof Boolean)
            return ((Boolean) o).booleanValue();
        if (o instanceof Number)
            return ((Number) o).intValue() != 0;
        return fromString(o.toString());
    }

    public static boolean fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return false;
        str = str.trim().toLowerCase();
        switch (str) {
        case "true":
        case "on":
        case "1":
            return true;

        case "false":
        case "off":
        case "0":
        case "":
            return false;

        default:
            return false;
        }
    }

    public static byte toByte(boolean value) {
        return value ? (byte) 1 : 0;
    }

    public static short toShort(boolean value) {
        return value ? (short) 1 : 0;
    }

    public static int toInt(boolean value) {
        return value ? (int) 1 : 0;
    }

    public static long toLong(boolean value) {
        return value ? 1L : 0L;
    }

    public static float toFloat(boolean value) {
        return value ? 1.0f : 0.0f;
    }

    public static double toDouble(boolean value) {
        return value ? 1.0 : 0.0;
    }

    public static boolean toBoolean(boolean value) {
        return value;
    }

    public static char toChar(boolean value) {
        return value ? '1' : '0';
    }

    public static BigInteger toBigInteger(boolean value) {
        return value ? BigInteger.ONE : BigInteger.ZERO;
    }

    public static BigDecimal toBigDecimal(boolean value) {
        return value ? BigDecimal.ONE : BigDecimal.ZERO;
    }

}
