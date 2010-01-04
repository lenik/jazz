package net.bodz.bas.collection.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.exceptions.NotImplementedException;

public class IntArrayWrapper
        extends AbstractArrayWrapper<int[], Integer> {

    public final int[] array;

    IntArrayWrapper(int[] array) {
        this(array, 0, array.length);
    }

    IntArrayWrapper(int[] array, int start, int end) {
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
        Arrays.fill(array, actualFrom, actualTo, (Integer) val);
    }

    @Override
    protected void _reverse(int actualFrom, int actualTo) {
        int swaps = length() / 2;
        assert swaps >= 0;
        int left = actualFrom;
        int right = actualTo;
        while (swaps != 0) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    @Override
    protected void _shuffle(Random random, int actualFrom, int actualTo) {
        int length = length();
        int swaps = length();
        if (swaps < 2)
            return;
        while (swaps-- > 0) {
            int n = random.nextInt(length);
            int m = random.nextInt(length);
            if (n == m)
                continue;
            int temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    @Override
    protected void _sort(int actualFrom, int actualTo) {
        Arrays.sort(array, actualFrom, actualTo);
    }

    @Override
    protected void _sort(int actualFrom, int actualTo, Comparator<? super Integer> comparator) {
        throw new NotImplementedException();
    }

    public static IntArrayWrapper wrap(int[] array) {
        return new IntArrayWrapper(array);
    }

    public static IntArrayWrapper wrap(int[] array, int start, int end) {
        return new IntArrayWrapper(array, start, end);
    }

}
