package net.bodz.bas.collection.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.exceptions.NotImplementedException;

public class FloatArrayWrapper
        extends AbstractArrayWrapper<float[], Float> {

    public final float[] array;

    FloatArrayWrapper(float[] array) {
        this(array, 0, array.length);
    }

    FloatArrayWrapper(float[] array, int start, int end) {
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
        Arrays.fill(array, actualFrom, actualTo, (Float) val);
    }

    @Override
    protected void _reverse(int actualFrom, int actualTo) {
        int swaps = length() / 2;
        assert swaps >= 0;
        int left = actualFrom;
        int right = actualTo;
        while (swaps != 0) {
            float temp = array[left];
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
            float temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    @Override
    protected void _sort(int actualFrom, int actualTo) {
        Arrays.sort(array, actualFrom, actualTo);
    }

    @Override
    protected void _sort(int actualFrom, int actualTo, Comparator<? super Float> comparator) {
        throw new NotImplementedException();
    }

    public static FloatArrayWrapper wrap(float[] array) {
        return new FloatArrayWrapper(array);
    }

    public static FloatArrayWrapper wrap(float[] array, int start, int end) {
        return new FloatArrayWrapper(array, start, end);
    }

}
