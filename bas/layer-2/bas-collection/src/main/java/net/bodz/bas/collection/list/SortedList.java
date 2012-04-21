package net.bodz.bas.collection.list;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.bodz.bas.util.order.NaturalComparator;

/**
 * @test {@link SortedListTest}
 */
public class SortedList<E>
        extends AbstractList<E>
        implements Serializable {

    private static final long serialVersionUID = -4774959095993441826L;

    private final List<E> list;
    private final Comparator<? super E> comparator;

    public SortedList(List<E> sortedList, Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.list = sortedList;
    }

    /**
     * @param sortedList
     *            Should contains only {@link Comparable} elements, otherwise
     *            {@link ClassCastException} may be thrown when sorting.
     */
    @SuppressWarnings("unchecked")
    public SortedList(List<E> sortedList) {
        this(sortedList, (Comparator<? super E>) new NaturalComparator<>());
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    public int binarySearch(E e) {
        int i = Collections.binarySearch(list, e, comparator);
        return i;
    }

    /**
     * the element will be inserted in order.
     */
    @Override
    public boolean add(E e) {
        int pos = binarySearch(e);
        if (pos < 0)
            pos = -pos - 1;
        else
            pos++; // add after the `equals' one.
        list.add(pos, e);
        return true;
    }

    /**
     * Be careful: call this method may break the order.
     */
    @Override
    public void add(int index, E element) {
        list.add(index, element);
    }

    /**
     * Be careful: call this method may break the order.
     */
    @Override
    public E set(int index, E element) {
        return list.set(index, element);
    }

    @Override
    public E remove(int index) {
        return list.remove(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

}
