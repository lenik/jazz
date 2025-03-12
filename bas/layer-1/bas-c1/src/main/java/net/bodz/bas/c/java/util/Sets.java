package net.bodz.bas.c.java.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Sets {

    @SafeVarargs
    public static <T> Set<T> toSet(T... elements) {
        return toHashSet(elements);
    }

    public static <T> Set<T> toSet(Enumeration<T> enumeration) {
        return toHashSet(enumeration);
    }

    @SafeVarargs
    public static <T> HashSet<T> toHashSet(T... elements) {
        HashSet<T> set = new HashSet<T>();
        Collections.addAll(set, elements);
        return set;
    }

    public static <T> HashSet<T> toHashSet(Enumeration<T> enumeration) {
        HashSet<T> set = new HashSet<T>();
        while (enumeration.hasMoreElements()) {
            T element = enumeration.nextElement();
            set.add(element);
        }
        return set;
    }

    @SafeVarargs
    public static <T extends Comparator<? super T>> TreeSet<T> toTreeSet(T... elements) {
        TreeSet<T> set = new TreeSet<>();
        Collections.addAll(set, elements);
        return set;
    }

    public static <T extends Comparable<? super T>> TreeSet<T> toTreeSet(Enumeration<T> enumeration) {
        TreeSet<T> set = new TreeSet<>();
        while (enumeration.hasMoreElements()) {
            T element = enumeration.nextElement();
            set.add(element);
        }
        return set;
    }

    @SafeVarargs
    public static <T> TreeSet<T> toTreeSet(Comparator<? super T> c, T... elements) {
        TreeSet<T> set = new TreeSet<>(c);
        Collections.addAll(set, elements);
        return set;
    }

    public static <T> TreeSet<T> toTreeSet(Comparator<? super T> c, Enumeration<T> enumeration) {
        TreeSet<T> set = new TreeSet<>(c);
        while (enumeration.hasMoreElements()) {
            T element = enumeration.nextElement();
            set.add(element);
        }
        return set;
    }

}
