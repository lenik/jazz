package net.bodz.bas.collection.iterator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.collection.comparator.NaturalComparator;
import net.bodz.bas.collection.list.DyingList;
import net.bodz.bas.collection.list.SortedList;

public class WeaveIterator<T extends Comparable<T>>
        implements Iterator<T> {

    private static class ItrNxt<T> {

        Iterator<T> iterator;
        T next;

        public ItrNxt(Iterator<T> iterator, T first) {
            if (iterator == null)
                throw new NullPointerException("iterator");
            this.iterator = iterator;
            this.next = first;
        }

        public boolean next() {
            if (!iterator.hasNext())
                return false;
            next = iterator.next();
            return true;
        }

    }

    private static class ItrNxtCmp<T>
            implements Comparator<ItrNxt<T>> {

        final Comparator<? super T> nxtcmp;

        public ItrNxtCmp(Comparator<? super T> nxtcmp) {
            assert nxtcmp != null;
            this.nxtcmp = nxtcmp;
        }

        @Override
        public int compare(ItrNxt<T> a, ItrNxt<T> b) {
            return nxtcmp.compare(a.next, b.next);
        }

    }

    private final Comparator<? super T> cmp;
    private final Iterator<T>[] iterators;
    private final SortedList<ItrNxt<T>> alive;

    private Iterator<T> lastIterator;

    public WeaveIterator(final Iterator<T>... itrs) {
        this(new NaturalComparator<T>(), itrs);
    }

    public WeaveIterator(Iterator<T> itr1, Iterator<T> itr2) {
        this(new NaturalComparator<T>(), itr1, itr2);
    }

    @SuppressWarnings ( "unchecked")
    public WeaveIterator(Comparator<? super T> cmp, Iterator<T> itr1, Iterator<T> itr2) {
        this(cmp, (Iterator<T>[]) (new Iterator<?>[] { itr1, itr2 }));
    }

    public WeaveIterator(Comparator<? super T> cmp, Iterator<T>... iterators) {
        if (cmp == null)
            throw new NullPointerException("cmp"); 
        if (iterators == null)
            throw new NullPointerException("iterators");
        this.cmp = cmp;
        this.iterators = iterators;

        ItrNxtCmp<T> nextComparator = new ItrNxtCmp<T>(cmp);
        this.alive = new SortedList<ItrNxt<T>>(new DyingList<ItrNxt<T>>(iterators.length), nextComparator);
        for (int i = 0; i < iterators.length; i++) {
            Iterator<T> itr = iterators[i];
            if (itr == null)
                throw new NullPointerException("itr[" + i + "]");  
            if (!itr.hasNext()) // skips when preload.
                continue;
            T first = itr.next();
            ItrNxt<T> inxt = new ItrNxt<T>(itr, first);
            alive.add(inxt);
        }
    }

    @Override
    public boolean hasNext() {
        return !alive.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        ItrNxt<T> head = alive.remove(0);
        lastIterator = head.iterator;
        T nxt = head.next;
        if (head.next())
            alive.add(head);
        return nxt;
    }

    @Override
    public void remove() {
        if (lastIterator != null)
            lastIterator.remove();
    }

}