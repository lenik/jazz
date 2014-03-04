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

    public static <T> List<T> toList(Enumeration<T> enumeration) {
        return toArrayList(enumeration);
    }

    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... elements) {
        return push(new ArrayList<T>(elements.length), elements);
    }

    public static <T> ArrayList<T> toArrayList(Enumeration<T> enumeration) {
        return push(new ArrayList<T>(), enumeration);
    }

    @SafeVarargs
    public static <T> LinkedList<T> toLinkedList(T... elements) {
        return push(new LinkedList<T>(), elements);
    }

    public static <T> LinkedList<T> toLinkedList(Enumeration<T> enumeration) {
        return push(new LinkedList<T>(), enumeration);
    }

    @SafeVarargs
    public static <T> Set<T> toSet(T... elements) {
        return toHashSet(elements);
    }

    public static <T> Set<T> toSet(Enumeration<T> enumeration) {
        return toHashSet(enumeration);
    }

    @SafeVarargs
    public static <T> HashSet<T> toHashSet(T... elements) {
        return push(new HashSet<T>(), elements);
    }

    public static <T> HashSet<T> toHashSet(Enumeration<T> enumeration) {
        return push(new HashSet<T>(), enumeration);
    }

    @SafeVarargs
    public static <T> TreeSet<T> toTreeSet(T... elements) {
        return push(new TreeSet<T>(), elements);
    }

    public static <T> TreeSet<T> toTreeSet(Enumeration<T> enumeration) {
        return push(new TreeSet<T>(), enumeration);
    }

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

    public static <K, V> Map<K, V> createMap(Boolean order) {
        Map<K, V> map;
        if (order == null)
            map = new LinkedHashMap<>();
        else if (order)
            map = new TreeMap<>();
        else
            map = new HashMap<>();
        return map;
    }

}
