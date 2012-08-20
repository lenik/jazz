package net.bodz.bas.c.java.lang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.lang.fn.Func1;
import net.bodz.bas.util.ints.IntIterable;
import net.bodz.bas.util.ints.IntIterator;

public class Arrays
        extends _Arrays {

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static boolean[] concat(boolean[]... arrays) {
        int total = 0;
        for (boolean[] a : arrays)
            total += a.length;
        if (total == 0)
            return new boolean[0];

        boolean[] result = new boolean[total];
        int offset = 0;
        for (boolean[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static char[] concat(char[]... arrays) {
        int total = 0;
        for (char[] a : arrays)
            total += a.length;
        if (total == 0)
            return new char[0];

        char[] result = new char[total];
        int offset = 0;
        for (char[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static byte[] concat(byte[]... arrays) {
        int total = 0;
        for (byte[] a : arrays)
            total += a.length;
        if (total == 0)
            return new byte[0];

        byte[] result = new byte[total];
        int offset = 0;
        for (byte[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static short[] concat(short[]... arrays) {
        int total = 0;
        for (short[] a : arrays)
            total += a.length;
        if (total == 0)
            return new short[0];

        short[] result = new short[total];
        int offset = 0;
        for (short[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static int[] concat(int[]... arrays) {
        int total = 0;
        for (int[] a : arrays)
            total += a.length;
        if (total == 0)
            return new int[0];

        int[] result = new int[total];
        int offset = 0;
        for (int[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static long[] concat(long[]... arrays) {
        int total = 0;
        for (long[] a : arrays)
            total += a.length;
        if (total == 0)
            return new long[0];

        long[] result = new long[total];
        int offset = 0;
        for (long[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static float[] concat(float[]... arrays) {
        int total = 0;
        for (float[] a : arrays)
            total += a.length;
        if (total == 0)
            return new float[0];

        float[] result = new float[total];
        int offset = 0;
        for (float[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    public static double[] concat(double[]... arrays) {
        int total = 0;
        for (double[] a : arrays)
            total += a.length;
        if (total == 0)
            return new double[0];

        double[] result = new double[total];
        int offset = 0;
        for (double[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    /**
     * Concat multiple arrays.
     * 
     * @throws NullPointerException
     *             If any array is <code>null</code>.
     */
    @SafeVarargs
    public static <T> T[] concat(T[]... arrays) {
        Class<?> arrayType = arrays.getClass().getComponentType();
        Class<?> componentType = arrayType.getComponentType();

        int total = 0;
        for (T[] a : arrays)
            total += a.length;
        if (total == 0) {
            T[] emptyArray = (T[]) Array.newInstance(componentType, 0);
            return emptyArray;
        }

        T[] result = (T[]) Array.newInstance(componentType, total);
        int offset = 0;
        for (T[] a : arrays) {
            System.arraycopy(a, 0, result, offset, a.length);
            offset += a.length;
        }
        return result;
    }

    @SafeVarargs
    public static <T> T[] unshift(T val, T... array) {
        Class<?> componentType = array.getClass().getComponentType();
        T[] result = (T[]) Array.newInstance(componentType, array.length + 1);
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    @SafeVarargs
    public static <T> T[] push(T[] array, T... vals) {
        Class<?> componentType = array.getClass().getComponentType();
        T[] result = (T[]) Array.newInstance(componentType, array.length + vals.length);
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    public static int[] convert(IntIterable iterable) {
        int c = 0;
        IntIterator it = iterable.iterator();
        while (it.hasNext()) {
            c++;
            it.next();
        }

        int[] array = new int[c];
        int i = 0;
        it = iterable.iterator();
        while (it.hasNext())
            array[i++] = it.next();
        return array;
    }

    public static int[] convert(IntIterator iterator) {
        List<Integer> buf = new ArrayList<Integer>();
        while (iterator.hasNext())
            buf.add(iterator.next());
        int n = buf.size();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = buf.get(i);
        return array;
    }

    public static int min(int[] array) {
        return min(array, 0, array.length);
    }

    public static int min(int[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        int min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            int v = array[i];
            if (v < min)
                min = v;
        }
        return min;
    }

    public static int max(int[] array) {
        return max(array, 0, array.length);
    }

    public static int max(int[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        int max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            int v = array[i];
            if (v > max)
                max = v;
        }
        return max;
    }

    public static <T> T[] map(T[] array, Func1<T, T> fn) {
        Class<?> valType = array.getClass().getComponentType();
        T[] result = (T[]) Array.newInstance(valType, array.length);
        for (int i = 0; i < array.length; i++) {
            T val = array[i];
            val = fn.eval(val);
            result[i] = val;
        }
        return result;
    }

}
