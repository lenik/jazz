package net.bodz.bas.types.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.lang.Filt1;
import net.bodz.bas.lang.Pred1;
import net.bodz.bas.lang.err.IllegalArgumentTypeException;

public class Arrays2 {

    public static Object[] _(Object... array) {
        return array;
    }

    /**
     * @throws NullPointerException
     *             if <code>array</code> is <code>null</code>.
     */
    public static <T> T copyOf(T array, int off, int len) {
        if (array == null)
            throw new NullPointerException("array");
        Class<?> type = array.getClass();
        if (!type.isArray())
            throw new IllegalArgumentTypeException(array, "array");
        Class<?> ctype = type.getComponentType();
        int alen = Array.getLength(array);
        assert off >= 0 && len >= 0 && off + len <= alen;
        // if (ctype.isPrimitive())
        @SuppressWarnings("unchecked")
        T dup = (T) Array.newInstance(ctype, len);
        System.arraycopy(array, off, dup, 0, len);
        return dup;
    }

    /**
     * @return <code>null</code> if <code>array</code> is <code>null</code>.
     */
    public static <T> T copyOf(T array) {
        if (array == null)
            return null;
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

    public static <A> A concat(A... arrays) {
        int n = 0;
        A sig = null;
        for (int i = 0; i < arrays.length; i++)
            if (arrays[i] != null)
                n += Array.getLength(sig = arrays[i]);
        if (sig == null)
            throw new IllegalArgumentException(
                    "don't know the component type, all arrays are null");
        Class<?> valtype = sig.getClass().getComponentType();
        @SuppressWarnings("unchecked")
        A cat = (A) Array.newInstance(valtype, n);
        int base = 0;
        for (int i = 0; i < arrays.length; i++) {
            A a = arrays[i];
            int alen = Array.getLength(a);
            if (a != null)
                System.arraycopy(a, 0, cat, base, alen);
            base += alen;
        }
        return cat;
    }

    @SuppressWarnings("unchecked")
    public static <A> A concatv(Object heads, Object tails) {
        Class<?> headType;
        if (heads == null || !(headType = heads.getClass()).isArray()) {
            if (tails == null)
                return (A) new Object[] { null, null };
            Class<?> tailType = tails.getClass();
            Class<?> type = tailType.isArray() ? tailType.getComponentType()
                    : tailType;
            A headsArray = (A) Array.newInstance(type, 1);
            Array.set(headsArray, 0, heads);
            return concatv(headsArray, tails);
        }
        assert heads != null && headType.isArray();
        Class<?> type = headType.getComponentType();
        if (tails == null || !tails.getClass().isArray()) {
            A tailsArray = (A) Array.newInstance(type, 1);
            Array.set(tailsArray, 0, tails);
            return concatv(heads, tailsArray);
        }
        return concat((A) heads, (A) tails);
    }

    @SuppressWarnings("unchecked")
    public static <A> A concatvl(Object heads, Object... tails) {
        return concatv(heads, (A) tails);
    }

    /**
     * @throws NullPointerException
     *             if <code>array</code> is <code>null</code>
     */
    public static void reverse(Object array, int off, int len) {
        if (array == null)
            throw new NullPointerException("array");
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

    /**
     * @throws NullPointerException
     *             if <code>array</code> is <code>null</code>
     */
    public static void reverse(Object array) {
        reverse(array, 0, Array.getLength(array));
    }

    @SuppressWarnings("unchecked")
    public static <A> A map(A array, Filt1<?, ?> filter) {
        Filt1<Object, Object> _filter = (Filt1<Object, Object>) filter;
        return (A) map((Object[]) array, _filter);
    }

    public static <T> T[] map(T[] array, Filt1<T, T> filter) {
        if (array == null)
            return null;
        Class<?> t = array.getClass().getComponentType();
        int len = array.length;
        @SuppressWarnings("unchecked")
        T[] dest = (T[]) Array.newInstance(t, len);
        for (int i = 0; i < len; i++) {
            T v = array[i];
            v = filter.filter(v);
            dest[i] = v;
        }
        return dest;
    }

    public static <A> A grep(A array, Pred1<?> pred) {
        return grep(array, pred, 0.5f);
    }

    @SuppressWarnings("unchecked")
    public static <A> A grep(A array, Pred1<?> pred, float appxHit) {
        Pred1<Object> _pred = (Pred1<Object>) pred;
        return (A) grep((Object[]) array, _pred, appxHit);
    }

    public static <T> T[] grep(T[] array, Pred1<T> pred) {
        return grep(array, pred, 0.5f);
    }

    public static <T> T[] grep(T[] array, Pred1<T> pred, float appxHit) {
        if (array == null)
            return null;
        Class<?> t = array.getClass().getComponentType();
        int len = array.length;
        int cap = (int) (len * appxHit);
        // cap = (cap + 0x10) & ~0xf;
        List<T> list = new ArrayList<T>(cap);
        for (int i = 0; i < len; i++) {
            T v = array[i];
            if (pred.test(v))
                list.add(v);
        }
        @SuppressWarnings("unchecked")
        T[] dest = (T[]) Array.newInstance(t, list.size());
        return list.toArray(dest);
    }

}
