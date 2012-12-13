package net.bodz.bas.t.list;

import java.util.AbstractList;
import java.util.Arrays;

import net.bodz.bas.err.ReadOnlyException;

public class ArrayAccessList<T>
        extends AbstractList<T> {

    private final T[] array;
    private final int off;
    private int len;

    public ArrayAccessList(T[] array, int off, int len) {
        assert array != null;
        assert len >= 0;
        assert off >= 0 && off + len <= array.length;
        this.array = array;
        this.off = off;
        this.len = len;
    }

    /**
     * @see Arrays#asList(Object...)
     */
    @Deprecated
    public ArrayAccessList(T[] array) {
        this(array, 0, array.length);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= len)
            throw new IndexOutOfBoundsException("" + index);
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public int indexOf(Object o) {
        int end = off + len;
        for (int i = off; i < end; i++) {
            T t = array[i];
            if (t == o)
                return i - off;
            if (t != null && t.equals(o))
                return i - off;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i >= 0) {
            remove(i);
            return true;
        }
        return false;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[off + index];
    }

    @Override
    public T set(int index, T value)
            throws ReadOnlyException {
        checkIndex(index);
        index += off;
        T old = array[index];
        array[index] = value;
        return old;
    }

    @Override
    public void add(int index, T value)
            throws ReadOnlyException {
        checkIndex(index);
        // if (off + len < array.length)
        // array[len++] = value;
        throw new ReadOnlyException();
    }

    @Override
    public T remove(int index)
            throws ReadOnlyException {
        checkIndex(index);
        throw new ReadOnlyException();
    }

}
