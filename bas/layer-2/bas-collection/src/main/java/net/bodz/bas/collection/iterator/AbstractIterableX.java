package net.bodz.bas.collection.iterator;

import java.util.Iterator;

public abstract class AbstractIterableX<T, X extends Throwable>
        implements IterableX<T, X> {

    @Override
    public final Iterator<T> iterator() {
        IteratorX<T, X> x = iteratorX();
        return Iterators.removeX(x);
    }

}
