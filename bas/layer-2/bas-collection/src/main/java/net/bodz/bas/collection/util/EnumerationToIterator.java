package net.bodz.bas.collection.util;

import java.util.Enumeration;
import java.util.Iterator;

import net.bodz.bas.collection.iterator.AbstractIteratorM;
import net.bodz.bas.collection.iterator.AbstractIteratorMX;
import net.bodz.bas.collection.iterator.IteratorM;
import net.bodz.bas.collection.iterator.IteratorMX;
import net.bodz.bas.collection.iterator.IteratorTargetException;
import net.bodz.bas.collection.iterator.IteratorX;

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

    public static class Enum2IterX<T, X extends Throwable>
            implements IteratorX<T, X> {

        private final Enumeration<T> enumration;

        public Enum2IterX(Enumeration<T> enumration) {
            if (enumration == null)
                throw new NullPointerException("enumration");
            this.enumration = enumration;
        }

        @Override
        public boolean hasNext() {
            return enumration.hasMoreElements();
        }

        @Override
        public T next()
                throws IteratorTargetException {
            return enumration.nextElement();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static class Enum2ImmIter<T>
            extends AbstractIteratorM<T> {

        private final Enumeration<T> enumration;
        private final boolean isOverlapped;

        public Enum2ImmIter(Enumeration<T> enumration, boolean isOverlapped) {
            if (enumration == null)
                throw new NullPointerException("enumration");
            this.enumration = enumration;
            this.isOverlapped = isOverlapped;
        }

        @Override
        public boolean isOverlapped() {
            return isOverlapped;
        }

        @Override
        public T _next() {
            if (enumration.hasMoreElements())
                return enumration.nextElement();
            else
                return end();
        }

    }

    public static class Enum2ImmIterX<T, X extends Throwable>
            extends AbstractIteratorMX<T, X> {

        private final Enumeration<T> enumration;
        private final boolean isOverlapped;
        private final Class<X> exceptionType;

        public Enum2ImmIterX(Enumeration<T> enumration, boolean isOverlapped, Class<X> exceptionType) {
            if (enumration == null)
                throw new NullPointerException("enumration");
            if (exceptionType == null)
                throw new NullPointerException("exceptionType");
            this.enumration = enumration;
            this.isOverlapped = isOverlapped;
            this.exceptionType = exceptionType;
        }

        @Override
        public boolean isOverlapped() {
            return isOverlapped;
        }

        @Override
        public T _next()
                throws X {
            try {
                if (enumration.hasMoreElements())
                    return enumration.nextElement();
                else
                    return end();
            } catch (IteratorTargetException e) {
                throw exceptionType.cast(e.getCause());
            }
        }

    }

    public static <T> Iterator<T> toIterator(Enumeration<T> e) {
        return new Enum2Iter<T>(e);
    }

    public static <T, X extends Exception> IteratorX<T, X> toIterator(Enumeration<T> e, Class<X> exceptionType) {
        return new Enum2IterX<T, X>(e);
    }

    public static <T> IteratorM<T> toIteratorM(Enumeration<T> e, boolean isOverlapped) {
        return new Enum2ImmIter<T>(e, isOverlapped);
    }

    public static <T, X extends Throwable> IteratorMX<T, X> toIteratorMX(Enumeration<T> e, boolean isOverlapped,
            Class<X> exceptionType) {
        return new Enum2ImmIterX<T, X>(e, isOverlapped, exceptionType);
    }

}
