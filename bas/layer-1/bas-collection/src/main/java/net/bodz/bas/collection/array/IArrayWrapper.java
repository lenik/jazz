package net.bodz.bas.collection.array;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public interface IArrayWrapper<A, E> {

    A getArray();

    int getStart();

    int getEnd();

    A allocate(int size);

    List<E> asList();

    /**
     * @throws IndexOutOfBoundsException
     */
    List<E> asList(int from, int to);

    int length();

    /**
     * @throws IndexOutOfBoundsException
     */
    IArrayWrapper<A, E> select(int from, int to);

    E get(int index);

    /**
     * @throws IndexOutOfBoundsException
     */
    void set(int index, E value);

    A copyArray();

    /**
     * @throws IndexOutOfBoundsException
     */
    A copyArray(int from, int to);

    IArrayWrapper<A, E> copy();

    /**
     * @param key
     *            non-<code>null</code> value to search.
     * @throws IndexOutOfBoundsException
     */
    IArrayWrapper<A, E> copy(int from, int to);

    int binarySearch(E key);

    /**
     * @param key
     *            non-<code>null</code> value to search.
     * @throws IndexOutOfBoundsException
     */
    int binarySearch(int from, int to, E key);

    void fill(E val);

    /**
     * @throws IndexOutOfBoundsException
     */
    void fill(int from, int to, E val);

    void sort();

    /**
     * @throws IndexOutOfBoundsException
     */
    void sort(int from, int to);

    void sort(Comparator<? super E> comparator);

    /**
     * @throws IndexOutOfBoundsException
     */
    void sort(int from, int to, Comparator<? super E> comparator);

    void reverse();

    /**
     * @throws IndexOutOfBoundsException
     */
    void reverse(int from, int to);

    void shuffle(Random random);

    void shuffle(Random random, int strength);

    /**
     * @throws IndexOutOfBoundsException
     */
    void shuffle(Random random, int from, int to);

    /**
     * @throws IndexOutOfBoundsException
     */
    void shuffle(Random random, int from, int to, int strength);

    int indexOf(E val);

    /**
     * @throws IndexOutOfBoundsException
     */
    int indexOf(E val, int start);

    int lastIndexOf(E val);

    /**
     * @throws IndexOutOfBoundsException
     */
    int lastIndexOf(E val, int start);

}
