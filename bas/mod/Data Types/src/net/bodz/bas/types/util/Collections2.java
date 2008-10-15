package net.bodz.bas.types.util;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Collections2 {

    public static <E> List<E> toList(Iterable<E> iterable) {
        List<E> list = new LinkedList<E>();
        for (E e : iterable)
            list.add(e);
        return list;
    }

    public static <E> List<E> toList(Enumeration<E> enumr) {
        List<E> list = new LinkedList<E>();
        while (enumr.hasMoreElements()) {
            E e = enumr.nextElement();
            list.add(e);
        }
        return list;
    }

    public static <E> Set<E> toSet(Iterable<E> iterable) {
        Set<E> set = new HashSet<E>();
        for (E e : iterable)
            set.add(e);
        return set;
    }

    public static <E> Set<E> toSet(Enumeration<E> enumr) {
        Set<E> set = new HashSet<E>();
        while (enumr.hasMoreElements()) {
            E e = enumr.nextElement();
            set.add(e);
        }
        return set;
    }

}
