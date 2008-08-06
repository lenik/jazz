package net.bodz.bas.types.util;

import java.lang.reflect.Array;

import net.bodz.bas.lang.err.IllegalArgumentTypeException;

public class Arrays2 {

    public static Object[] _(Object... array) {
        return array;
    }

    public static <T> T copyOf(T array, int off, int len) {
        Class<?> type = array.getClass();
        if (!type.isArray())
            throw new IllegalArgumentTypeException(array, "array");
        Class<?> ctype = type.getComponentType();
        int alen = Array.getLength(array);
        assert off >= 0 && len >= 0 && off + len <= alen;
        // if (ctype.isPrimitive())
        @SuppressWarnings("unchecked") T dup = (T) Array
                .newInstance(ctype, len);
        System.arraycopy(array, off, dup, 0, len);
        return dup;
    }

    public static Object copyOf(Object array) {
        int len = Array.getLength(array);
        return copyOf(array, 0, len);
    }

    @SuppressWarnings("unchecked")
    static <A> A toArray(Object objectOrArray) {
        Class<?> clazz = objectOrArray.getClass();
        if (clazz.isArray())
            return (A) objectOrArray;
        A v = (A) Array.newInstance(clazz, 1);
        Array.set(v, 0, objectOrArray);
        return v;
    }

    @SuppressWarnings("unchecked")
    public static <A> A _concat(A heads, A tails) {
        assert heads != null && tails != null;
        int headlen = Array.getLength(heads);
        if (headlen == 0)
            return tails;
        int taillen = Array.getLength(tails);
        if (taillen == 0)
            return heads;
        Class<?> clazz = heads.getClass().getComponentType();
        A cat = (A) Array.newInstance(clazz, headlen + taillen);
        System.arraycopy(heads, 0, cat, 0, headlen);
        System.arraycopy(tails, 0, cat, headlen, taillen);
        return cat;
    }

    @SuppressWarnings("unchecked")
    public static <A> A concat(Object heads, Object tails) {
        Class<?> headType;
        if (heads == null || !(headType = heads.getClass()).isArray()) {
            if (tails == null)
                return (A) new Object[] { null, null };
            Class<?> tailType = tails.getClass();
            Class<?> type = tailType.isArray() ? tailType.getComponentType()
                    : tailType;
            A headsArray = (A) Array.newInstance(type, 1);
            Array.set(headsArray, 0, heads);
            return concat(headsArray, tails);
        }
        assert heads != null && headType.isArray();
        Class<?> type = headType.getComponentType();
        if (tails == null || !tails.getClass().isArray()) {
            A tailsArray = (A) Array.newInstance(type, 1);
            Array.set(tailsArray, 0, tails);
            return concat(heads, tailsArray);
        }
        return _concat((A) heads, (A) tails);
    }

    @SuppressWarnings("unchecked")
    public static <A> A concatl(Object heads, Object... tails) {
        return concat(heads, (A) tails);
    }

    public static void reverse(Object array, int off, int len) {
        if (len < 2)
            return;
        int end = off + len - 1;
        while (off < end) {
            Object left = Array.get(array, off);
            Object right = Array.get(array, end);
            Array.set(array, off, right);
            Array.set(array, end, left);
            off++;
            end--;
        }
    }

    public static void reverse(Object array) {
        reverse(array, 0, Array.getLength(array));
    }

}
