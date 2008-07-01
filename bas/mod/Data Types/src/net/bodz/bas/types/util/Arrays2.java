package net.bodz.bas.types.util;

import java.lang.reflect.Array;

public class Arrays2 {

    public static Object duplicate(Object array, int off, int len) {
        Class<?> type = array.getClass();
        if (!type.isArray())
            throw new IllegalArgumentException("not an array: " + array);
        Class<?> ctype = type.getComponentType();
        int alen = Array.getLength(array);
        assert off >= 0 && len >= 0 && off + len <= alen;
        Object dup = Array.newInstance(ctype, len);
        System.arraycopy(array, off, dup, 0, len);
        return dup;
    }

    @SuppressWarnings("unchecked")
    public static <T> T concat(Object heads, Object tails) {
        // assert heads.getClass().isArray();
        // assert tails.getClass().isArray();
        int headlen;
        int taillen;
        if (heads == null || (headlen = Array.getLength(heads)) == 0)
            return (T) tails;
        if (tails == null || (taillen = Array.getLength(tails)) == 0)
            return (T) heads;
        Object[] cat = new Object[headlen + taillen];
        System.arraycopy(heads, 0, cat, 0, headlen);
        System.arraycopy(tails, 0, cat, headlen, taillen);
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
