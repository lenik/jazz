package net.bodz.bas.type;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.exceptions.TypeConvertException;

public class TypeMatrix_byte {

    public static byte fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return (byte) 0;
        if (o instanceof Number)
            return ((Number) o).byteValue();
        return fromString(o.toString());
    }

    public static byte fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return (byte) 0;
        try {
            return Byte.parseByte((String) str);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e);
        }
    }

    public static byte toByte(byte value) {
        return (byte) value;
    }

    public static short toShort(byte value) {
        return (short) value;
    }

    public static int toInt(byte value) {
        return (int) value;
    }

    public static long toLong(byte value) {
        return (long) value;
    }

    public static float toFloat(byte value) {
        return (float) value;
    }

    public static double toDouble(byte value) {
        return (double) value;
    }

    public static boolean toBoolean(byte value) {
        return value != 0;
    }

    public static char toChar(byte value) {
        return (char) (value & 0xff);
    }

    public static BigInteger toBigInteger(byte value) {
        return BigInteger.valueOf(value);
    }

    public static BigDecimal toBigDecimal(byte value) {
        return new BigDecimal(value);
    }

}
