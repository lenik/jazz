package net.bodz.bas.types.util;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.Iterator;

import net.bodz.bas.lang.err.IllegalArgumentTypeException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang2.Filt1;
import net.bodz.bas.nls.TypesNLS;

public class Iterates {

    public static <T> Iterable<T> EMPTY() {
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                return Iterators.getEmptyIterator();
            }

        };
    }

    public static <T, X extends Throwable> Iterable<T> iterate(
            final DirectIterable<? extends T, X> dit) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                DirectIterator<? extends T, X> _dit = dit.iterator(false);
                // This down-cast is safe because we won't modify any data thru Iterator.
                @SuppressWarnings("unchecked")
                Iterator<T> iterator = (Iterator<T>) Iterators.iterator(_dit);
                return iterator;
            }
        };
    }

    static class OnceIterable<E> implements Iterable<E> {

        private Iterator<E> iter;

        public OnceIterable(Iterator<E> iter) {
            assert iter != null;
            this.iter = iter;
        }

        @Override
        public Iterator<E> iterator() {
            if (this.iter == null)
                throw new IllegalStateException(TypesNLS.getString("Iterates.alreadyBeenIterated")); //$NON-NLS-1$
            Iterator<E> iter = this.iter;
            this.iter = null;
            return iter;
        }

    }

    public static <E> Iterable<E> once(Iterator<E> iter) {
        return new OnceIterable<E>(iter);
    }

    public static <E> Iterable<E> once(Enumeration<E> enumr) {
        return new OnceIterable<E>(Iterators.iterator(enumr));
    }

    public static <E> Iterable<E> iterate(final Class<? extends Iterator<E>> iterType) {
        if (iterType == null)
            throw new NullPointerException("iterType"); //$NON-NLS-1$
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

    public static <E> Iterable<E> iterate(final Class<? extends Iterator<E>> iterType,
            final Object enclosing) {
        if (enclosing == null)
            throw new NullPointerException("enclosing"); //$NON-NLS-1$
        final Constructor<? extends Iterator<E>> ctor;
        try {
            ctor = iterType.getDeclaredConstructor(enclosing.getClass());
        } catch (NoSuchMethodException e) {
            System.err.println(TypesNLS.getString("Iterates.ctors")); //$NON-NLS-1$
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

    @SuppressWarnings("unchecked")
    protected static <T> Iterator<T> getIterator(Object iter) {
        if (iter.getClass().isArray())
            return Iterators.iterator((T[]) iter);
        if (iter instanceof Iterator)
            return (Iterator<T>) iter;
        else if (iter instanceof Iterable)
            return ((Iterable<T>) iter).iterator();
        else
            throw new IllegalArgumentTypeException(iter, "iterator or iterable"); //$NON-NLS-1$
    }

    static <T> Iterable<T> _concat(final Object... iters) {
        if (iters == null)
            throw new NullPointerException("iters"); //$NON-NLS-1$
        final int n = iters.length;
        if (n == 0)
            return EMPTY();
        // if (n == 1)
        // return iterables[0];
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                @SuppressWarnings("unchecked")
                Iterator<T>[] iterators = (Iterator<T>[]) new Iterator<?>[n];
                for (int i = 0; i < n; i++) {
                    Object iter = iters[i];
                    iterators[i] = getIterator(iter);
                }
                return Iterators.concat(iterators);
            }

        };
    }

    public static <T> Iterable<T> concat(Iterable<T>... iterables) {
        return _concat((Object[]) iterables);
    }

    public static <T> Iterable<T> concat(T[]... arrays) {
        return _concat((Object[]) arrays);
    }

    static <T> Iterable<T> _map(final Object iter, final Filt1<T, T> filter) {
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                Iterator<T> iterator = getIterator(iter);
                return Iterators.map(iterator, filter);
            }

        };
    }

    public static <T> Iterable<T> map(Iterable<T> iterable, final Filt1<T, T> filter) {
        return _map(iterable, filter);
    }

    public static <T> Iterable<T> map(T[] array, final Filt1<T, T> filter) {
        return _map(array, filter);
    }

}
