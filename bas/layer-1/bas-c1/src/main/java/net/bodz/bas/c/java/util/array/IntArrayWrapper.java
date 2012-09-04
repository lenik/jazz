package net.bodz.bas.c.java.util.array;

import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.NotImplementedException;

public class IntArrayWrapper
        extends AbstractArrayWrapper<int[], Integer> {

    public final int[] array;

    public IntArrayWrapper(int[] array) {
        this(array, 0, array.length);
    }

    public IntArrayWrapper(int[] array, int start, int end) {
        super(start, end);
        if (array == null)
            throw new NullPointerException("array");
        if (end > array.length)
            throw new IndexOutOfBoundsException("Bad end index: " + end);
        this.array = array;
    }

    @Override
    public int[] allocate(int size) {
        return new int[size];
    }

    @Override
    public int[] getArray() {
        return array;
    }

    @Override
    protected IArrayWrapper<int[], Integer> _select(int actualFrom, int actualTo) {
        return wrap(array, actualFrom, actualTo);
    }

    @Override
    public Integer _get(int actualIndex) {
        return array[actualIndex];
    }

    @Override
    public void _set(int actualIndex, Integer value) {
        array[actualIndex] = value;
    }

    @Override
    protected int _binarySearch(int actualFrom, int actualTo, Integer key) {
        return Arrays.binarySearch(array, actualFrom, actualTo, key);
    }

    @Override
    protected int[] _copyArray(int actualFrom, int actualTo) {
        return Arrays.copyOfRange(array, actualFrom, actualTo);
    }

    @Override
    protected IArrayWrapper<int[], Integer> _copy(int actualFrom, int actualTo) {
        int[] copyArray = _copyArray(actualFrom, actualTo);
        return wrap(copyArray);
    }

    @Override
    public void _fill(int actualFrom, int actualTo, Integer val) {
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
        Arrays.sort(array, actualFrom, actualTo);
    }

    @Override
    protected void _sort(int actualFrom, int actualTo, Comparator<? super Integer> comparator) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(Integer val, int start) {
        int _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i < end; i++)
            if (array[i] == _val)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Integer val, int start) {
        int _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i >= start; i--)
            if (array[i] == _val)
                return i;
        return -1;
    }

}
