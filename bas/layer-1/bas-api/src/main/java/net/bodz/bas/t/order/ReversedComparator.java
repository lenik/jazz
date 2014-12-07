package net.bodz.bas.t.order;

import java.util.Comparator;

public class ReversedComparator<T>
        implements Comparator<T> {

    private final Comparator<T> comparator;

    public ReversedComparator(Comparator<T> comparator) {
        if (comparator == null)
            throw new NullPointerException("comparator");
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2) {
        return comparator.compare(o2, o1);
    }

}
