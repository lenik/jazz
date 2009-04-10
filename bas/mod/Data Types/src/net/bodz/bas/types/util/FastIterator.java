package net.bodz.bas.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Usage:
 * 
 * <pre>
 * FastIterator i = ...; 
 * while ((x = i.next()) != null || i.hasNext()) {
 *     // do with x...
 * }
 * </pre>
 * 
 * The {@link FastIterator} is compatible with {@link Iterator} except that
 * {@link FastIterator} won't throw {@link NoSuchElementException} when the
 * iteration has no more element.
 */
public interface FastIterator<T> extends Iterator<T> {

    /**
     * Returns the next element in the iteration or <code>null</code> if the
     * iteration has no more element.
     */
    T next();

}
