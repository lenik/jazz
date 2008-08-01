package net.bodz.bas.types.util;

import java.lang.reflect.Array;

import net.bodz.bas.lang.Closure;

public class Perms {

    private static <A> void _iterate(A array, int off, int len,
            Closure<A> closure) {
        if (len == 1) {
            closure.execute(array);
            return;
        }
        ArrayOp<A> op = ArrayOps.get(array);
        int count = len;
        while (count-- > 0) {
            _iterate(array, off + 1, len - 1, closure);
            if (count != 0)
                op.reverse(array, off, off + len);
        }
    }

    public static <A> void iterate(A array, int off, int len, Closure<A> closure) {
        A copy = Arrays2.copyOf(array, off, len);
        _iterate(copy, off, len, closure);
    }

    public static <A> void iterateInPlace(A array, int off, int len,
            Closure<A> closure) {
        _iterate(array, off, len, closure);
        Arrays2.reverse(array, off, len);
    }

    public static <A> void iterate(A array, Closure<A> closure) {
        iterate(array, 0, Array.getLength(array), closure);
    }

    public static <A> void iterateInPlace(A array, Closure<A> closure) {
        iterateInPlace(array, 0, Array.getLength(array), closure);
    }

    public static <A> void perm(int ord, A src, int srcoff, int srclen, A dst,
            int dstoff, int dstlen) {
        ArrayOp<A> op = ArrayOps.get(src);

        int[] ordv = new int[dstlen];
        for (int n = 2; n <= dstlen; n++) { // i = 1..len-1
            int mod = ord % n; // mod = ord % (i + 1)
            ord /= n;
            ordv[dstlen - n] = mod; // ordv[len - 1 - i] = mod
        }
        assert ordv[dstlen - 1] == 0;

        IntSList candidates = new IntSList(srclen);
        for (int i = 0; i < dstlen; i++) {
            int mod = ordv[i];
            int srcindex = candidates.remove(mod);
            Object val = op.get(src, srcoff + srcindex);
            op.set(dst, dstoff + i, val);
        }
    }

    public static <A> void perm(int ord, A src, A dst) {
        int srclen = Array.getLength(src);
        int dstlen = Array.getLength(dst);
        perm(ord, src, 0, srclen, dst, 0, dstlen);
    }

    public static <A> int ord(A src, int srcoff, int srclen, A dst, int dstoff,
            int dstlen) {
        ArrayOp<A> op = ArrayOps.get(src);

        IntSList candidates = new IntSList(srclen);
        int ord = 0;
        for (int i = 0; i < dstlen; i++) {
            assert candidates.size() == srclen - i;

            Object val = op.get(dst, dstoff + i);
            int srcindex = op.indexOf(src, val, srcoff);
            int mod = candidates.indexOf(srcindex);
            if (mod == -1 || mod >= srclen)
                throw new IllegalArgumentException("invalid value at dst["
                        + (dstoff + i) + "]");
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
