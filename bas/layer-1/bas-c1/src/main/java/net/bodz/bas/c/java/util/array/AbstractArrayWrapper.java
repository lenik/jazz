package net.bodz.bas.c.java.util.array;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import net.bodz.bas.c.java.util.Arrays;

public abstract class AbstractArrayWrapper<A, E>
        extends Arrays
        implements IArrayWrapper<A, E> {

    public final int start;
    public final int end;

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public AbstractArrayWrapper(int start, int end) {
        if (start > end)
            throw new IllegalArgumentException(String.format("start %d > end %d", start, end));
        this.start = start;
        this.end = end;
    }

    protected abstract E _get(int actualIndex);

    protected abstract void _set(int actualIndex, E value);

    /**
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    protected abstract IArrayWrapper<A, E> _select(int actualFrom, int actualTo);

    /**
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    protected abstract A _copyArray(int actualFrom, int actualTo);

    /**
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    protected abstract IArrayWrapper<A, E> _copy(int actualFrom, int actualTo);

    /**
     * @param key
     *            non-<code>null</code> value to search.
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    protected abstract int _binarySearch(int actualFrom, int actualTo, E key);

    /**
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    protected abstract void _fill(int actualFrom, int actualTo, E val);

    /**
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    protected abstract void _sort(int actualFrom, int actualTo);

    /**
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    /**
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    protected abstract void _sort(int actualFrom, int actualTo, Comparator<? super E> comparator);

    protected abstract void _reverse(int actualFrom, int actualTo);

    /**
     * @param actualFrom
     *            inclusive
     * @param actualTo
     *            exclusive
     */
    protected abstract void _shuffle(Random random, int actualFrom, int actualTo, int strength);

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getEnd() {
        return end;
    }

    @Override
    public final int length() {
        return end - start;
    }

    protected int checkIndex(int index) {
        int actualIndex = start + index;
        if (actualIndex < start || actualIndex >= end)
            throw new IndexOutOfBoundsException(String.valueOf(index));
        return actualIndex;
    }

    protected void checkIndex(int _start, int _end) {
        int actualStart = start + _start;
        if (actualStart < start || actualStart >= end)
            throw new IndexOutOfBoundsException("Start index: " + _start);
        int actualEnd = start + _end;
        if (actualEnd < actualStart || actualEnd > end)
            throw new IndexOutOfBoundsException("End index: " + _end);
    }

    @Override
    public List<E> asList() {
        return _asList(start, end);
    }

    @Override
    public List<E> asList(int from, int to) {
        checkIndex(from, to);
        return _asList(start + from, start + to);
    }

    protected List<E> _asList(int actualFrom, int actualTo) {
        return new _AsList(actualFrom, actualTo);
    }

    class _AsList
            extends AbstractList<E> {

        private final int actualFrom;
        private final int actualTo;

        public _AsList(int actualFrom, int actualTo) {
            this.actualFrom = actualFrom;
            this.actualTo = actualTo;
        }

        protected int checkIndex(int index) {
            int actualIndex = actualFrom + index;
            if (actualIndex < actualFrom || actualIndex >= actualTo)
                throw new IndexOutOfBoundsException(String.valueOf(index));
            return actualIndex;
        }

        @Override
        public E get(int index) {
            int actualIndex = checkIndex(index);
            return _get(actualIndex);
        }

        @Override
        public E set(int index, E element) {
            int actualIndex = checkIndex(index);
            E old = _get(actualIndex);
            _set(actualIndex, element);
            return old;
        }

        @Override
        public int size() {
            return actualTo - actualFrom;
        }

    }

    @Override
    public E get(int index) {
        int actualIndex = checkIndex(index);
        return _get(actualIndex);
    }

    @Override
    public void set(int index, E value) {
        int actualIndex = checkIndex(index);
        _set(actualIndex, value);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public IArrayWrapper<A, E> select(int from, int to) {
        checkIndex(from, to);
        return _select(start + from, start + to);
    }

    @Override
    public int binarySearch(E key) {
        return _binarySearch(start, end, key);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public int binarySearch(int from, int to, E key) {
        if (key == null)
            throw new NullPointerException("key");
        checkIndex(from, to);
        return _binarySearch(start + from, start + to, key);
    }

    @Override
    public IArrayWrapper<A, E> copy() {
        return _copy(start, end);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public IArrayWrapper<A, E> copy(int from, int to) {
        checkIndex(from, to);
        return _copy(start + from, start + to);
    }

    @Override
    public A copyArray() {
        return _copyArray(start, end);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public A copyArray(int from, int to) {
        checkIndex(from, to);
        return _copyArray(start + from, start + to);
    }

    @Override
    public void fill(E val) {
        _fill(start, end, val);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public void fill(int from, int to, E val) {
        checkIndex(from, to);
        _fill(start + from, start + to, val);
    }

    @Override
    public void reverse() {
        _reverse(start, end);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public void reverse(int from, int to) {
        checkIndex(from, to);
        _reverse(start + from, start + to);
    }

    @Override
    public void shuffle(Random random) {
        _shuffle(random, start, end, end - start);
    }

    @Override
    public void shuffle(Random random, int strength) {
        _shuffle(random, start, end, strength);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public void shuffle(Random random, int from, int to) {
        checkIndex(from, to);
        _shuffle(random, start + from, start + to, to - from);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public void shuffle(Random random, int from, int to, int strength) {
        checkIndex(from, to);
        _shuffle(random, start + from, start + to, strength);
    }

    @Override
    public void sort() {
        _sort(start, end);
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        _sort(start, end, comparator);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public void sort(int from, int to) {
        checkIndex(from, to);
        _sort(start + from, start + to);
    }

    /**
     * @param from
     *            inclusive
     * @param to
     *            exclusive
     */
    @Override
    public void sort(int from, int to, Comparator<? super E> comparator) {
        checkIndex(from, to);
        _sort(start + from, start + to, comparator);
    }

    @Override
    public int indexOf(E val) {
        int len = length();
        if (len == 0)
            return -1;
        return indexOf(val, 0);
    }

    @Override
    public int lastIndexOf(E val) {
        int len = length();
        if (len == 0)
            return -1;
        return lastIndexOf(val, --len);
    }

}
