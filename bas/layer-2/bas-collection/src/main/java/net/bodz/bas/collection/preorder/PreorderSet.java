package net.bodz.bas.collection.preorder;

import java.util.Iterator;
import java.util.TreeSet;

import net.bodz.bas.collection.iterator.PrefetchedIterator;

public abstract class PreorderSet<E>
        extends TreeSet<E> {

    private static final long serialVersionUID = 1L;

    private final IPreorder<E> preorder;

    public PreorderSet(IPreorder<E> preorder) {
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    @Override
    public E floor(E e) {
        E floor = super.floor(e);
        while (floor != null) {
            if (preorder.isLessThan(floor, e))
                return floor;
            e = preorder.getPreceding(e);
            if (e == null)
                break;
            floor = super.floor(e);
        }
        return null;
    }

    @Override
    public E ceiling(E e) {
        E ceil = super.ceiling(e);
        if (ceil != null) {
            if (preorder.isGreaterOrEquals(ceil, e))
                return ceil;
        }
        return null;
    }

    public Iterable<E> ceilings(final E e) {
        final E start = ceiling(e);

        class Iter
                extends PrefetchedIterator<E> {
            private E next;

            public Iter(E next) {
                this.next = next;
            }

            @Override
            protected E fetch() {
                if (next == null)
                    return end();
                E ret = next;
                next = higher(next);
                if (next != null && !preorder.isLessThan(e, next))
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
