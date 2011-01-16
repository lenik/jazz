package net.bodz.bas.collection.comparator;

import java.util.Comparator;

public interface NonNullComparator<T>
        extends Comparator<T> {

    /**
     * Compare two nullable values.
     */
    @Override
    int compare(T o1, T o2);

    /**
     * @return -1: null-first, 1: null-last, 0: null-undetermined.
     */
    int getNullOrder();

    /**
     * Compare two non-<code>null</code> values.
     * 
     * @param o1
     *            Non-<code>null</code> value to compare.
     * @param o2
     *            Non-<code>null</code> value to compare.
     * @return negative number if <code>o1</code> is less then <code>o2</code>, or positive number
     *         if <code>o1</code> is greater then <code>o2</code>, or 0 if both parameters are the
     *         same.
     */
    int compareNonNull(T o1, T o2);

}
