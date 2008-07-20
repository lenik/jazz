package net.bodz.bas.types.util;

import java.util.Comparator;

public interface ArrayOp<A> {

    Class<?> getComponentType();

    A allocate(int size);

    void set(A array, int index, Object value);

    void copy(A src, int srcoff, A dst, int dstoff, int len);

    /**
     * @see java.util.Arrays#copyOf(Object[], int)
     */
    A copyOf(A array, int newLength);

    /**
     * @see java.util.Arrays#copyOf(Object[], int)
     */
    A copyOf(A array);

    /**
     * @see java.util.Arrays#copyOfRange(Object[], int, int)
     */
    A copyOfRange(A array, int from, int to);

    /**
     * @see java.util.Arrays#binarySearch(Object[], Object)
     */
    int binarySearch(A array, Object key);

    /**
     * @see java.util.Arrays#binarySearch(Object[], int, int, Object)
     */
    int binarySearch(A array, int fromIndex, int toIndex, Object key);

    /**
     * @see java.util.Arrays#fill(Object[], Object)
     */
    void fill(A array, Object val);

    /**
     * @see java.util.Arrays#fill(Object[], int, int, Object)
     */
    void fill(A array, int fromIndex, int toIndex, Object val);

    /**
     * @see java.util.Arrays#sort(Object[])
     */
    void sort(A array);

    /**
     * @see java.util.Arrays#sort(Object[], Comparator)
     */
    void sort(A array, Comparator<?> comparator);

    /**
     * @see java.util.Arrays#sort(Object[], int, int)
     */
    void sort(A array, int fromIndex, int toIndex);

    /**
     * @see java.util.Arrays#sort(Object[], int, int, Comparator)
     */
    void sort(A array, int fromIndex, int toIndex, Comparator<?> comparator);

    /**
     * @see java.util.Arrays#equals(Object[], Object[])
     */
    boolean equals(A a, A b);

    /**
     * @see java.util.Arrays#hashCode(Object[])
     */
    int hashCode(A array);

    /**
     * @see java.util.Arrays#toString(Object[])
     */
    String toString(A array);

}
