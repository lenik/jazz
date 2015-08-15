package net.bodz.bas.t.iterator;

import java.util.Iterator;

public abstract class PrefetchSeed<T>
        extends PrefetchedIterator<T>
        implements Cloneable, Iterable<T> {

    @Override
    public abstract PrefetchedIterator<T> clone();

    @Override
    public Iterator<T> iterator() {
        return clone();
    }

}
