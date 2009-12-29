package net.bodz.bas.collection.comparator;

import java.util.Comparator;

public class NullFirstComparator<T>
        implements Comparator<T> {

    private final Comparator<T> nonNullComparator;

    public NullFirstComparator(Comparator<T> comparator) {
        if (comparator == null)
            throw new NullPointerException("nonNullComparator");
        this.nonNullComparator = comparator;
    }

    @Override
    public int compare(T o1, T o2) {
        if (o1 == null)
            return o2 == null ? 0 : -1;
        if (o2 == null)
            return 1;
        return nonNullComparator.compare(o1, o2);
    }

}
