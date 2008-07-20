package net.bodz.bas.lang;

import java.lang.reflect.Method;

public class FastMath {

    public static int unsign(byte b) {
        if (b < 0)
            return 256 + b;
        return b;
    }

    public static int unsign(short s) {
        if (s < 0)
            return 65536 + s;
        return s;
    }

    public static long unsign(int i) {
        if (i < 0)
            return 0x100000000L + i;
        return i;
    }

    public static double unsign(long l) {
        final double LongMax = (double) 0x100000000L * (double) 0x100000000L;
        if (l < 0)
            return LongMax + l;
        return l;
    }

    public static boolean test(byte exp) {
        return exp != 0;
    }

    public static boolean test(short exp) {
        return exp != 0;
    }

    public static boolean test(int exp) {
        return exp != 0;
    }

    public static boolean test(long exp) {
        return exp != 0;
    }

    public static boolean test(float exp) {
        return exp != 0.0f && !Float.isNaN(exp);
    }

    public static boolean test(double exp) {
        return exp != 0.0 && !Double.isNaN(exp);
    }

    public static boolean test(boolean exp) {
        return exp;
    }

    public static boolean test(char exp) {
        return exp != 0;
    }

    public static boolean test(Number exp) {
        return exp.intValue() != 0;
    }

    public static boolean test(Character exp) {
        return exp.charValue() != 0;
    }

    public static boolean test(Boolean exp) {
        return exp.booleanValue();
    }

    public static boolean test(String exp) {
        return exp != null && exp.length() > 0;
    }

    public static boolean test(Object exp) {
        if (exp == null)
            return false;
        Class<?> type = exp.getClass();
        if (type == Object.class)
            return true;
        try {
            // ?? can find test(Number) by test(Integer)?
            Method testf = FastMath.class.getMethod("test", type);
            return (Boolean) testf.invoke(null, exp);
        } catch (NoSuchMethodException e) {
            return true;
        } catch (Exception e) {
            throw new Error(e.getMessage(), e);
        }
    }

    public static int ones(int bits) {
        int c = 0;
        while (bits != 0) {
            bits = bits & (bits - 1);
            c++;
        }
        return c;
    }

    public static int ones(long bits) {
        int c = 0;
        while (bits != 0) {
            bits = bits & (bits - 1);
            c++;
        }
        return c;
    }

    public static int zeros(byte bits) {
        return Byte.SIZE - ones(bits);
    }

    public static int zeros(short bits) {
        return Short.SIZE - ones(bits);
    }

    public static int zeros(int bits) {
        return Integer.SIZE - ones(bits);
    }

    public static int zeros(long bits) {
        return Long.SIZE - ones(bits);
    }

}
