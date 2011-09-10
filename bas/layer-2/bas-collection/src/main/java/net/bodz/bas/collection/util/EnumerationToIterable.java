package net.bodz.bas.collection.util;

import java.util.Enumeration;
import java.util.Iterator;

import net.bodz.bas.collection.iterator.IterableMX;
import net.bodz.bas.collection.iterator.IteratorMX;
import net.bodz.bas.collection.iterator.IteratorTargetException;
import net.bodz.bas.collection.iterator.IteratorX;

public class EnumerationToIterable<T>
        implements Iterable<T> {

    private Enumeration<T> enumeration;

    EnumerationToIterable(Enumeration<T> enumerator) {
        if (enumerator == null)
            throw new NullPointerException("enumeration");
        this.enumeration = enumerator;
    }

    @Override
    public Iterator<T> iterator() {
        if (enumeration == null)
            throw new IllegalStateException("Already iterated");
        Iterator<T> iterator = EnumerationToIterator.toIterator(enumeration);
        enumeration = null;
        return iterator;
    }

    public static <T> Iterable<T> toIterable(Enumeration<T> enumeration) {
        return new EnumerationToIterable<T>(enumeration);
    }

    public static <T, X extends Exception> IterableMX<T, X> toImmediateIterable(
            final Enumeration<T> enumeration, final boolean isOverlapped, final Class<X> exceptionType) {
        return new IterableMX<T, X>() {
            @Override
            public IteratorMX<? extends T, ? extends X> iterator(boolean allowOverlap)
                    throws X {
                final boolean resultOverlap = isOverlapped;
                if (isOverlapped) {
                    if (!allowOverlap)
                        throw new UnsupportedOperationException();
                }
                return EnumerationToIterator.toIteratorMX(enumeration, resultOverlap, exceptionType);
            }

            @Override
            public IteratorX<T, X> iterator()
                    throws IteratorTargetException {
                return EnumerationToIterator.toIterator(enumeration, exceptionType);
            }
        };
    }

}
