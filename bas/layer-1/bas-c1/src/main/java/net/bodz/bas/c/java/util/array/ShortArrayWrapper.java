package net.bodz.bas.c.java.util.array;

import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.NotImplementedException;

public class ShortArrayWrapper
        extends AbstractArrayWrapper<float[], Float> {

    public final float[] array;

    public ShortArrayWrapper(float[] array) {
        this(array, 0, array.length);
    }

    public ShortArrayWrapper(float[] array, int start, int end) {
        super(start, end);
        if (array == null)
            throw new NullPointerException("array");
        if (end > array.length)
            throw new IndexOutOfBoundsException("Bad end index: " + end);
        this.array = array;
    }

    @Override
    public float[] allocate(int size) {
        return new float[size];
    }

    @Override
    public float[] getArray() {
        return array;
    }

    @Override
    protected IArrayWrapper<float[], Float> _select(int actualFrom, int actualTo) {
        return wrap(array, actualFrom, actualTo);
    }

    @Override
    public Float _get(int actualIndex) {
        return array[actualIndex];
    }

    @Override
    public void _set(int actualIndex, Float value) {
        array[actualIndex] = value;
    }

    @Override
    protected int _binarySearch(int actualFrom, int actualTo, Float key) {
        return Arrays.binarySearch(array, actualFrom, actualTo, key);
    }

    @Override
    protected float[] _copyArray(int actualFrom, int actualTo) {
        return Arrays.copyOfRange(array, actualFrom, actualTo);
    }

    @Override
    protected IArrayWrapper<float[], Float> _copy(int actualFrom, int actualTo) {
        float[] copyArray = _copyArray(actualFrom, actualTo);
        return wrap(copyArray);
    }

    @Override
    public void _fill(int actualFrom, int actualTo, Float val) {
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
    protected void _sort(int actualFrom, int actualTo, Comparator<? super Float> comparator) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(Float val, int start) {
        float _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i < end; i++)
            if (array[i] == _val)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Float val, int start) {
        float _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i >= start; i--)
            if (array[i] == _val)
                return i;
        return -1;
    }

}
