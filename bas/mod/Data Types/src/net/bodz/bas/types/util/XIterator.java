package net.bodz.bas.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @see Iterator
 */
public interface XIterator<E, X extends Throwable> {

    /**
     * Returns {@code true} if the iteration has more elements. (In other words,
     * returns {@code true} if {@link #next} would return an element rather than
     * throwing an exception.)
     * 
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext() throws X;

    /**
     * Returns the next element in the iteration.
     * 
     * @return the next element in the iteration
     * @throws NoSuchElementException
     *             if the iteration has no more elements
     */
    E next() throws X;

    /**
     * Removes from the underlying collection the last element returned by this
     * iterator (optional operation). This method can be called only once per
     * call to {@link #next}. The behavior of an iterator is unspecified if the
     * underlying collection is modified while the iteration is in progress in
     * any way other than by calling this method.
     * 
     * @throws UnsupportedOperationException
     *             if the {@code remove} operation is not supported by this
     *             iterator
     * 
     * @throws IllegalStateException
     *             if the {@code next} method has not yet been called, or the
     *             {@code remove} method has already been called after the last
     *             call to the {@code next} method
     */
    void remove() throws X;

}
