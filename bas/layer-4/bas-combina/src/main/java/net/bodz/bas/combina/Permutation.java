package net.bodz.bas.combina;

import net.bodz.bas.closure.alt.Proc1;
import net.bodz.bas.collection.array.ArrayWrapper;
import net.bodz.bas.collection.array.IArrayWrapper;
import net.bodz.bas.collection.list.IntSList;
import net.bodz.bas.util.exception.IllegalUsageException;
import net.bodz.bas.util.exception.OutOfDomainException;

public class Permutation {

    /**
     * In-place iterate core implementation.
     * 
     * The array after full-iterated will be reversed.
     */
    private static <A, E> void _iterate(IArrayWrapper<A, E> array, int off, int len, Proc1<A> visitor) {
        if (len == 1) {
            visitor.exec(array.getArray());
            return;
        }
        int count = len;
        while (count-- > 0) {
            _iterate(array, off + 1, len - 1, visitor);
            if (count != 0)
                array.reverse(off, off + len);
        }
    }

    public static <T> void iterate(T[] array, Proc1<T[]> closure) {
        ArrayWrapper<T> _array = ArrayWrapper.wrap(array);
        iterate(_array, closure);
    }

    public static <A, E> void iterate(IArrayWrapper<A, E> array, Proc1<A> closure) {
        _iterate(array.copy(), 0, array.length(), closure);
    }

    public static <T> void iterateInPlace(T[] array, Proc1<T[]> closure) {
        ArrayWrapper<T> _array = ArrayWrapper.wrap(array);
        iterateInPlace(_array, closure);
    }

    public static <A, E> void iterateInPlace(IArrayWrapper<A, E> array, Proc1<A> closure) {
        _iterate(array, 0, array.length(), closure);
        array.reverse();
    }

    public static <T> void perm(int ord, T[] array, T[] output) {
        if (array == null)
            throw new NullPointerException("array");
        if (output == null)
            throw new NullPointerException("output");
        ArrayWrapper<T> _array = ArrayWrapper.wrap(array);
        ArrayWrapper<T> _sample = ArrayWrapper.wrap(output);
        perm(ord, _array, _sample);
    }

    public static <A, E> void perm(int ord, IArrayWrapper<A, E> array, IArrayWrapper<A, E> output) {
        int length = array.length();
        int[] ordv = new int[length];
        for (int n = 2; n <= length; n++) { // i = 1..len-1
            int mod = ord % n; // mod = ord % (i + 1)
            ord /= n;
            ordv[length - n] = mod; // ordv[len - 1 - i] = mod
        }
        assert ordv[length - 1] == 0;

        int srclen = array.length();

        IntSList candidates = new IntSList(srclen);
        for (int i = 0; i < length; i++) {
            int mod = ordv[i];
            int srcindex = candidates.remove(mod);
            E val = array.get(srcindex);
            output.set(i, val);
        }
    }

    public static <T> int ord(T[] array, T[] sample) {
        if (array == null)
            throw new NullPointerException("array");
        if (sample == null)
            throw new NullPointerException("sample");
        ArrayWrapper<T> _array = ArrayWrapper.wrap(array);
        ArrayWrapper<T> _sample = ArrayWrapper.wrap(sample);
        return ord(_array, _sample);
    }

    public static <A, E> int ord(IArrayWrapper<A, E> array, IArrayWrapper<A, E> sample) {
        if (array == null)
            throw new NullPointerException("array");
        if (sample == null)
            throw new NullPointerException("sample");
        int len = array.length();
        int sampleLen = sample.length();
        if (len != sampleLen)
            throw new IllegalUsageException("the two array of different length");

        IntSList candidates = new IntSList(len);
        int ord = 0;
        for (int i = 0; i < sampleLen; i++) {
            assert candidates.size() == len - i;

            E val = sample.get(i);
            int srcindex = array.indexOf(val);
            int mod = candidates.indexOf(srcindex);
            if (mod == -1 || mod >= len)
                throw new OutOfDomainException("dst[" + i + "]");
            candidates.removeAbsolute(srcindex);

            ord = ord * (len - i) + mod;
        }
        return ord;
    }

}
