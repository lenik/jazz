package net.bodz.bas.collection.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.exceptions.NotImplementedException;

public class DoubleArrayWrapper
        extends AbstractArrayWrapper<double[], Double> {

    public final double[] array;

    DoubleArrayWrapper(double[] array) {
        this(array, 0, array.length);
    }

    DoubleArrayWrapper(double[] array, int start, int end) {
        super(start, end);
        if (array == null)
            throw new NullPointerException("array");
        if (end > array.length)
            throw new IndexOutOfBoundsException("Bad end index: " + end);
        this.array = array;
    }

    public static DoubleArrayWrapper wrap(double[] array) {
        return new DoubleArrayWrapper(array);
    }

    public static DoubleArrayWrapper wrap(double[] array, int start, int end) {
        return new DoubleArrayWrapper(array, start, end);
    }

    @Override
    public double[] allocate(int size) {
        return new double[size];
    }

    @Override
    public double[] getArray() {
        return array;
    }

    @Override
    protected IArrayWrapper<double[], Double> _select(int actualFrom, int actualTo) {
        return wrap(array, actualFrom, actualTo);
    }

    @Override
    public Double _get(int actualIndex) {
        return array[actualIndex];
    }

    @Override
    public void _set(int actualIndex, Double value) {
        array[actualIndex] = value;
    }

    @Override
    protected int _binarySearch(int actualFrom, int actualTo, Double key) {
        return Arrays.binarySearch(array, actualFrom, actualTo, key);
    }

    @Override
    protected double[] _copyArray(int actualFrom, int actualTo) {
        return Arrays.copyOfRange(array, actualFrom, actualTo);
    }

    @Override
    protected IArrayWrapper<double[], Double> _copy(int actualFrom, int actualTo) {
        double[] copyArray = _copyArray(actualFrom, actualTo);
        return wrap(copyArray);
    }

    @Override
    public void _fill(int actualFrom, int actualTo, Double val) {
        Arrays.fill(array, actualFrom, actualTo, (Double) val);
    }

    @Override
    protected void _reverse(int actualFrom, int actualTo) {
        int swaps = length() / 2;
        assert swaps >= 0;
        int left = actualFrom;
        int right = actualTo;
        while (swaps != 0) {
            double temp = array[left];
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
            double temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    @Override
    protected void _sort(int actualFrom, int actualTo) {
        Arrays.sort(array, actualFrom, actualTo);
    }

    @Override
    protected void _sort(int actualFrom, int actualTo, Comparator<? super Double> comparator) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(Double val, int start) {
        double _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i < end; i++)
            if (array[i] == _val)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Double val, int start) {
        double _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i >= start; i--)
            if (array[i] == _val)
                return i;
        return -1;
    }

}
