package net.bodz.bas.c.enm;

public class Enums {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T getEnum(Class<T> enumClass, String name) {
        // T[] enumConstants = enumClass.getEnumConstants();
        try {
            return (T) Enum.valueOf((Class) enumClass, name);
        } catch (IllegalArgumentException e) {
            // invalid enum name.
            return null;
        }
    }

}
