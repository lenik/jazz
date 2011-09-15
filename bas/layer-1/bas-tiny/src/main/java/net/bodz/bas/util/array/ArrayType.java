package net.bodz.bas.util.array;

import java.lang.reflect.Array;

public class ArrayType {

    public static <T> Class<T[]> getArrayType(Class<T> componentType) {
        Object array = Array.newInstance(componentType, 0);
        return (Class<T[]>) array.getClass();
    }

}
