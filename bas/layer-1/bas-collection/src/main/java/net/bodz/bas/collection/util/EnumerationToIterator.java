package net.bodz.bas.collection.util;

import java.util.Enumeration;
import java.util.Iterator;

import net.bodz.bas.collection.iterator.AbstractImmediateIterator;
import net.bodz.bas.collection.iterator.ImmediateIterator;

public class EnumerationToIterator {

    /**
     * Interface adapter to convert an existing {@link Enumeration} to {@link Iterator}.
     */
    public static class Enum2Iter<T>
            implements Iterator<T> {

        private final Enumeration<T> enumration;

        public Enum2Iter(Enumeration<T> enumration) {
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

    public static class Enum2ImmIter<T>
            extends AbstractImmediateIterator<T> {

        private final Enumeration<T> enumration;

        public Enum2ImmIter(Enumeration<T> enumration) {
            if (enumration == null)
                throw new NullPointerException("enumration");
            this.enumration = enumration;
        }

        @Override
        public T next() {
            if (enumration.hasMoreElements())
                return enumration.nextElement();
            else
                return end();
        }

    }

    public static <T> Iterator<T> toIterator(Enumeration<T> e) {
        return new Enum2Iter<T>(e);
    }

    public static <T> ImmediateIterator<T> toImmediateIterator(Enumeration<T> e) {
        return new Enum2ImmIter<T>(e);
    }

}
