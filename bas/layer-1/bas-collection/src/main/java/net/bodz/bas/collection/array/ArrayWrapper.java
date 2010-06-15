package net.bodz.bas.collection.array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.lang.Nullables;

public class ArrayWrapper<T>
        extends AbstractArrayWrapper<T[], T> {

    public final T[] array;

    ArrayWrapper(T[] array) {
        this(array, 0, array.length);
    }

    ArrayWrapper(T[] array, int start, int end) {
        super(start, end);
        if (array == null)
            throw new NullPointerException("array");
        if (end > array.length)
            throw new IndexOutOfBoundsException("Bad end index: " + end);
        this.array = array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] allocate(int size) {
        Class<?> componentType = array.getClass().getComponentType();
        return (T[]) Array.newInstance(componentType, size);
    }

    @Override
    public T[] getArray() {
        return array;
    }

    @Override
    protected IArrayWrapper<T[], T> _select(int actualFrom, int actualTo) {
        return wrap(array, actualFrom, actualTo);
    }

    @Override
    public T _get(int actualIndex) {
        return array[actualIndex];
    }

    @Override
    public void _set(int actualIndex, T value) {
        array[actualIndex] = value;
    }

    @Override
    protected int _binarySearch(int actualFrom, int actualTo, T key) {
        return Arrays.binarySearch(array, actualFrom, actualTo, key);
    }

    @Override
    protected T[] _copyArray(int actualFrom, int actualTo) {
        return Arrays.copyOfRange(array, actualFrom, actualTo);
    }

    @Override
    protected IArrayWrapper<T[], T> _copy(int actualFrom, int actualTo) {
        T[] copyArray = _copyArray(actualFrom, actualTo);
        return wrap(copyArray);
    }

    @Override
    public void _fill(int actualFrom, int actualTo, T val) {
        Arrays.fill(array, actualFrom, actualTo, (T) val);
    }

    @Override
    protected void _reverse(int actualFrom, int actualTo) {
        int swaps = (actualTo - actualFrom) / 2;
        assert swaps >= 0;
        int left = actualFrom;
        int right = actualTo - 1;
        while (swaps-- != 0) {
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    @Override
    protected void _shuffle(Random random, int actualFrom, int actualTo, int strength) {
        int length = actualTo - actualFrom;
        while (strength-- > 0) {
            int n = start + random.nextInt(length);
            int m = start + random.nextInt(length);
            if (n == m)
                continue;
            T temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    @Override
    protected void _sort(int actualFrom, int actualTo) {
        Arrays.sort(array, actualFrom, actualTo);
    }

    @Override
    protected void _sort(int actualFrom, int actualTo, Comparator<? super T> comparator) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(T val, int start) {
        int actualStart = checkIndex(start);
        for (int i = actualStart; i < end; i++)
            if (Nullables.equals(array[i], val))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(T val, int start) {
        int actualStart = checkIndex(start);
        for (int i = actualStart; i >= start; i--)
            if (Nullables.equals(array[i], val))
                return i;
        return -1;
    }

}
