package net.bodz.bas.types.util;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.lang.err.IllegalArgumentTypeException;
import net.bodz.bas.lang.err.IllegalUsageError;

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

    public static <E> Iterator<E> iterator(final Enumeration<E> enumr) {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return enumr.hasMoreElements();
            }

            @Override
            public E next() {
                return enumr.nextElement();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    static class OneTimeIterable<E> implements Iterable<E> {

        private Iterator<E> iter;

        public OneTimeIterable(Iterator<E> iter) {
            assert iter != null;
            this.iter = iter;
        }

        @Override
        public Iterator<E> iterator() {
            if (this.iter == null)
                throw new IllegalStateException("already been iterated");
            Iterator<E> iter = this.iter;
            this.iter = null;
            return iter;
        }

    }

    public static <E> Iterable<E> iterate(Iterator<E> iter) {
        return new OneTimeIterable<E>(iter);
    }

    public static <E> Iterable<E> iterate(Enumeration<E> enumr) {
        return new OneTimeIterable<E>(iterator(enumr));
    }

    public static <E> Iterable<E> iterate(
            final Class<? extends Iterator<E>> iterType) {
        if (iterType == null)
            throw new NullPointerException("iterType");
        return new Iterable<E>() {

            @Override
            public Iterator<E> iterator() {
                try {
                    return iterType.newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        };
    }

    public static <E> Iterable<E> iterate(
            final Class<? extends Iterator<E>> iterType, final Object enclosing) {
        if (enclosing == null)
            throw new NullPointerException("encldoing");
        final Constructor<? extends Iterator<E>> ctor;
        try {
            ctor = iterType.getDeclaredConstructor(enclosing.getClass());
        } catch (NoSuchMethodException e) {
            System.err.println("Constructors: ");
            for (Constructor<?> c : iterType.getDeclaredConstructors())
                System.err.println(c);
            throw new IllegalUsageError(e);
        }
        ctor.setAccessible(true);
        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                Iterator<E> iter;
                try {
                    iter = ctor.newInstance(enclosing);
                } catch (Exception e) {
                    throw new IllegalUsageError(e);
                }
                return iter;
            }
        };
    }

}
