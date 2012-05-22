package net.bodz.bas.collection.preorder;

import java.util.Iterator;
import java.util.TreeSet;

import net.bodz.bas.util.iter.PrefetchedIterator;

public abstract class PreorderSet<E>
        extends TreeSet<E>
        implements IPreorderSet<E> {

    private static final long serialVersionUID = 1L;

    private final IPreorder<E> preorder;

    public PreorderSet(IPreorder<E> preorder) {
        if (preorder == null)
            throw new NullPointerException("preorder");
        this.preorder = preorder;
    }

    @Override
    public E meet(E e) {
        E floor = super.floor(e);
        while (floor != null) {
            if (preorder.isLessOrEquals(floor, e))
                return floor;
            e = preorder.getPreceding(e);
            if (e == null)
                break;
            floor = super.floor(e);
        }
        return null;
    }

    // @Override
    public E join_fast(E e) {
        E ceil = super.ceiling(e);
        if (ceil != null) {
            if (preorder.isGreaterOrEquals(ceil, e))
                return ceil;
        }
        return null;
    }

    @Override
    public Iterable<E> join(E start) {
        final E start_ = ceiling(start);

        class HigherIter
                extends PrefetchedIterator<E> {

            private E next = start_;

            @Override
            protected E fetch() {
                if (next == null)
                    return end();
                E ret = next;
                next = higher(next);

                // Continue only if start_ < next, otherwise it should break.
                if (next != null && !preorder.isLessThan(start_, next))
                    next = null;

                return ret;
            }
        }

        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                return new HigherIter();
            }
        };
    }

}
