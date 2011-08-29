package net.bodz.bas.collection.iterator;

import java.util.Iterator;

public class ConcatIterator<T>
        extends PrefetchedIterator<T> {

    private Iterator<T>[] iterators;

    private int currentIteratorIndex;
    private Iterator<T> currentIterator;

    public ConcatIterator(Iterator<T>... iterators) {
        if (iterators == null)
            throw new NullPointerException("iterators");
        this.iterators = iterators;
    }

    /**
     * <i> Type safety : A generic array of Iterator<El> is created for a varargs </i>
     */
    @SuppressWarnings("unchecked")
    public ConcatIterator(Iterator<T> it1, Iterator<T> it2) {
        this((Iterator<T>[]) new Iterator<?>[] { it1, it2 });
    }

    @Override
    protected T fetch() {
        if (currentIterator == null) {
            if (currentIteratorIndex >= iterators.length)
                return end();
            currentIterator = iterators[currentIteratorIndex++];
            return fetch();
        }
        if (!currentIterator.hasNext()) {
            currentIterator = null;
            return fetch();
        }
        return currentIterator.next();
    }

    @Override
    public void remove() {
        currentIterator.remove();
    }

}
