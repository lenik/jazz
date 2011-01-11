package net.bodz.bas.collection.util;

import java.util.Iterator;

public class IteratorToIterable<T>
        implements Iterable<T> {

    private Iterator<T> iterator;

    IteratorToIterable(Iterator<T> iterator) {
        if (iterator == null)
            throw new NullPointerException("iterator");
        this.iterator = iterator;
    }

    @Override
    public Iterator<T> iterator() {
        if (iterator == null)
            throw new IllegalStateException("Already iterated");
        return iterator;
    }

    public static <T> Iterable<T> toIterable(Iterator<T> iterator) {
        return new IteratorToIterable<T>(iterator);
    }

}
