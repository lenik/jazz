package net.bodz.bas.collection.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public interface ArrayOp<A> {

    Class<?> getComponentType();

    A allocate(int size);

    Object get(A array, int index);

    void set(A array, int index, Object value);

    A toArray(Iterator<?> iterator, int size);

    A toArray(Iterable<?> iterable, int size);

    A toArray(List<?> list);

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

    A shift(A array);

    A pop(A array);

    int indexOf(A array, Object key);

    /**
     * @see String#indexOf(String, int)
     */
    int indexOf(A array, Object key, int start);

    int indexOf(A array, int from, int to, Object key, int start);

    int lastIndexOf(A array, Object key);

    /**
     * @see String#lastIndexOf(String, int)
     */
    int lastIndexOf(A array, Object key, int start);

    int lastIndexOf(A array, int from, int to, Object key, int start);

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

    void fill(A array, Random rands);

    void fill(A array, int fromIndex, int toIndex, Random rands);

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

    void reverse(A array);

    void reverse(A array, int fromIndex, int toIndex);

    /**
     * @see java.util.Arrays#equals(Object[], Object[])
     */
    boolean equals(A a, A b);

    boolean equals(A a, int aoff, A b, int boff, int len);

    boolean equalsWithWrap(A pattern, A array);

    boolean equalsWithWrap(A pattern, A array, int from, int to);

    /**
     * @see java.util.Arrays#hashCode(Object[])
     */
    int hashCode(A array);

    /**
     * @see java.util.Arrays#toString(Object[])
     */
    String toString(A array);

}
