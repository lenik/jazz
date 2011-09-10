package net.bodz.bas.collection.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.collection.iterator.IteratorMX;

public class IteratorToList {

    static final int defaultAppxSize = 16;

    public static <T> List<T> toList(Iterator<T> iterator) {
        return toList(iterator, defaultAppxSize);
    }

    public static <T> List<T> toList(Iterator<T> iterator, int appxSize) {
        List<T> list = new ArrayList<T>(appxSize);
        while (iterator.hasNext()) {
            T o = iterator.next();
            list.add(o);
        }
        return list;
    }

    public static <T> List<T> toListLimited(Iterator<T> iterator, int limit) {
        return toListLimited(iterator, limit, defaultAppxSize);
    }

    public static <T> List<T> toListLimited(Iterator<T> iterator, int limit, int appxSize) {
        List<T> list = new ArrayList<T>(appxSize);
        while (limit-- > 0 && iterator.hasNext()) {
            T o = iterator.next();
            list.add(o);
        }
        return list;
    }

    public static <T, X extends Exception> List<T> toList(IteratorMX<T, X> iterator)
            throws X {
        return toList(iterator, defaultAppxSize);
    }

    public static <T, X extends Exception> List<T> toList(IteratorMX<T, X> iterator, int appxSize)
            throws X {
        List<T> list = new ArrayList<T>(appxSize);
        T o;
        while ((o = iterator._next()) != null || !iterator.isEnded())
            list.add(iterator.deoverlap(o));
        return list;
    }

    public static <T, X extends Exception> List<T> toListLimited(IteratorMX<T, X> iterator, int limit)
            throws X {
        return toListLimited(iterator, limit, defaultAppxSize);
    }

    public static <T, X extends Exception> List<T> toListLimited(IteratorMX<T, X> iterator, int limit,
            int appxSize)
            throws X {
        List<T> list = new ArrayList<T>(appxSize);
        T o;
        while (limit-- > 0 && ((o = iterator._next()) != null || !iterator.isEnded()))
            list.add(iterator.deoverlap(o));
        return list;
    }

}
