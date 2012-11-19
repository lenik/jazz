package net.bodz.bas.c.java.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.bodz.bas.c.java.util.array.*;
import net.bodz.bas.lang.fn.Func1;
import net.bodz.bas.util.Pair;
import net.bodz.bas.util.ints.IntIterable;
import net.bodz.bas.util.ints.IntIterator;

public class Arrays
        extends _Arrays {

    public static <T> Class<T[]> getArrayType(Class<T> componentType) {
        Object array = Array.newInstance(componentType, 0);
        return (Class<T[]>) array.getClass();
    }

    public static <T> ArrayWrapper<T> wrap(T[] array) {
        return new ArrayWrapper<T>(array);
    }

    public static <T> ArrayWrapper<T> wrap(T[] array, int start, int end) {
        return new ArrayWrapper<T>(array, start, end);
    }

    public static ByteArrayWrapper wrap(byte[] array, int start) {
        return wrap(array, start, array.length);
    }

    public static ByteArrayWrapper wrap(byte[] array) {
        return new ByteArrayWrapper(array);
    }

    public static ByteArrayWrapper wrap(byte[] array, int start, int end) {
        return new ByteArrayWrapper(array, start, end);
    }

    public static IntArrayWrapper wrap(int[] array, int start) {
        return wrap(array, start, array.length);
    }

    public static IntArrayWrapper wrap(int[] array) {
        return new IntArrayWrapper(array);
    }

    public static IntArrayWrapper wrap(int[] array, int start, int end) {
        return new IntArrayWrapper(array, start, end);
    }

    public static LongArrayWrapper wrap(long[] array, int start) {
        return wrap(array, start, array.length);
    }

    public static LongArrayWrapper wrap(long[] array) {
        return new LongArrayWrapper(array);
    }

    public static LongArrayWrapper wrap(long[] array, int start, int end) {
        return new LongArrayWrapper(array, start, end);
    }

    public static FloatArrayWrapper wrap(float[] array, int start) {
        return wrap(array, start, array.length);
    }

    public static FloatArrayWrapper wrap(float[] array) {
        return new FloatArrayWrapper(array);
    }

    public static FloatArrayWrapper wrap(float[] array, int start, int end) {
        return new FloatArrayWrapper(array, start, end);
    }

    public static DoubleArrayWrapper wrap(double[] array, int start) {
        return wrap(array, start, array.length);
    }

    public static DoubleArrayWrapper wrap(double[] array) {
        return new DoubleArrayWrapper(array);
    }

    public static DoubleArrayWrapper wrap(double[] array, int start, int end) {
        return new DoubleArrayWrapper(array, start, end);
    }

    public static BooleanArrayWrapper wrap(boolean[] array, int start) {
        return wrap(array, start, array.length);
    }

    public static BooleanArrayWrapper wrap(boolean[] array) {
        return new BooleanArrayWrapper(array);
    }

    public static BooleanArrayWrapper wrap(boolean[] array, int start, int end) {
        return new BooleanArrayWrapper(array, start, end);
    }

    public static CharArrayWrapper wrap(char[] array, int start) {
        return wrap(array, start, array.length);
    }

    public static CharArrayWrapper wrap(char[] array) {
        return new CharArrayWrapper(array);
    }

    public static CharArrayWrapper wrap(char[] array, int start, int end) {
        return new CharArrayWrapper(array, start, end);
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

    /**
     * Concat
     */
    public static <T> T concat(Class<?> arrayType, T array, Object... elements) {
        if (arrayType == null)
            throw new NullPointerException("arrayType");
        if (array == null)
            throw new NullPointerException("array");
        if (elements == null)
            throw new NullPointerException("elements");

        Class<?> valType = arrayType.getComponentType();
        int len = Array.getLength(array);

        if (elements.length == 0) {
            T copy = (T) Array.newInstance(valType, elements.length);
            System.arraycopy(array, 0, copy, 0, len);
            return copy;
        }

        @SuppressWarnings("unchecked")
        T result = (T) concat((Object[]) array, elements);
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
    public static <T> T[] prepend(T val, T... array) {
        Class<?> componentType = array.getClass().getComponentType();
        T[] result = (T[]) Array.newInstance(componentType, array.length + 1);
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    @SafeVarargs
    public static <T> T[] append(T[] array, T... vals) {
        Class<?> componentType = array.getClass().getComponentType();
        T[] result = (T[]) Array.newInstance(componentType, array.length + vals.length);
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    @SafeVarargs
    public static <T> Pair<T, T[]> shift(T... array) {
        if (array.length == 0)
            return null;
        T[] remaining = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<T, T[]>(array[0], remaining);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    @SafeVarargs
    public static <T> Pair<T[], T> pop(T... array) {
        if (array.length == 0)
            return null;
        T[] remaining = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<T[], T>(remaining, array[array.length - 1]);
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

    // Array Wrapper

    // Generated Code: See ArraysCG, ArraysTemplate
    // ============== BEGIN OF GENERATED CODE ================

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
     * Prepend value to the array.
     */
    public static byte[] prepend(byte val, byte... array) {
        byte[] result = new byte[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    /**
     * Prepend value to the array.
     */
    public static short[] prepend(short val, short... array) {
        short[] result = new short[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    /**
     * Prepend value to the array.
     */
    public static int[] prepend(int val, int... array) {
        int[] result = new int[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    /**
     * Prepend value to the array.
     */
    public static long[] prepend(long val, long... array) {
        long[] result = new long[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    /**
     * Prepend value to the array.
     */
    public static float[] prepend(float val, float... array) {
        float[] result = new float[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    /**
     * Prepend value to the array.
     */
    public static double[] prepend(double val, double... array) {
        double[] result = new double[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    /**
     * Prepend value to the array.
     */
    public static char[] prepend(char val, char... array) {
        char[] result = new char[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    /**
     * Prepend value to the array.
     */
    public static boolean[] prepend(boolean val, boolean... array) {
        boolean[] result = new boolean[1 + array.length];
        result[0] = val;
        System.arraycopy(array, 0, result, 1, array.length);
        return result;
    }

    /**
     * Append values to the array.
     */
    public static byte[] append(byte[] array, byte... vals) {
        byte[] result = new byte[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Append values to the array.
     */
    public static short[] append(short[] array, short... vals) {
        short[] result = new short[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Append values to the array.
     */
    public static int[] append(int[] array, int... vals) {
        int[] result = new int[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Append values to the array.
     */
    public static long[] append(long[] array, long... vals) {
        long[] result = new long[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Append values to the array.
     */
    public static float[] append(float[] array, float... vals) {
        float[] result = new float[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Append values to the array.
     */
    public static double[] append(double[] array, double... vals) {
        double[] result = new double[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Append values to the array.
     */
    public static char[] append(char[] array, char... vals) {
        char[] result = new char[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Append values to the array.
     */
    public static boolean[] append(boolean[] array, boolean... vals) {
        boolean[] result = new boolean[array.length + vals.length];
        System.arraycopy(array, 0, result, 0, array.length);
        System.arraycopy(vals, 0, result, array.length, vals.length);
        return result;
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Byte, byte[]> shift(byte... array) {
        if (array.length == 0)
            return null;
        byte[] remaining = new byte[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Byte, byte[]>(array[0], remaining);
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Short, short[]> shift(short... array) {
        if (array.length == 0)
            return null;
        short[] remaining = new short[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Short, short[]>(array[0], remaining);
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Integer, int[]> shift(int... array) {
        if (array.length == 0)
            return null;
        int[] remaining = new int[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Integer, int[]>(array[0], remaining);
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Long, long[]> shift(long... array) {
        if (array.length == 0)
            return null;
        long[] remaining = new long[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Long, long[]>(array[0], remaining);
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Float, float[]> shift(float... array) {
        if (array.length == 0)
            return null;
        float[] remaining = new float[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Float, float[]>(array[0], remaining);
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Double, double[]> shift(double... array) {
        if (array.length == 0)
            return null;
        double[] remaining = new double[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Double, double[]>(array[0], remaining);
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Character, char[]> shift(char... array) {
        if (array.length == 0)
            return null;
        char[] remaining = new char[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Character, char[]>(array[0], remaining);
    }

    /**
     * Shift out from the array.
     * 
     * @return A pair contains the shifted value and the remaining array. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<Boolean, boolean[]> shift(boolean... array) {
        if (array.length == 0)
            return null;
        boolean[] remaining = new boolean[array.length - 1];
        System.arraycopy(array, 1, remaining, 0, array.length - 1);
        return new Pair<Boolean, boolean[]>(array[0], remaining);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<byte[], Byte> pop(byte... array) {
        if (array.length == 0)
            return null;
        byte[] remaining = new byte[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<byte[], Byte>(remaining, array[array.length - 1]);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<short[], Short> pop(short... array) {
        if (array.length == 0)
            return null;
        short[] remaining = new short[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<short[], Short>(remaining, array[array.length - 1]);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<int[], Integer> pop(int... array) {
        if (array.length == 0)
            return null;
        int[] remaining = new int[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<int[], Integer>(remaining, array[array.length - 1]);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<long[], Long> pop(long... array) {
        if (array.length == 0)
            return null;
        long[] remaining = new long[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<long[], Long>(remaining, array[array.length - 1]);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<float[], Float> pop(float... array) {
        if (array.length == 0)
            return null;
        float[] remaining = new float[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<float[], Float>(remaining, array[array.length - 1]);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<double[], Double> pop(double... array) {
        if (array.length == 0)
            return null;
        double[] remaining = new double[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<double[], Double>(remaining, array[array.length - 1]);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<char[], Character> pop(char... array) {
        if (array.length == 0)
            return null;
        char[] remaining = new char[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<char[], Character>(remaining, array[array.length - 1]);
    }

    /**
     * Pop out from the array.
     * 
     * @return A pair contains the remaining array and the popped value. Returns <code>null</code>
     *         if the array is empty.
     */
    public static Pair<boolean[], Boolean> pop(boolean... array) {
        if (array.length == 0)
            return null;
        boolean[] remaining = new boolean[array.length - 1];
        System.arraycopy(array, 0, remaining, 0, array.length - 1);
        return new Pair<boolean[], Boolean>(remaining, array[array.length - 1]);
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static byte[] reverse(byte... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(byte[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            byte temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static short[] reverse(short... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(short[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            short temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static int[] reverse(int... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(int[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static long[] reverse(long... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(long[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            long temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static float[] reverse(float... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(float[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            float temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static double[] reverse(double... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(double[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            double temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static char[] reverse(char... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(char[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static boolean[] reverse(boolean... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(boolean[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            boolean temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @return The input array.
     */
    public static Object[] reverse(Object... array) {
        reverse(array, 0, array.length);
        return array;
    }

    /**
     * Reverse the order of elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be reversed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void reverse(Object[] array, int begin, int end) {
        int swaps = (end - begin) / 2;
        assert swaps >= 0;
        int left = begin;
        int right = end - 1;
        while (swaps-- != 0) {
            Object temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static byte[] shuffle(byte... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(byte[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            byte temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static short[] shuffle(short... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(short[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            short temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static int[] shuffle(int... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(int[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            int temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static long[] shuffle(long... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(long[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            long temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static float[] shuffle(float... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(float[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            float temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static double[] shuffle(double... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(double[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            double temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static char[] shuffle(char... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(char[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            char temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static boolean[] shuffle(boolean... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(boolean[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            boolean temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @return The input array.
     */
    public static Object[] shuffle(Object... array) {
        shuffle(array, 0, array.length, new Random(), 3);
        return array;
    }

    /**
     * Shuffle the elements in the array.
     * 
     * @param array
     *            Non-<code>null</code> array to be shuffled.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The input array.
     */
    public static void shuffle(Object[] array, int begin, int end, Random random, int strength) {
        int length = end - begin;
        while (strength-- > 0) {
            int n = begin + random.nextInt(length);
            int m = begin + random.nextInt(length);
            if (n == m)
                continue;
            Object temp = array[n];
            array[n] = array[m];
            array[m] = temp;
        }
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static int sum(byte... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static int sum(byte[] array, int begin, int end) {
        int sum = (byte) 0;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return sum;
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static int sum(short... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static int sum(short[] array, int begin, int end) {
        int sum = (short) 0;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return sum;
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static long sum(int... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static long sum(int[] array, int begin, int end) {
        long sum = 0;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return sum;
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static long sum(long... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static long sum(long[] array, int begin, int end) {
        long sum = 0L;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return sum;
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static float sum(float... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static float sum(float[] array, int begin, int end) {
        float sum = 0.0f;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return sum;
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static double sum(double... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static double sum(double[] array, int begin, int end) {
        double sum = 0.0;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return sum;
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static int sum(char... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static int sum(char[] array, int begin, int end) {
        int sum = '\0';
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return sum;
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static BigDecimal sum(BigDecimal... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static BigDecimal sum(BigDecimal[] array, int begin, int end) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = begin; i < end; i++)
            sum = sum.add(array[i]);
        return sum;
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @return The sum value.
     */
    public static BigInteger sum(BigInteger... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the total value of an array.
     * 
     * @param array
     *            Non-<code>null</code> array to be summed.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The sum value.
     */
    public static BigInteger sum(BigInteger[] array, int begin, int end) {
        BigInteger sum = BigInteger.ZERO;
        for (int i = begin; i < end; i++)
            sum = sum.add(array[i]);
        return sum;
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static byte average(byte... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static byte average(byte[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        int sum = (byte) 0;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return (byte) (sum / n);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static short average(short... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static short average(short[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        int sum = (short) 0;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return (short) (sum / n);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static int average(int... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static int average(int[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        long sum = 0;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return (int) (sum / n);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static long average(long... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static long average(long[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        long sum = 0L;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return (sum / n);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static float average(float... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static float average(float[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        float sum = 0.0f;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return (sum / n);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static double average(double... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static double average(double[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        double sum = 0.0;
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return (sum / n);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static char average(char... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static char average(char[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        int sum = '\0';
        for (int i = begin; i < end; i++)
            sum = sum + array[i];
        return (char) (sum / n);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigDecimal average(BigDecimal... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigDecimal average(BigDecimal[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = begin; i < end; i++)
            sum = sum.add(array[i]);
        return (sum.divide(BigDecimal.valueOf(n)));
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigInteger average(BigInteger... array) {
        return min(array, 0, array.length);
    }

    /**
     * Calculate the average value of an array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array to be calculated.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The average value.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigInteger average(BigInteger[] array, int begin, int end) {
        int n = end - begin;
        if (n <= 0)
            throw new IllegalArgumentException("Can't calculate average for empty set.");
        BigInteger sum = BigInteger.ZERO;
        for (int i = begin; i < end; i++)
            sum = sum.add(array[i]);
        return (sum.divide(BigInteger.valueOf(n)));
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static byte min(byte... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static byte min(byte[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        byte min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            byte v = array[i];
            if (v < min)
                min = v;
        }
        return min;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static short min(short... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static short min(short[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        short min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            short v = array[i];
            if (v < min)
                min = v;
        }
        return min;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static int min(int... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
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

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static long min(long... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static long min(long[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        long min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            long v = array[i];
            if (v < min)
                min = v;
        }
        return min;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static float min(float... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static float min(float[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        float min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            float v = array[i];
            if (v < min)
                min = v;
        }
        return min;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static double min(double... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static double min(double[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        double min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            double v = array[i];
            if (v < min)
                min = v;
        }
        return min;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static char min(char... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static char min(char[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        char min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            char v = array[i];
            if (v < min)
                min = v;
        }
        return min;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigDecimal min(BigDecimal... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigDecimal min(BigDecimal[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        BigDecimal min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            BigDecimal v = array[i];
            if (v.compareTo(min) < 0)
                min = v;
        }
        return min;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigInteger min(BigInteger... array) {
        return min(array, 0, array.length);
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigInteger min(BigInteger[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        BigInteger min = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            BigInteger v = array[i];
            if (v.compareTo(min) < 0)
                min = v;
        }
        return min;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static byte max(byte... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static byte max(byte[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        byte max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            byte v = array[i];
            if (v > max)
                max = v;
        }
        return max;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static short max(short... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static short max(short[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        short max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            short v = array[i];
            if (v > max)
                max = v;
        }
        return max;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static int max(int... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
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

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static long max(long... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static long max(long[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        long max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            long v = array[i];
            if (v > max)
                max = v;
        }
        return max;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static float max(float... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static float max(float[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        float max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            float v = array[i];
            if (v > max)
                max = v;
        }
        return max;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static double max(double... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static double max(double[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        double max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            double v = array[i];
            if (v > max)
                max = v;
        }
        return max;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static char max(char... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static char max(char[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        char max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            char v = array[i];
            if (v > max)
                max = v;
        }
        return max;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigDecimal max(BigDecimal... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigDecimal max(BigDecimal[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        BigDecimal max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            BigDecimal v = array[i];
            if (v.compareTo(max) > 0)
                max = v;
        }
        return max;
    }

    /**
     * Get the minimum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @return The minimum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigInteger max(BigInteger... array) {
        return max(array, 0, array.length);
    }

    /**
     * Get the maximum element in the array.
     * 
     * @param array
     *            Non-<code>null</code> and non-empty array.
     * @param begin
     *            The inclusive begin index.
     * @param end
     *            The exclusive end index.
     * @return The maximum element.
     * @throws IllegalArgumentException
     *             If the array is empty.
     */
    public static BigInteger max(BigInteger[] array, int offset, int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1" + length);

        BigInteger max = array[offset];
        int end = offset + length;
        for (int i = offset + 1; i < end; i++) {
            BigInteger v = array[i];
            if (v.compareTo(max) > 0)
                max = v;
        }
        return max;
    }

    // ============== END OF GENERATED CODE ================

}
