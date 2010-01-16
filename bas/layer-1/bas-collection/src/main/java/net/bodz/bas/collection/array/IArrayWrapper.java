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

    List<E> asList(int from, int to);

    int length();

    IArrayWrapper<A, E> select(int from, int to);

    E get(int index);

    void set(int index, E value);

    A copyArray();

    A copyArray(int from, int to);

    IArrayWrapper<A, E> copy();

    IArrayWrapper<A, E> copy(int from, int to);

    int binarySearch(E key);

    int binarySearch(int from, int to, E key);

    void fill(E val);

    void fill(int from, int to, E val);

    void sort();

    void sort(int from, int to);

    void sort(Comparator<? super E> comparator);

    void sort(int from, int to, Comparator<? super E> comparator);

    void reverse();

    void reverse(int from, int to);

    void shuffle(Random random);

    void shuffle(Random random, int from, int to);

}
