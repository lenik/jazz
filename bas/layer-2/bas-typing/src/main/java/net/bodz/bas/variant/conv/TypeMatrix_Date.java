package net.bodz.bas.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.bodz.bas.err.TypeConvertException;

public class TypeMatrix_Date {

    public static Date fromObject(Object o)
            throws TypeConvertException {
        if (o == null)
            return null;
        if (o instanceof Date)
            return (Date) o;
        return fromString(o.toString());
    }

    static DateFormat dateFormat;
    static {
        dateFormat = SimpleDateFormat.getDateInstance();
    }

    public static Date fromString(String str)
            throws TypeConvertException {
        if (str == null)
            return null;
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    public static byte toByte(Date value) {
        return (byte) value.getTime();
    }

    public static short toShort(Date value) {
        return (short) value.getTime();
    }

    public static int toInt(Date value) {
        return (int) value.getTime();
    }

    public static long toLong(Date value) {
        return (long) value.getTime();
    }

    public static float toFloat(Date value) {
        return (float) value.getTime();
    }

    public static double toDouble(Date value) {
        return (double) value.getTime();
    }

    public static boolean toBoolean(Date value) {
        return value != null;
    }

    public static char toChar(Date value) {
        return (char) value.getTime();
    }

    public static BigInteger toBigInteger(Date value) {
        return BigInteger.valueOf(value.getTime());
    }

    public static BigDecimal toBigDecimal(Date value) {
        return BigDecimal.valueOf(value.getTime());
    }

}
