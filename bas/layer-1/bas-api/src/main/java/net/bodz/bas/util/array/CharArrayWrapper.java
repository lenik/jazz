package net.bodz.bas.util.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.err.NotImplementedException;

public class CharArrayWrapper
        extends AbstractArrayWrapper<char[], Character> {

    public final char[] array;

    CharArrayWrapper(char[] array) {
        this(array, 0, array.length);
    }

    CharArrayWrapper(char[] array, int start, int end) {
        super(start, end);
        if (array == null)
            throw new NullPointerException("array");
        if (end > array.length)
            throw new IndexOutOfBoundsException("Bad end index: " + end);
        this.array = array;
    }

    @Override
    public char[] allocate(int size) {
        return new char[size];
    }

    @Override
    public char[] getArray() {
        return array;
    }

    @Override
    protected IArrayWrapper<char[], Character> _select(int actualFrom, int actualTo) {
        return wrap(array, actualFrom, actualTo);
    }

    @Override
    public Character _get(int actualIndex) {
        return array[actualIndex];
    }

    @Override
    public void _set(int actualIndex, Character value) {
        array[actualIndex] = value;
    }

    @Override
    protected int _binarySearch(int actualFrom, int actualTo, Character key) {
        return Arrays.binarySearch(array, actualFrom, actualTo, key);
    }

    @Override
    protected char[] _copyArray(int actualFrom, int actualTo) {
        return Arrays.copyOfRange(array, actualFrom, actualTo);
    }

    @Override
    protected IArrayWrapper<char[], Character> _copy(int actualFrom, int actualTo) {
        char[] copyArray = _copyArray(actualFrom, actualTo);
        return wrap(copyArray);
    }

    @Override
    public void _fill(int actualFrom, int actualTo, Character val) {
        Arrays.fill(array, actualFrom, actualTo, (Character) val);
    }

    @Override
    protected void _reverse(int actualFrom, int actualTo) {
        int swaps = (actualTo - actualFrom) / 2;
        assert swaps >= 0;
        int left = actualFrom;
        int right = actualTo - 1;
        while (swaps-- != 0) {
            char temp = array[left];
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
            char temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    @Override
    protected void _sort(int actualFrom, int actualTo) {
        Arrays.sort(array, actualFrom, actualTo);
    }

    @Override
    protected void _sort(int actualFrom, int actualTo, Comparator<? super Character> comparator) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(Character val, int start) {
        char _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i < end; i++)
            if (array[i] == _val)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Character val, int start) {
        char _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i >= start; i--)
            if (array[i] == _val)
                return i;
        return -1;
    }

    @Override
    public String toString() {
        return new String(array, start, end);
    }

}
