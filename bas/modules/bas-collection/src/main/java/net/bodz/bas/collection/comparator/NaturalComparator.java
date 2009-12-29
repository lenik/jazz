package net.bodz.bas.collection.comparator;

public class NaturalComparator<T extends Comparable<T>>
        implements NonNullComparator<T> {

    public NaturalComparator() {
    }

    @Override
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }

    interface _Comparable
            extends Comparable<Object> {
    }

}
