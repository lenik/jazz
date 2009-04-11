package net.bodz.bas.types.util;

import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.lang.Filt1;

/**
 * @test IteratorsTest
 */
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
    public static <T> Iterator<T> getEmptyIterator() {
        return (Iterator<T>) EMPTY;
    }

    public static <T> Iterator<T> iterator(final T simple) {
        return new Iterator<T>() {

            boolean ended;

            @Override
            public boolean hasNext() {
                return !ended;
            }

            @Override
            public T next() {
                ended = true;
                return simple;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    public static <T> Iterator<T> iterator(final Enumeration<T> enumr) {
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

    /**
     * @see Arrays2#convert(Iterator)
     */
    public static <T> Iterator<T> iterator(final T[] array) {
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

    /**
     * Warning removal: <i> Type safety : A generic array of Iterator<El> is
     * created for a varargs </i>
     */
    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> concat(Iterator<T> it1, Iterator<T> it2) {
        Iterator<?>[] gv = { it1, it2 };
        return concat((Iterator<T>[]) gv);
    }

    public static <T> Iterator<T> concat(final Iterator<T>... iterators) {
        class ConcatIterator extends PrefetchedIterator<T> {

            private int         index;
            private Iterator<T> current;

            @Override
            protected T fetch() {
                if (current == null) {
                    if (index >= iterators.length)
                        return end();
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

    private static class ItrNxt<T> {

        Iterator<T> itr;
        T           nxt;

        public ItrNxt(Iterator<T> itr, T first) {
            assert itr != null : "null itr";
            this.itr = itr;
            this.nxt = first;
        }

        public boolean next() {
            if (!itr.hasNext())
                return false;
            nxt = itr.next();
            return true;
        }

    }

    private static class ItrNxtCmp<T> implements Comparator<ItrNxt<T>> {

        final Comparator<? super T> nxtcmp;

        public ItrNxtCmp(Comparator<? super T> nxtcmp) {
            assert nxtcmp != null;
            this.nxtcmp = nxtcmp;
        }

        @Override
        public int compare(ItrNxt<T> a, ItrNxt<T> b) {
            return nxtcmp.compare(a.nxt, b.nxt);
        }

    }

    public static <T> Iterator<T> weave(final Iterator<T>... itrs) {
        return weave(Comparators.STD, itrs);
    }

    public static <T> Iterator<T> weave(Iterator<T> itr1, Iterator<T> itr2) {
        return weave(Comparators.STD, itr1, itr2);
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterator<T> weave(Comparator<? super T> cmp,
            Iterator<T> itr1, Iterator<T> itr2) {
        Iterator<?> gv[] = { itr1, itr2 };
        return weave(cmp, (Iterator<T>[]) gv);
    }

    /**
     * @throws NullPointerException
     *             if <code>cmp</code> or any of <code>itrs</code> is
     *             <code>null</code>.
     */
    public static <T> Iterator<T> weave(final Comparator<? super T> cmp,
            final Iterator<T>... itrs) {
        if (cmp == null)
            throw new NullPointerException("cmp");
        ItrNxtCmp<T> inxtcmp = new ItrNxtCmp<T>(cmp);
        final SortedList<ItrNxt<T>> alive = new SortedList<ItrNxt<T>>(
                new DyingList<ItrNxt<T>>(itrs.length), inxtcmp);
        for (int i = 0; i < itrs.length; i++) {
            Iterator<T> itr = itrs[i];
            if (itr == null)
                throw new NullPointerException("itr[" + i + "]");
            if (!itr.hasNext()) // skips when preload.
                continue;
            T first = itr.next();
            ItrNxt<T> inxt = new ItrNxt<T>(itr, first);
            alive.add(inxt);
        }

        class WeaveIterator implements Iterator<T> {

            private Iterator<T> lastItr;

            @Override
            public boolean hasNext() {
                return !alive.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                ItrNxt<T> head = alive.remove(0);
                lastItr = head.itr;
                T nxt = head.nxt;
                if (head.next())
                    alive.add(head);
                return nxt;
            }

            @Override
            public void remove() {
                if (lastItr != null)
                    lastItr.remove();
            }

        }
        return new WeaveIterator();
    }

}
