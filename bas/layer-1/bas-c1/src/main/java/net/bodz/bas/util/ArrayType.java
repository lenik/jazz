package net.bodz.bas.util;

import java.lang.reflect.Array;

public class ArrayType {

    @SuppressWarnings("unchecked")
    public static <T> Class<T[]> getArrayType(Class<T> componentType) {
        Object array = Array.newInstance(componentType, 0);
        return (Class<T[]>) array.getClass();
    }

}
