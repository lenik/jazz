package net.bodz.bas.util.primitive;

import java.lang.reflect.Method;

public class BoolMath {

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
            Method testf = IntMath.class.getMethod("test", type);
            return (Boolean) testf.invoke(null, exp);
        } catch (NoSuchMethodException e) {
            return true;
        } catch (Exception e) {
            throw new Error(e.getMessage(), e);
        }
    }

    public static boolean and(int bits, int mask) {
        return (bits & mask) != 0;
    }

    public static boolean or(int bits, int mask) {
        return (bits | mask) != 0;
    }

    public static boolean implies(int bits, int mask) {
        return (~bits | mask) != 0;
    }

}
