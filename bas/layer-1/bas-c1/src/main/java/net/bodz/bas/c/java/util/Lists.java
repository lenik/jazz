package net.bodz.bas.c.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public class Lists {

    @SafeVarargs
    public static <T> List<T> toList(T... elements) {
        return toArrayList(elements);
    }

    public static <T> List<T> toList(Enumeration<T> enumeration) {
        return toArrayList(enumeration);
    }

    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<T>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }

    public static <T> ArrayList<T> toArrayList(Enumeration<T> enumeration) {
        ArrayList<T> list = new ArrayList<T>();
        while (enumeration.hasMoreElements()) {
            T element = enumeration.nextElement();
            list.add(element);
        }
        return list;
    }

    @SafeVarargs
    public static <T> LinkedList<T> toLinkedList(T... elements) {
        LinkedList<T> list = new LinkedList<T>();
        Collections.addAll(list, elements);
        return list;
    }

    public static <T> LinkedList<T> toLinkedList(Enumeration<T> enumeration) {
        LinkedList<T> list = new LinkedList<T>();
        while (enumeration.hasMoreElements()) {
            T element = enumeration.nextElement();
            list.add(element);
        }
        return list;
    }

    public static <T extends Comparable<? super T>> List<T> sortCopy(Collection<T> collection) {
        List<T> copy = new ArrayList<>(collection);
        Collections.sort(copy);
        return copy;
    }

    public static <T> List<T> sortCopy(Collection<T> collection, Comparator<? super T> comparator) {
        List<T> copy = new ArrayList<>(collection);
        copy.sort(comparator);
        return copy;
    }

    public static <T, U extends T> U binarySearch1(@NotNull List<U> sorted, T key, @Nullable Comparator<? super T> order) {
        int pos = Collections.binarySearch(sorted, key, order);
        if (pos < 0)
            pos = -pos - 1;
        if (pos < sorted.size())
            return sorted.get(pos);
        if (sorted.isEmpty())
            return null;
        else
            return sorted.get(sorted.size() - 1);
    }

}
