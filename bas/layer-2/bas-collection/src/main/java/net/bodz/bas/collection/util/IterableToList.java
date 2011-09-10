package net.bodz.bas.collection.util;

import java.util.List;

import net.bodz.bas.collection.iterator.IterableMX;

public class IterableToList {

    public static <T> List<T> toList(Iterable<T> iterable) {
        return IteratorToList.toList(iterable.iterator());
    }

    public static <T> List<T> toList(Iterable<T> iterable, int appxSize) {
        return IteratorToList.toList(iterable.iterator(), appxSize);
    }

    public static <T> List<T> toListLimited(Iterable<T> iterable, int limit) {
        return IteratorToList.toListLimited(iterable.iterator(), limit);
    }

    public static <T> List<T> toListLimited(Iterable<T> iterable, int limit, int appxSize) {
        return IteratorToList.toListLimited(iterable.iterator(), limit, appxSize);
    }

    public static <T, X extends Exception> List<? extends T> toList(IterableMX<T, X> iterable)
            throws X {
        return IteratorToList.toList(iterable.iterator(false));
    }

    public static <T, X extends Exception> List<? extends T> toList(IterableMX<T, X> iterable, int appxSize)
            throws X {
        return IteratorToList.toList(iterable.iterator(false), appxSize);
    }

    public static <T, X extends Exception> List<? extends T> toListLimited(IterableMX<T, X> iterable, int limit)
            throws X {
        return IteratorToList.toListLimited(iterable.iterator(false), limit);
    }

    public static <T, X extends Exception> List<? extends T> toListLimited(IterableMX<T, X> iterable,
            int limit, int appxSize)
            throws X {
        return IteratorToList.toListLimited(iterable.iterator(false), limit, appxSize);
    }

}
