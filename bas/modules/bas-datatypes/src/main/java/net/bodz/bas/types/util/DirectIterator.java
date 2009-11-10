package net.bodz.bas.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Usage:
 * 
 * <pre>
 * DirectIterator i = ...; 
 * while (i.next())) {
 *     T x = i.get(); 
 *     // do with x...
 * }
 * </pre>
 * 
 * The {@link DirectIterator} is compatible with {@link Iterator} except that {@link DirectIterator}
 * won't throw {@link NoSuchElementException} when the iteration has no more element.
 */
public interface DirectIterator<T, X extends Throwable> {

    /**
     * An overlapped iterator will return overlapped iterated values (the returned value includes
     * both the value returned by {@link #get()}, and other useful values in the running context),
     * so you must keep the iterated values by deep-copy, rather than by references.
     */
    boolean isOverlapped();

    /**
     * Fetch the next element, or returns <code>false</code> if no more element.
     */
    boolean next() throws X;

    /**
     * Returns the current iterated value.
     * 
     * Only meaningful if last call to {@link #next()} returns <code>true</code>.
     */
    T get();

    /**
     * @throws NoSuchElementException
     *             if no more element.
     */
    T getNext() throws X, NoSuchElementException;

}
