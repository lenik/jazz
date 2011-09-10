package net.bodz.bas.collection.iterator;

import java.util.Iterator;

public interface IterableX<T, X extends Throwable>
        extends Iterable<T> {

    @Override
    Iterator<T> iterator();

    IteratorX<T, X> iteratorX();

}
