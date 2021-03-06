package net.bodz.bas.c.java.util.array;

import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.NotImplementedException;

public class BooleanArrayWrapper
        extends AbstractArrayWrapper<boolean[], Boolean> {

    public final boolean[] array;

    public BooleanArrayWrapper(boolean[] array) {
        this(array, 0, array.length);
    }

    public BooleanArrayWrapper(boolean[] array, int start, int end) {
        super(start, end);
        if (array == null)
            throw new NullPointerException("array");
        if (end > array.length)
            throw new IndexOutOfBoundsException("Bad end index: " + end);
        this.array = array;
    }

    @Override
    public boolean[] allocate(int size) {
        return new boolean[size];
    }

    @Override
    public boolean[] getArray() {
        return array;
    }

    @Override
    protected IArrayWrapper<boolean[], Boolean> _select(int actualFrom, int actualTo) {
        return wrap(array, actualFrom, actualTo);
    }

    @Override
    public Boolean _get(int actualIndex) {
        return array[actualIndex];
    }

    @Override
    public void _set(int actualIndex, Boolean value) {
        array[actualIndex] = value;
    }

    @Override
    protected int _binarySearch(int actualFrom, int actualTo, Boolean key) {
        // boolean _key = key;
        throw new NotImplementedException();
    }

    @Override
    protected boolean[] _copyArray(int actualFrom, int actualTo) {
        return Arrays.copyOfRange(array, actualFrom, actualTo);
    }

    @Override
    protected IArrayWrapper<boolean[], Boolean> _copy(int actualFrom, int actualTo) {
        boolean[] copyArray = _copyArray(actualFrom, actualTo);
        return wrap(copyArray);
    }

    @Override
    public void _fill(int actualFrom, int actualTo, Boolean val) {
        Arrays.fill(array, actualFrom, actualTo, val);
    }

    @Override
    protected void _reverse(int actualFrom, int actualTo) {
        Arrays.reverse(array, actualFrom, actualTo);
    }

    @Override
    protected void _shuffle(Random random, int actualFrom, int actualTo, int strength) {
        Arrays.shuffle(array, actualFrom, actualTo, random, strength);
    }

    @Override
    protected void _sort(int actualFrom, int actualTo) {
        int trueCount = 0;
        for (int i = actualFrom; i < actualTo; i++)
            if (array[i])
                trueCount++;
        int lastFalseIndex = actualTo - trueCount;
        for (int i = actualFrom; i < actualTo; i++)
            array[i] = i >= lastFalseIndex;
    }

    protected void _sortReverse(int actualFrom, int actualTo) {
        int trueCount = 0;
        for (int i = actualFrom; i < actualTo; i++)
            if (array[i])
                trueCount++;
        int lastTrueIndex = actualFrom + trueCount;
        for (int i = actualFrom; i < actualTo; i++)
            array[i] = i < lastTrueIndex;
    }

    @Override
    protected void _sort(int actualFrom, int actualTo, Comparator<? super Boolean> comparator) {
        int cmp = comparator.compare(Boolean.FALSE, Boolean.TRUE);
        boolean asc = cmp < 0;
        if (asc)
            _sort(actualFrom, actualTo);
        else
            _sortReverse(actualFrom, actualTo);
    }

    @Override
    public int indexOf(Boolean val, int start) {
        boolean _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i < end; i++)
            if (array[i] == _val)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Boolean val, int start) {
        boolean _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i >= start; i--)
            if (array[i] == _val)
                return i;
        return -1;
    }

}
