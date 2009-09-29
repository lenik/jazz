package net.bodz.bas.types.set;

import java.util.Comparator;

import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.DirectIterator;

public abstract class _OrderedSet<T> extends _Set<T> {

    protected final Comparator<? super T> cmp;

    public _OrderedSet(Comparator<? super T> cmp) {
        if (cmp == null)
            this.cmp = Comparators.STD;
        else
            this.cmp = cmp;
    }

    public abstract Comparator<? super T> comparator();

    protected abstract T predecessor(T x);

    protected abstract T succeed(T x);

    @Override
    public DirectIterator<T, RuntimeException> iterator(boolean allowOverlap) {
        return null;
    }

}
