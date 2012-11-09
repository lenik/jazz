package net.bodz.bas.c.java.util;

import java.util.*;

public class Collections
        extends _Collections {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    @SafeVarargs
    public static <T> List<T> toList(T... elements) {
        return toArrayList(elements);
    }

    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... elements) {
        return push(new ArrayList<T>(elements.length), elements);
    }

    @SafeVarargs
    public static <T> LinkedList<T> toLinkedList(T... elements) {
        return push(new LinkedList<T>(), elements);
    }

    @SafeVarargs
    public static <T> Set<T> toSet(T... elements) {
        return toHashSet(elements);
    }

    @SafeVarargs
    public static <T> HashSet<T> toHashSet(T... elements) {
        return push(new HashSet<T>(), elements);
    }

    @SafeVarargs
    public static <T> TreeSet<T> toTreeSet(T... elements) {
        return push(new TreeSet<T>(), elements);
    }

    @SafeVarargs
    public static <T, C extends Collection<T>> C push(C collection, T... elements) {
        for (T element : elements)
            collection.add(element);
        return collection;
    }

}
