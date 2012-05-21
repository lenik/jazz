package net.bodz.bas.c.type;

public class TypeKind {

    /**
     * Normal class type is a type which is not primitive, interface, or array.
     * 
     * @return <code>true</code> For normal class.
     */
    public static boolean isNormalClass(Class<?> type) {
        if (type.isArray() || type.isInterface() || type.isPrimitive())
            return false;
        return true;
    }

    /**
     * Whether the type is boolean or its boxed type.
     */
    public static boolean isBoolean(Class<?> type) {
        return type == boolean.class || type == Boolean.class;
    }

    /**
     * Whether the type is byte, short, int, long, or their boxed type.
     */
    public static boolean isInteger(Class<?> type) {
        return type == byte.class //
                || type == short.class //
                || type == int.class //
                || type == long.class //
                || type == Byte.class //
                || type == Short.class //
                || type == Integer.class //
                || type == Long.class;
    }

}
