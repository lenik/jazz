package net.bodz.bas.types;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import net.bodz.bas.types.util.PrefetchedIterator;

public abstract class HierSet<E> extends TreeSet<E> implements Hier<E> {

    private static final long serialVersionUID = -3131717397860882935L;

    public HierSet() {
        super();
    }

    public HierSet(Collection<? extends E> c) {
        super(c);
    }

    public HierSet(Comparator<? super E> comparator) {
        super(comparator);
    }

    public HierSet(SortedSet<E> s) {
        super(s);
    }

    /**
     * @return <code>null</code>
     */
    protected E nonexist() {
        return null;
    }

    @Override
    public E floor(E e) {
        E floo = super.floor(e);
        while (floo != null) {
            if (derives(floo, e))
                return floo;
            e = shrink(e);
            if (e == null)
                break;
            floo = super.floor(e);
        }
        return nonexist();
    }

    @Override
    public E ceiling(E e) {
        E ceil = super.ceiling(e);
        if (ceil == null)
            return nonexist();
        if (!derives(e, ceil))
            return nonexist();
        return ceil;
    }

    public Iterable<E> ceilings(final E e) {
        final E start = ceiling(e);

        class Iter extends PrefetchedIterator<E> {
            private E next;

            public Iter(E next) {
                this.next = next;
            }

            @Override
            protected Object fetch() {
                if (next == null)
                    return END;
                E ret = next;
                next = higher(next);
                if (next != null && !derives(e, next))
                    next = null;
                return ret;
            }
        }

        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iter(start);
            }
        };
    }

}
