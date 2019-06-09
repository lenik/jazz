package net.bodz.bas.t.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @see org.apache.commons.collections.iterators.IteratorChain
 */
public class ConcatIterator<T>
        implements Iterator<T> {

    protected final Iterator<? extends Iterator<T>> iterators;

    protected Iterator<T> current = null;

    protected Iterator<T> lastFetched = null;

    @SafeVarargs
    public ConcatIterator(Iterator<T>... iterators) {
        this(Arrays.asList(iterators));
    }

    public ConcatIterator(List<Iterator<T>> iterators) {
        this(iterators.iterator());
    }

    public ConcatIterator(Iterator<? extends Iterator<T>> iterators) {
        this.iterators = iterators;
        if (iterators.hasNext())
            current = iterators.next();
    }

    @Override
    public boolean hasNext() {
        while (current != null) {
            if (current.hasNext())
                return true;
            current = iterators.next();
        }
        return false;
    }

    @Override
    public T next() {
        while (current != null) {
            if (current.hasNext()) {
                lastFetched = current;
                return current.next();
            }
            current = iterators.next();
        }
        throw new NoSuchElementException("end of the iterators.");
    }

    @Override
    public void remove() {
        if (lastFetched == null)
            throw new IllegalStateException("next has not yet been called.");
        lastFetched.remove();
    }

}
