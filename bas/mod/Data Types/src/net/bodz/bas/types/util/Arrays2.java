package net.bodz.bas.types.util;

import java.lang.reflect.Array;

public class Arrays2 {

    public static Object copyOf(Object array, int off, int len) {
        Class<?> type = array.getClass();
        if (!type.isArray())
            throw new IllegalArgumentException("not an array: " + array);
        Class<?> ctype = type.getComponentType();
        int alen = Array.getLength(array);
        assert off >= 0 && len >= 0 && off + len <= alen;
        Object dup;
        // if (ctype.isPrimitive())
        dup = Array.newInstance(ctype, len);
        System.arraycopy(array, off, dup, 0, len);
        return dup;
    }

    public static Object copyOf(Object array) {
        int len = Array.getLength(array);
        return copyOf(array, 0, len);
    }

    @SuppressWarnings("unchecked")
    public static <T> T concat(Object heads, Object tails) {
        boolean headarray = heads != null && heads.getClass().isArray();
        boolean tailarray = tails != null && tails.getClass().isArray();
        int headlen = headarray ? Array.getLength(heads) : 1;
        int taillen = tailarray ? Array.getLength(tails) : 1;
        if (headlen == 0)
            return tailarray ? (T) tails : (T) (new Object[] { tails });
        if (taillen == 0)
            return headarray ? (T) heads : (T) (new Object[] { heads });

        Object[] cat = new Object[headlen + taillen];

        if (headarray)
            System.arraycopy(heads, 0, cat, 0, headlen);
        else
            cat[0] = heads;

        if (tailarray)
            System.arraycopy(tails, 0, cat, headlen, taillen);
        else
            cat[headlen] = tails;

        return (T) cat;
    }

    public static <T> T concatl(Object heads, Object... tails) {
        return concat(heads, (Object) tails);
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
