package net.bodz.bas.c.java.util.array;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.NotImplementedException;

public class ArrayWrapper<T>
        extends AbstractArrayWrapper<T[], T> {

    public final T[] array;

    public ArrayWrapper(T[] array) {
        this(array, 0, array.length);
    }

    public ArrayWrapper(T[] array, int start, int end) {
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

    @Override
    public int hashCode() {
        return hashCode(array);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!getClass().equals(obj.getClass()))
            return false;

        ArrayWrapper<?> o = (ArrayWrapper<?>) obj;
        Object[] a = o.array;
        return Arrays.equals(array, a);
    }

}
