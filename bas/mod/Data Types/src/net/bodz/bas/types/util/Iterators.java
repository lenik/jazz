package net.bodz.bas.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.lang.err.IllegalArgumentTypeException;

public class Iterators {

    private static final EmptyIterator<?> EMPTY = new EmptyIterator<Object>();

    @SuppressWarnings("unchecked")
    public <E> Iterator<E> EMPTY() {
        return (Iterator<E>) EMPTY;
    }

    static class EmptyIterator<E> implements Iterator<E> {

        public boolean hasNext() {
            return false;
        }

        public E next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static <E> Iterator<E> concat(Object... iters) {
        return new ConcatIterator<E>(iters);
    }

    static class ConcatIterator<E> extends PrefetchedIterator<E> {

        private Object[]    iters;
        private int         index;
        private Iterator<E> current;

        public ConcatIterator(Object... iters) {
            this.iters = iters;
        }

        @SuppressWarnings("unchecked")
        protected Iterator<E> getIterator(Object iter) {
            if (iter instanceof Iterator)
                return (Iterator<E>) iter;
            else if (iter instanceof Iterable)
                return ((Iterable<E>) iter).iterator();
            else
                throw new IllegalArgumentTypeException(iter,
                        "iterator or iterable");
        }

        @Override
        protected Object fetch() {
            if (iters == null)
                return END;
            if (current == null) {
                if (index >= iters.length)
                    iters = null;
                else
                    current = getIterator(iters[index++]);
                return fetch();
            }
            if (!current.hasNext()) {
                current = null;
                return fetch();
            }
            return current.next();
        }

        @Override
        public void remove() {
            current.remove();
        }

    }

}
