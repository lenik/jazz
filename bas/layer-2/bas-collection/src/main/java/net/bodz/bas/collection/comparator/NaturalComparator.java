package net.bodz.bas.collection.comparator;

/**
 * It's impossible to create a static instance for general ComparableComparator purpose.
 * <p>
 * The resolution here is, <b>YOU SHOULD ALWAYS PASS A COMPARATOR. </b>
 */
public class NaturalComparator<T extends Comparable<? super T>>
        extends AbstractNonNullComparator<T> {

    @Override
    public int compareNonNull(T o1, T o2) {
        return o1.compareTo(o2);
    }

}
