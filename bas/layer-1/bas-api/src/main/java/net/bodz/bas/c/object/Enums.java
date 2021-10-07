package net.bodz.bas.c.object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Enums {

    public static <T extends Enum<T>> T strictValueOf(Class<T> enumType, int ordinal)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            Method valueOfInt = enumType.getMethod("valueOf", int.class);
            @SuppressWarnings("unchecked")
            T enumVal = (T) valueOfInt.invoke(valueOfInt, ordinal);
            return enumVal;
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IndexOutOfBoundsException)
                throw (IndexOutOfBoundsException) cause;
            else
                throw e;
        } catch (NoSuchMethodException e) {
            throw e;
        }
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, int ordinal) {
        return valueOf(enumType, ordinal, null);
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, int ordinal, T defaultValue) {
        try {
            return strictValueOf(enumType, ordinal);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, Object key) {
        return valueOf(enumType, key, null);
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, Object key, T defaultValue) {
        if (key instanceof Number) {
            Number nVal = (Number) key;
            int iVal = nVal.intValue();
            return Enums.valueOf(enumType, iVal, defaultValue);
        } else {
            String strVal = key.toString();
            try {
                return Enum.valueOf(enumType, strVal);
            } catch (IllegalArgumentException e) {
                return defaultValue;
            }
        }
    }

}
