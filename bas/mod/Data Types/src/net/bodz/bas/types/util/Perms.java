package net.bodz.bas.types.util;

import java.lang.reflect.Array;

import net.bodz.bas.lang.Closure;

public class Perms {

    public static void _iterate(Object array, int off, int len,
            Closure<Object> closure) {
        if (len == 1) {
            closure.execute(array);
            return;
        }
        int count = len;
        while (count-- > 0) {
            _iterate(array, off + 1, len - 1, closure);
            if (count != 0)
                Arrays2.reverse(array, off, len);
        }
    }

    public static void iterate(Object array, int off, int len,
            Closure<Object> closure) {
        _iterate(array, off, len, closure);
        Arrays2.reverse(array, off, len);
    }

    public static void iterate(Object array, Closure<Object> closure) {
        iterate(array, 0, Array.getLength(array), closure);
    }

}
