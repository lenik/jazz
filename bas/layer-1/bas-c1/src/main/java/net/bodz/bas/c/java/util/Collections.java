package net.bodz.bas.c.java.util;

import java.util.Collection;
import java.util.Enumeration;

public class Collections
        extends _Collections {

    @SafeVarargs
    public static <T, C extends Collection<T>> C push(C collection, T... elements) {
        for (T element : elements)
            collection.add(element);
        return collection;
    }

    public static <T, C extends Collection<T>> C push(C collection, Enumeration<T> enumeration) {
        while (enumeration.hasMoreElements()) {
            T element = enumeration.nextElement();
            collection.add(element);
        }
        return collection;
    }

}
