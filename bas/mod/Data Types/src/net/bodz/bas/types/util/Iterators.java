package net.bodz.bas.types.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.lang.Filt1;

public class Iterators {

    static class EmptyIterator<T> implements Iterator<T> {

        public boolean hasNext() {
            return false;
        }

        public T next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private static final EmptyIterator<?> EMPTY = new EmptyIterator<Object>();

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> EMPTY() {
        return (Iterator<T>) EMPTY;
    }

    public static <T> Iterator<T> adapt(final Enumeration<T> enumr) {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return enumr.hasMoreElements();
            }

            @Override
            public T next() {
                return enumr.nextElement();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    public static <T> Iterator<T> adapt(final T[] array) {
        if (array == null)
            throw new NullPointerException("array");
        class ArrayIterator implements Iterator<T> {

            int next = 0;

            @Override
            public boolean hasNext() {
                return next < array.length;
            }

            @Override
            public T next() {
                if (next >= array.length)
                    throw new NoSuchElementException();
                T item = array[next++];
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        }
        return new ArrayIterator();
    }

    public static <T> Iterator<T> concat(final Iterator<T>... iterators) {
        class ConcatIterator extends PrefetchedIterator<T> {

            private int         index;
            private Iterator<T> current;

            @Override
            protected Object fetch() {
                if (current == null) {
                    if (index >= iterators.length)
                        return END;
                    current = iterators[index++];
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
        return new ConcatIterator();
    }

    public static <T> Iterator<T> map(final Iterator<T> iterator,
            final Filt1<T, T> filter) {
        class FilterIterator implements Iterator<T> {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                T item = iterator.next();
                return filter.filter(item);
            }

            @Override
            public void remove() {
                iterator.remove();
            }

        }
        return new FilterIterator();
    }

}
