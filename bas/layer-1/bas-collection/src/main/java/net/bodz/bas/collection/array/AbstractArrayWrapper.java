package net.bodz.bas.collection.array;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public abstract class AbstractArrayWrapper<A, E>
        implements IArrayWrapper<A, E> {

    public final int start;
    public final int end;

    public AbstractArrayWrapper(int start, int end) {
        if (end > start)
            throw new IllegalArgumentException(String.format("end %d > start %d", end, start));
        this.start = start;
        this.end = end;
    }

    protected abstract E _get(int actualIndex);

    protected abstract void _set(int actualIndex, E value);

    protected abstract IArrayWrapper<A, E> _select(int actualFrom, int actualTo);

    protected abstract A _copyArray(int actualFrom, int actualTo);

    protected abstract IArrayWrapper<A, E> _copy(int actualFrom, int actualTo);

    protected abstract int _binarySearch(int actualFrom, int actualTo, E key);

    protected abstract void _fill(int actualFrom, int actualTo, E val);

    protected abstract void _sort(int actualFrom, int actualTo);

    protected abstract void _sort(int actualFrom, int actualTo, Comparator<? super E> comparator);

    protected abstract void _reverse(int actualFrom, int actualTo);

    protected abstract void _shuffle(Random random, int actualFrom, int actualTo);

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

    @Override
    public List<E> asList() {
        return _asList(start, end);
    }

    @Override
    public List<E> asList(int from, int to) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        return _asList(actualFrom, actualTo);
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

    @Override
    public IArrayWrapper<A, E> select(int from, int to) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        return _select(actualFrom, actualTo);
    }

    @Override
    public int binarySearch(E key) {
        return _binarySearch(start, end, key);
    }

    @Override
    public int binarySearch(int from, int to, E key) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        return _binarySearch(actualFrom, actualTo, key);
    }

    @Override
    public IArrayWrapper<A, E> copy() {
        return _copy(start, end);
    }

    @Override
    public IArrayWrapper<A, E> copy(int from, int to) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        return _copy(actualFrom, actualTo);
    }

    @Override
    public A copyArray() {
        return _copyArray(start, end);
    }

    @Override
    public A copyArray(int from, int to) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        return _copyArray(actualFrom, actualTo);
    }

    @Override
    public void fill(E val) {
        _fill(start, end, val);
    }

    @Override
    public void fill(int from, int to, E val) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        _fill(actualFrom, actualTo, val);
    }

    @Override
    public void reverse() {
        _reverse(start, end);
    }

    @Override
    public void reverse(int from, int to) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        _reverse(actualFrom, actualTo);
    }

    @Override
    public void shuffle(Random random) {
        _shuffle(random, start, end);
    }

    @Override
    public void shuffle(Random random, int from, int to) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        _shuffle(random, actualFrom, actualTo);
    }

    @Override
    public void sort() {
        _sort(start, end);
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        _sort(start, end, comparator);
    }

    @Override
    public void sort(int from, int to) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        _sort(actualFrom, actualTo);
    }

    @Override
    public void sort(int from, int to, Comparator<? super E> comparator) {
        int actualFrom = checkIndex(from);
        int actualTo = checkIndex(to);
        _sort(actualFrom, actualTo, comparator);
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
