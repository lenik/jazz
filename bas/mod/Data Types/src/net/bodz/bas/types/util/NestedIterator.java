package net.bodz.bas.types.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @see StackedIterator
 * @test NestedIteratorTest
 */
public class NestedIterator<T> implements Iterator<T> {

    private Iterator<Iterator<T>> iterators;
    private Iterator<T>           currentIterator;
    private Iterator<T>           lastIterator;

    public NestedIterator(Iterator<T>... iterators) {
        this(Arrays.asList((Iterator<T>[]) iterators));
    }

    public NestedIterator(Iterable<Iterator<T>> iterators) {
        this(iterators.iterator());
    }

    public NestedIterator(Iterator<Iterator<T>> iterators) {
        this.iterators = iterators;
        nextIter();
    }

    void nextIter() {
        while (iterators.hasNext()) {
            Iterator<T> iter = iterators.next();
            if (iter.hasNext()) {
                currentIterator = iter;
                return;
            }
        }
        currentIterator = null;
    }

    @Override
    public boolean hasNext() {
        return currentIterator != null;
    }

    @Override
    public T next() {
        if (currentIterator == null)
            throw new NoSuchElementException();
        T x = currentIterator.next();
        lastIterator = currentIterator;
        if (!currentIterator.hasNext())
            nextIter();
        return x;
    }

    @Override
    public void remove() {
        if (lastIterator == null)
            throw new IllegalStateException();
        lastIterator.remove();
    }

}
