package net.bodz.bas.types.util;

public interface XIterable<T, X extends Throwable> {

    /**
     * Returns an iterator over a set of elements of type T.
     * 
     * @return an Iterator.
     */
    XIterator<T, X> iterator();

}
