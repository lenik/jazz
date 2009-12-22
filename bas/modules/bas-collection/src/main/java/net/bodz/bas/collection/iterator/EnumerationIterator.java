package net.bodz.bas.collection.iterator;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Interface adapter to convert an existing {@link Enumeration} to {@link Iterator}.
 */
public class EnumerationIterator<T>
        implements Iterator<T> {

    private final Enumeration<T> enumration;

    public EnumerationIterator(Enumeration<T> enumration) {
        if (enumration == null)
            throw new NullPointerException("enumration");
        this.enumration = enumration;
    }

    @Override
    public boolean hasNext() {
        return enumration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
