package net.bodz.bas.collection.comparator;

public class DecoratedComparator<T>
        extends AbstractNonNullComparator<T> {

    @Override
    public int compareNonNull(T o1, T o2) {
        return 0;
    }

}
