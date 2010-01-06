package net.bodz.bas.combina;

import java.lang.reflect.Array;

import net.bodz.bas.closure.alt.Proc1;
import net.bodz.bas.collection.array.IArrayWrapper;
import net.bodz.bas.collection.list.IntSList;
import net.bodz.bas.exceptions.OutOfDomainException;

/**
 * @test {@link PermutationTest}
 */
public class Permutation {

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

    public static <A, E> void iterate(IArrayWrapper<A, E> array, Proc1<A> closure) {
        _iterate(array.copy(), 0, array.length(), closure);
    }

    public static <A, E> void iterateInPlace(IArrayWrapper<A, E> array, int off, int len, Proc1<A> closure) {
        _iterate(array, 0, array.length(), closure);
        array.reverse();
    }

    public static <A, E> void iterate(IArrayWrapper<A, E> array, Proc1<A> closure) {
        iterate(array, 0, Array.getLength(array), closure);
    }

    public static <A, E> void iterateInPlace(IArrayWrapper<A, E> array, Proc1<A> closure) {
        iterateInPlace(array, 0, Array.getLength(array), closure);
    }

    public static <A, E> void perm(int ord, IArrayWrapper<A, E> array, A output, int outputOffset) {
        int length = array.length();
        int[] ordv = new int[length];
        for (int n = 2; n <= length; n++) { // i = 1..len-1
            int mod = ord % n; // mod = ord % (i + 1)
            ord /= n;
            ordv[length - n] = mod; // ordv[len - 1 - i] = mod
        }
        assert ordv[length - 1] == 0;

        IntSList candidates = new IntSList(srclen);
        for (int i = 0; i < length; i++) {
            int mod = ordv[i];
            int srcindex = candidates.remove(mod);
            Object val = op.get(array, srcoff + srcindex);
            op.set(output, outputOffset + i, val);
        }
    }

    public static <A> void perm(int ord, A src, A dst) {
        int srclen = Array.getLength(src);
        int dstlen = Array.getLength(dst);
        perm(ord, src, 0, srclen, dst, 0, dstlen);
    }

    public static <A> int ord(A src, int srcoff, int srclen, A dst, int dstoff, int dstlen) {
        ArrayOp<A> op = ArrayOps.get(src);

        IntSList candidates = new IntSList(srclen);
        int ord = 0;
        for (int i = 0; i < dstlen; i++) {
            assert candidates.size() == srclen - i;

            Object val = op.get(dst, dstoff + i);
            int srcindex = op.indexOf(src, val, srcoff);
            int mod = candidates.indexOf(srcindex);
            if (mod == -1 || mod >= srclen)
                throw new OutOfDomainException("dst[" + (dstoff + i) + "]");  
            candidates.removeAbsolute(srcindex);

            ord = ord * (srclen - i) + mod;
        }
        return ord;
    }

    public static <A> int ord(A src, A dst) {
        int srclen = Array.getLength(src);
        int dstlen = Array.getLength(dst);
        return ord(src, 0, srclen, dst, 0, dstlen);
    }

}
