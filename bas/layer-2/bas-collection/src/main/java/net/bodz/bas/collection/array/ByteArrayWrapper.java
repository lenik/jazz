package net.bodz.bas.collection.array;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import net.bodz.bas.util.exception.NotImplementedException;

public class ByteArrayWrapper
        extends AbstractArrayWrapper<byte[], Byte> {

    public final byte[] array;

    ByteArrayWrapper(byte[] array) {
        this(array, 0, array.length);
    }

    ByteArrayWrapper(byte[] array, int start, int end) {
        super(start, end);
        if (array == null)
            throw new NullPointerException("array");
        if (end > array.length)
            throw new IndexOutOfBoundsException("Bad end index: " + end);
        this.array = array;
    }

    @Override
    public byte[] allocate(int size) {
        return new byte[size];
    }

    @Override
    public byte[] getArray() {
        return array;
    }

    @Override
    protected IArrayWrapper<byte[], Byte> _select(int actualFrom, int actualTo) {
        return wrap(array, actualFrom, actualTo);
    }

    @Override
    public Byte _get(int actualIndex) {
        return array[actualIndex];
    }

    @Override
    public void _set(int actualIndex, Byte value) {
        array[actualIndex] = value;
    }

    @Override
    protected int _binarySearch(int actualFrom, int actualTo, Byte key) {
        return Arrays.binarySearch(array, actualFrom, actualTo, key);
    }

    @Override
    protected byte[] _copyArray(int actualFrom, int actualTo) {
        return Arrays.copyOfRange(array, actualFrom, actualTo);
    }

    @Override
    protected IArrayWrapper<byte[], Byte> _copy(int actualFrom, int actualTo) {
        byte[] copyArray = _copyArray(actualFrom, actualTo);
        return wrap(copyArray);
    }

    @Override
    public void _fill(int actualFrom, int actualTo, Byte val) {
        Arrays.fill(array, actualFrom, actualTo, (Byte) val);
    }

    @Override
    protected void _reverse(int actualFrom, int actualTo) {
        int swaps = (actualTo - actualFrom) / 2;
        assert swaps >= 0;
        int left = actualFrom;
        int right = actualTo - 1;
        while (swaps-- != 0) {
            byte temp = array[left];
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
            byte temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    @Override
    protected void _sort(int actualFrom, int actualTo) {
        Arrays.sort(array, actualFrom, actualTo);
    }

    @Override
    protected void _sort(int actualFrom, int actualTo, Comparator<? super Byte> comparator) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(Byte val, int start) {
        byte _val = val;
        int actualStart = checkIndex(start);
        for (int i = actualStart; i < end; i++)
            if (array[i] == _val)
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Byte val, int start) {
        byte _val = val;
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

    public String toString(Charset charset) {
        return new String(array, start, end, charset);
    }

    public String toString(String charset)
            throws UnsupportedEncodingException {
        return new String(array, start, end, charset);
    }

}
