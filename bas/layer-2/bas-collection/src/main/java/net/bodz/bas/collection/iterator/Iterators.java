package net.bodz.bas.collection.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.util.exception.Err;

public class Iterators {

    static class EmptyIterator<T>
            implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        static final EmptyIterator<?> EMPTY = new EmptyIterator<Object>();

    }

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> empty() {
        return (Iterator<T>) EmptyIterator.EMPTY;
    }

    static class IteratorX2_<T>
            implements Iterator<T> {

        final IteratorX<T, ? extends Throwable> x;

        public IteratorX2_(IteratorX<T, ? extends Throwable> x) {
            if (x == null)
                throw new NullPointerException("x");
            this.x = x;
        }

        @Override
        public boolean hasNext() {
            try {
                return x.hasNext();
            } catch (Throwable e) {
                throw Err.throwOrWrap(IteratorTargetException.class, e);
            }
        }

        @Override
        public T next() {
            try {
                return x.next();
            } catch (Throwable e) {
                throw Err.throwOrWrap(IteratorTargetException.class, e);
            }
        }

        @Override
        public void remove() {
            try {
                x.remove();
            } catch (Throwable e) {
                throw Err.throwOrWrap(IteratorTargetException.class, e);
            }
        }

    }

    public static <T> Iterator<T> removeX(IteratorX<T, ? extends Throwable> x) {
        return new IteratorX2_<T>(x);
    }

}
