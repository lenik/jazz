package net.bodz.bas.t.list;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NullList<E>
        extends AbstractList<E> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.emptyIterator();
    }

    static final Object[] emptyArray = new Object[0];

    @Override
    public Object[] toArray() {
        return emptyArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return a;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public E get(int index) {
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public E set(int index, E element) {
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public int indexOf(Object o) {
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return Collections.emptyListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index == 0)
            return Collections.emptyListIterator();
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex != 0)
            throw new IndexOutOfBoundsException("Index: " + fromIndex);
        if (toIndex != 0)
            throw new IndexOutOfBoundsException("toIndex: " + toIndex);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof List) && ((List<?>) o).isEmpty();
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
