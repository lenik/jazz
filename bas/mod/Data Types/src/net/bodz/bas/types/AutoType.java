package net.bodz.bas.types;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoType {

    public static byte toByte(byte a) {
        return a;
    }

    public static byte toByte(short a) {
        return (byte) a;
    }

    public static byte toByte(int a) {
        return (byte) a;
    }

    public static byte toByte(long a) {
        return (byte) a;
    }

    public static byte toByte(float a) {
        return (byte) (int) a;
    }

    public static byte toByte(double a) {
        return (byte) (int) a;
    }

    public static byte toByte(boolean a) {
        return (byte) (a ? 1 : 0);
    }

    public static byte toByte(char a) {
        return (byte) a; // unsigned
    }

    public static byte toByte(Number n) {
        if (n == null)
            return (byte) 0;
        return n.byteValue();
    }

    public static byte toByte(Boolean b) {
        if (b == null)
            return (byte) 0;
        return (byte) (b ? 1 : 0);
    }

    public static byte toByte(String s) {
        if (s == null)
            return (byte) 0;
        return Byte.parseByte(s);
    }

    public static byte toByte(Object o) {
        if (o instanceof Number)
            return ((Number) o).byteValue();
        if (o == null)
            return (byte) 0;
        return Byte.parseByte(String.valueOf(o));
    }

    public static short toShort(byte a) {
        return a;
    }

    public static short toShort(short a) {
        return a;
    }

    public static short toShort(int a) {
        return (short) a;
    }

    public static short toShort(long a) {
        return (short) a;
    }

    public static short toShort(float a) {
        return (short) a;
    }

    public static short toShort(double a) {
        return (short) a;
    }

    public static short toShort(boolean a) {
        return (short) (a ? 1 : 0);
    }

    public static short toShort(char a) {
        return (short) a;
    }

    public static short toShort(Number n) {
        if (n == null)
            return (short) 0;
        return n.shortValue();
    }

    public static short toShort(Boolean b) {
        if (b == null)
            return (short) 0;
        return (short) (b ? 1 : 0);
    }

    public static short toShort(String s) {
        if (s == null)
            return (short) 0;
        return Short.parseShort(s);
    }

    public static short toShort(Object o) {
        if (o instanceof Number)
            return ((Number) o).shortValue();
        if (o == null)
            return (short) 0;
        return Short.parseShort(String.valueOf(o));
    }

    public static int toInt(byte a) {
        return a;
    }

    public static int toInt(short a) {
        return a;
    }

    public static int toInt(int a) {
        return a;
    }

    public static int toInt(long a) {
        return (int) a;
    }

    public static int toInt(float a) {
        return (int) a;
    }

    public static int toInt(double a) {
        return (int) a;
    }

    public static int toInt(boolean a) {
        return (a ? 1 : 0);
    }

    public static int toInt(char a) {
        return a;
    }

    public static int toInt(Number n) {
        if (n == null)
            return 0;
        return n.intValue();
    }

    public static int toInt(Boolean b) {
        if (b == null)
            return 0;
        return (b ? 1 : 0);
    }

    public static int toInt(String s) {
        if (s == null)
            return 0;
        return Integer.parseInt(s);
    }

    public static int toInt(Object o) {
        if (o instanceof Number)
            return ((Number) o).intValue();
        if (o == null)
            return 0;
        return Integer.parseInt(String.valueOf(o));
    }

    public static long toLong(byte a) {
        return a;
    }

    public static long toLong(short a) {
        return a;
    }

    public static long toLong(int a) {
        return a;
    }

    public static long toLong(long a) {
        return a;
    }

    public static long toLong(float a) {
        return (long) a;
    }

    public static long toLong(double a) {
        return (long) a;
    }

    public static long toLong(boolean a) {
        return (a ? 1 : 0);
    }

    public static long toLong(char a) {
        return a;
    }

    public static long toLong(Number n) {
        if (n == null)
            return 0;
        return n.longValue();
    }

    public static long toLong(Boolean b) {
        if (b == null)
            return 0;
        return (b ? 1 : 0);
    }

    public static long toLong(String s) {
        if (s == null)
            return 0;
        return Long.parseLong(s);
    }

    public static long toLong(Object o) {
        if (o instanceof Number)
            return ((Number) o).longValue();
        if (o == null)
            return 0;
        return Long.parseLong(String.valueOf(o));
    }

    public static float toFloat(byte a) {
        return a;
    }

    public static float toFloat(short a) {
        return a;
    }

    public static float toFloat(int a) {
        return a;
    }

    public static float toFloat(long a) {
        return a;
    }

    public static float toFloat(float a) {
        return a;
    }

    public static float toFloat(double a) {
        return (float) a;
    }

    public static float toFloat(boolean a) {
        return (a ? 1 : 0);
    }

    public static float toFloat(char a) {
        return a;
    }

    public static float toFloat(Number n) {
        if (n == null)
            return 0;
        return n.floatValue();
    }

    public static float toFloat(Boolean b) {
        if (b == null)
            return 0;
        return (b ? 1 : 0);
    }

    public static float toFloat(String s) {
        if (s == null)
            return 0;
        return Float.parseFloat(s);
    }

    public static float toFloat(Object o) {
        if (o instanceof Number)
            return ((Number) o).floatValue();
        if (o == null)
            return 0;
        return Float.parseFloat(String.valueOf(o));
    }

    public static double toDouble(byte a) {
        return a;
    }

    public static double toDouble(short a) {
        return a;
    }

    public static double toDouble(int a) {
        return a;
    }

    public static double toDouble(long a) {
        return a;
    }

    public static double toDouble(float a) {
        return a;
    }

    public static double toDouble(double a) {
        return a;
    }

    public static double toDouble(boolean a) {
        return (a ? 1 : 0);
    }

    public static double toDouble(char a) {
        return a;
    }

    public static double toDouble(Number n) {
        if (n == null)
            return 0;
        return n.doubleValue();
    }

    public static double toDouble(Boolean b) {
        if (b == null)
            return 0;
        return (b ? 1 : 0);
    }

    public static double toDouble(String s) {
        if (s == null)
            return 0;
        return Double.parseDouble(s);
    }

    public static double toDouble(Object o) {
        if (o instanceof Number)
            return ((Number) o).doubleValue();
        if (o == null)
            return 0;
        return Double.parseDouble(String.valueOf(o));
    }

    public static boolean toBoolean(byte a) {
        return a != 0;
    }

    public static boolean toBoolean(short a) {
        return a != 0;
    }

    public static boolean toBoolean(int a) {
        return a != 0;
    }

    public static boolean toBoolean(long a) {
        return a != 0;
    }

    public static boolean toBoolean(float a) {
        return Math.round(a) != 0;
    }

    public static boolean toBoolean(double a) {
        return Math.round(a) != 0;
    }

    public static boolean toBoolean(boolean a) {
        return a;
    }

    public static boolean toBoolean(char a) {
        return a >= '1' && a <= '9';
    }

    public static boolean toBoolean(Number n) {
        if (n == null)
            return false;
        return n.intValue() != 0;
    }

    public static boolean toBoolean(Boolean b) {
        if (b == null)
            return false;
        return b;
    }

    public static boolean toBoolean(String s) {
        if (s == null)
            return false;
        return Boolean.parseBoolean(s);
    }

    public static boolean toBoolean(Object o) {
        if (o instanceof Boolean)
            return ((Boolean) o).booleanValue();
        if (o == null)
            return false;
        return Boolean.parseBoolean(String.valueOf(o));
    }

    public static char toChar(byte a) {
        return (char) a;
    }

    public static char toChar(short a) {
        return (char) a;
    }

    public static char toChar(int a) {
        return (char) a;
    }

    public static char toChar(long a) {
        return (char) a;
    }

    public static char toChar(float a) {
        return (char) a;
    }

    public static char toChar(double a) {
        return (char) a;
    }

    public static char toChar(boolean a) {
        return a ? '1' : '0';
    }

    public static char toChar(char a) {
        return a;
    }

    public static char toChar(Number n) {
        if (n == null)
            return (char) 0;
        return (char) n.intValue();
    }

    public static char toChar(Boolean b) {
        if (b == null)
            return (char) 0;
        return (char) (b ? 1 : 0);
    }

    public static char toChar(String s) {
        if (s == null)
            return (char) 0;
        if (s.length() == 0)
            return (char) 0;
        return s.charAt(0);
    }

    public static char toChar(Object o) {
        if (o instanceof Character)
            return ((Character) o).charValue();
        if (o == null)
            return (char) 0;
        String s = String.valueOf(o);
        if (s.length() == 0)
            return (char) 0;
        return s.charAt(0);
    }

    public static Number toNumber(byte a) {
        return (int) a;
    }

    public static Number toNumber(short a) {
        return a;
    }

    public static Number toNumber(int a) {
        return a;
    }

    public static Number toNumber(long a) {
        return a;
    }

    public static Number toNumber(float a) {
        return a;
    }

    public static Number toNumber(double a) {
        return a;
    }

    public static Number toNumber(boolean a) {
        return a ? 1 : 0;
    }

    public static Number toNumber(char a) {
        return Integer.parseInt(String.valueOf(a));
    }

    public static Number toNumber(Number n) {
        return n;
    }

    public static Number toNumber(Boolean b) {
        if (b == null)
            return null;
        return (b ? 1 : 0);
    }

    public static Number toNumber(String s) {
        if (s == null)
            return null;
        return Double.parseDouble(s);
    }

    public static Number toNumber(Object o) {
        if (o instanceof Number)
            return (Number) o;
        if (o == null)
            return null;
        return Double.parseDouble(String.valueOf(o));
    }

    public static String toString(Object o) {
        if (o == null)
            return null;
        return o.toString();
    }

    public static Date toDate(byte a) {
        return new Date(System.currentTimeMillis() + a);
    }

    public static Date toDate(short a) {
        return new Date(System.currentTimeMillis() + a);
    }

    public static Date toDate(int a) {
        return new Date(System.currentTimeMillis() + a);
    }

    public static Date toDate(long a) {
        return new Date(a);
    }

    public static Date toDate(float a) {
        return new Date((long) a);
    }

    public static Date toDate(double a) {
        return new Date((long) a);
    }

    public static Date toDate(boolean a) {
        return a ? new Date() : null;
    }

    public static Date toDate(char a) {
        return null;
    }

    public static Date toDate(Number n) {
        if (n == null)
            return null;
        return new Date(n.longValue());
    }

    public static Date toDate(Boolean b) {
        if (b == null)
            return null;
        return b ? new Date() : null;
    }

    private static DateFormat DATE_FORMAT;
    static {
        DATE_FORMAT = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
    }

    public static Date toDate(String s) {
        if (s == null)
            return null;
        try {
            return DATE_FORMAT.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public static Date toDate(Object o) {
        if (o == null)
            return null;
        if (o instanceof Number)
            return toDate((Number) o);
        String s = String.valueOf(o);
        return toDate(s);
    }

}
