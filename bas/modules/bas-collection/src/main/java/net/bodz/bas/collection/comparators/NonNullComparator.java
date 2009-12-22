package net.bodz.bas.collection.comparators;

import java.util.Comparator;

/**
 * For comparators who always expect non-<code>null</code> values to compare.
 */
public interface NonNullComparator<T>
        extends Comparator<T> {

    /**
     * {@inheritDoc}
     */
    int compare(T o1, T o2);

}
