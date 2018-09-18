package net.bodz.bas.t.iterator;

import java.util.Enumeration;
import java.util.Iterator;

public class Enumerations {

    public static <E> Enumeration<E> otp(Iterator<E> iter) {
        return new IteratorEnum<E>(iter);
    }

    public static <E> Enumeration<E> enm(Iterable<E> iterable) {
        return new IteratorEnum<E>(iterable.iterator());
    }

}

class IteratorEnum<E>
        implements Enumeration<E> {

    Iterator<E> it;

    public IteratorEnum(Iterator<E> it) {
        this.it = it;
    }

    @Override
    public boolean hasMoreElements() {
        return it.hasNext();
    }

    @Override
    public E nextElement() {
        return it.next();
    }

}
