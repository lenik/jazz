package net.bodz.bas.types.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.lang.err.NotImplementedException;

public class ArrayOps {

    abstract static class _ArrayOp<A> implements ArrayOp<A> {

        private final Class<?> componentType;

        public _ArrayOp(Class<?> componentType) {
            this.componentType = componentType;
        }

        public Class<?> getComponentType() {
            return componentType;
        }

        @SuppressWarnings("unchecked")
        @Override
        public A allocate(int size) {
            return (A) Array.newInstance(componentType, size);
        }

        @Override
        public void copy(A src, int srcoff, A dst, int dstoff, int len) {
            System.arraycopy(src, srcoff, dst, dstoff, len);
        }

        @Override
        public A copyOf(A array) {
            return copyOf(array, Array.getLength(array));
        }

        @Override
        public void sort(A array, Comparator<?> comparator) {
            throw new IllegalArgumentException("comparator isn't used");
        }

        @Override
        public void sort(A array, int fromIndex, int toIndex,
                Comparator<?> comparator) {
            throw new IllegalArgumentException("comparator isn't used");
        }
    }

    public static class Bytes extends _ArrayOp<byte[]> {
        public Bytes() {
            super(byte.class);
        }

        @Override
        public void set(byte[] array, int index, Object value) {
            array[index] = (java.lang.Byte) value;
            // AutoType.toByte(value);
        }

        @Override
        public byte[] copyOf(byte[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public byte[] copyOfRange(byte[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(byte[] array, Object key) {
            return Arrays.binarySearch(array, (java.lang.Byte) key);
        }

        @Override
        public int binarySearch(byte[] array, int fromIndex, int toIndex,
                Object key) {
            return Arrays.binarySearch(array, fromIndex, toIndex,
                    (java.lang.Byte) key);
        }

        @Override
        public void fill(byte[] array, Object val) {
            Arrays.fill(array, (java.lang.Byte) val);
        }

        public void fill(byte[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, (java.lang.Byte) val);
        }

        @Override
        public void sort(byte[] array) {
            Arrays.sort(array);
        }

        @Override
        public void sort(byte[] array, int fromIndex, int toIndex) {
            Arrays.sort(array, fromIndex, toIndex);
        }

        @Override
        public boolean equals(byte[] a, byte[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(byte[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(byte[] array) {
            return Arrays.toString(array);
        }
    }

    public static class Shorts extends _ArrayOp<short[]> {
        public Shorts() {
            super(short.class);
        }

        @Override
        public void set(short[] array, int index, Object value) {
            array[index] = (java.lang.Short) value;
            // AutoType.toShort(value);
        }

        @Override
        public short[] copyOf(short[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public short[] copyOfRange(short[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(short[] array, Object key) {
            return Arrays.binarySearch(array, (java.lang.Short) key);
        }

        @Override
        public int binarySearch(short[] array, int fromIndex, int toIndex,
                Object key) {
            return Arrays.binarySearch(array, fromIndex, toIndex,
                    (java.lang.Short) key);
        }

        @Override
        public void fill(short[] array, Object val) {
            Arrays.fill(array, (java.lang.Short) val);
        }

        @Override
        public void fill(short[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, (java.lang.Short) val);
        }

        @Override
        public void sort(short[] array) {
            Arrays.sort(array);
        }

        @Override
        public void sort(short[] array, int fromIndex, int toIndex) {
            Arrays.sort(array, fromIndex, toIndex);
        }

        @Override
        public boolean equals(short[] a, short[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(short[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(short[] array) {
            return Arrays.toString(array);
        }
    }

    public static class Ints extends _ArrayOp<int[]> {
        public Ints() {
            super(int.class);
        }

        @Override
        public void set(int[] array, int index, Object value) {
            array[index] = (java.lang.Integer) value;
            // AutoType.toInt(value);
        }

        @Override
        public int[] copyOf(int[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public int[] copyOfRange(int[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(int[] array, Object key) {
            return Arrays.binarySearch(array, (java.lang.Integer) key);
        }

        @Override
        public int binarySearch(int[] array, int fromIndex, int toIndex,
                Object key) {
            return Arrays.binarySearch(array, fromIndex, toIndex,
                    (java.lang.Integer) key);
        }

        @Override
        public void fill(int[] array, Object val) {
            Arrays.fill(array, (java.lang.Integer) val);
        }

        @Override
        public void fill(int[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, (java.lang.Integer) val);
        }

        @Override
        public void sort(int[] array) {
            Arrays.sort(array);
        }

        @Override
        public void sort(int[] array, int fromIndex, int toIndex) {
            Arrays.sort(array, fromIndex, toIndex);
        }

        @Override
        public boolean equals(int[] a, int[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(int[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(int[] array) {
            return Arrays.toString(array);
        }
    }

    public static class Longs extends _ArrayOp<long[]> {
        public Longs() {
            super(long.class);
        }

        @Override
        public void set(long[] array, int index, Object value) {
            array[index] = (java.lang.Long) value;
            // AutoType.toLong(value);
        }

        @Override
        public long[] copyOf(long[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public long[] copyOfRange(long[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(long[] array, Object key) {
            return Arrays.binarySearch(array, (java.lang.Long) key);
        }

        @Override
        public int binarySearch(long[] array, int fromIndex, int toIndex,
                Object key) {
            return Arrays.binarySearch(array, fromIndex, toIndex,
                    (java.lang.Long) key);
        }

        @Override
        public void fill(long[] array, Object val) {
            Arrays.fill(array, (java.lang.Long) val);
        }

        @Override
        public void fill(long[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, (java.lang.Long) val);
        }

        @Override
        public void sort(long[] array) {
            Arrays.sort(array);
        }

        @Override
        public void sort(long[] array, int fromIndex, int toIndex) {
            Arrays.sort(array, fromIndex, toIndex);
        }

        @Override
        public boolean equals(long[] a, long[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(long[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(long[] array) {
            return Arrays.toString(array);
        }
    }

    public static class Floats extends _ArrayOp<float[]> {
        public Floats() {
            super(float.class);
        }

        @Override
        public void set(float[] array, int index, Object value) {
            array[index] = (java.lang.Float) value;
            // AutoType.toFloat(value);
        }

        @Override
        public float[] copyOf(float[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public float[] copyOfRange(float[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(float[] array, Object key) {
            return Arrays.binarySearch(array, (java.lang.Float) key);
        }

        @Override
        public int binarySearch(float[] array, int fromIndex, int toIndex,
                Object key) {
            return Arrays.binarySearch(array, fromIndex, toIndex,
                    (java.lang.Float) key);
        }

        @Override
        public void fill(float[] array, Object val) {
            Arrays.fill(array, (java.lang.Float) val);
        }

        @Override
        public void fill(float[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, (java.lang.Float) val);
        }

        @Override
        public void sort(float[] array) {
            Arrays.sort(array);
        }

        @Override
        public void sort(float[] array, int fromIndex, int toIndex) {
            Arrays.sort(array, fromIndex, toIndex);
        }

        @Override
        public boolean equals(float[] a, float[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(float[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(float[] array) {
            return Arrays.toString(array);
        }
    }

    public static class Doubles extends _ArrayOp<double[]> {
        public Doubles() {
            super(double.class);
        }

        @Override
        public void set(double[] array, int index, Object value) {
            array[index] = (java.lang.Double) value;
            // AutoType.toDouble(value);
        }

        @Override
        public double[] copyOf(double[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public double[] copyOfRange(double[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(double[] array, Object key) {
            return Arrays.binarySearch(array, (java.lang.Double) key);
        }

        @Override
        public int binarySearch(double[] array, int fromIndex, int toIndex,
                Object key) {
            return Arrays.binarySearch(array, fromIndex, toIndex,
                    (java.lang.Double) key);
        }

        @Override
        public void fill(double[] array, Object val) {
            Arrays.fill(array, (java.lang.Double) val);
        }

        @Override
        public void fill(double[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, (java.lang.Double) val);
        }

        @Override
        public void sort(double[] array) {
            Arrays.sort(array);
        }

        @Override
        public void sort(double[] array, int fromIndex, int toIndex) {
            Arrays.sort(array, fromIndex, toIndex);
        }

        @Override
        public boolean equals(double[] a, double[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(double[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(double[] array) {
            return Arrays.toString(array);
        }
    }

    public static class Booleans extends _ArrayOp<boolean[]> {
        public Booleans() {
            super(boolean.class);
        }

        @Override
        public void set(boolean[] array, int index, Object value) {
            array[index] = (java.lang.Boolean) value;
            // AutoType.toBoolean(value);
        }

        @Override
        public boolean[] copyOf(boolean[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public boolean[] copyOfRange(boolean[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(boolean[] array, Object key) {
            throw new NotImplementedException();
            // return Arrays.binarySearch(array, (java.lang.Boolean) key);
        }

        @Override
        public int binarySearch(boolean[] array, int fromIndex, int toIndex,
                Object key) {
            throw new NotImplementedException();
            // return Arrays.binarySearch(array, fromIndex, toIndex,
            // (java.lang.Boolean) key);
        }

        @Override
        public void fill(boolean[] array, Object val) {
            Arrays.fill(array, (java.lang.Boolean) val);
        }

        @Override
        public void fill(boolean[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, (java.lang.Boolean) val);
        }

        @Override
        public void sort(boolean[] array) {
            throw new NotImplementedException();
        }

        @Override
        public void sort(boolean[] array, int fromIndex, int toIndex) {
            throw new NotImplementedException();
        }

        @Override
        public boolean equals(boolean[] a, boolean[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(boolean[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(boolean[] array) {
            return Arrays.toString(array);
        }
    }

    public static class Chars extends _ArrayOp<char[]> {
        public Chars() {
            super(char.class);
        }

        @Override
        public void set(char[] array, int index, Object value) {
            array[index] = (java.lang.Character) value;
            // AutoType.toChar(value);
        }

        @Override
        public char[] copyOf(char[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public char[] copyOfRange(char[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(char[] array, Object key) {
            return Arrays.binarySearch(array, (java.lang.Character) key);
        }

        @Override
        public int binarySearch(char[] array, int fromIndex, int toIndex,
                Object key) {
            return Arrays.binarySearch(array, fromIndex, toIndex,
                    (java.lang.Character) key);
        }

        @Override
        public void fill(char[] array, Object val) {
            Arrays.fill(array, (java.lang.Character) val);
        }

        @Override
        public void fill(char[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, (java.lang.Character) val);
        }

        @Override
        public void sort(char[] array) {
            Arrays.sort(array);
        }

        @Override
        public void sort(char[] array, int fromIndex, int toIndex) {
            Arrays.sort(array, fromIndex, toIndex);
        }

        @Override
        public boolean equals(char[] a, char[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(char[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(char[] array) {
            return Arrays.toString(array);
        }
    }

    public static class Objects<T> extends _ArrayOp<T[]> {
        public Objects(Class<T> clazz) {
            super(clazz);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void set(T[] array, int index, Object value) {
            array[index] = (T) value;
        }

        @Override
        public T[] copyOf(T[] array, int newLength) {
            return Arrays.copyOf(array, newLength);
        }

        @Override
        public T[] copyOfRange(T[] array, int from, int to) {
            return Arrays.copyOfRange(array, from, to);
        }

        @Override
        public int binarySearch(T[] array, Object key) {
            return Arrays.binarySearch(array, key);
        }

        @Override
        public int binarySearch(T[] array, int fromIndex, int toIndex,
                Object key) {
            return Arrays.binarySearch(array, fromIndex, toIndex, key);
        }

        @Override
        public void fill(T[] array, Object val) {
            Arrays.fill(array, val);
        }

        @Override
        public void fill(T[] array, int fromIndex, int toIndex, Object val) {
            Arrays.fill(array, fromIndex, toIndex, val);
        }

        @Override
        public void sort(T[] array) {
            Arrays.sort(array);
        }

        @Override
        public void sort(T[] array, int fromIndex, int toIndex) {
            Arrays.sort(array, fromIndex, toIndex);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void sort(T[] array, Comparator<?> comparator) {
            Arrays.sort(array, (Comparator<? super T>) comparator);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void sort(T[] array, int fromIndex, int toIndex,
                Comparator<?> comparator) {
            Arrays.sort(array, fromIndex, toIndex,
                    (Comparator<? super T>) comparator);
        }

        @Override
        public boolean equals(T[] a, T[] b) {
            return Arrays.equals(a, b);
        }

        @Override
        public int hashCode(T[] array) {
            return Arrays.hashCode(array);
        }

        @Override
        public String toString(T[] array) {
            return Arrays.toString(array);
        }
    }

    private static final Map<Class<?>, ArrayOp<?>> ops;

    public static final Bytes                      Bytes;
    public static final Shorts                     Shorts;
    public static final Ints                       Ints;
    public static final Longs                      Longs;
    public static final Floats                     Floats;
    public static final Doubles                    Doubles;
    public static final Booleans                   Booleans;
    public static final Chars                      Chars;
    public static final Objects<Object>            Objects;

    static {
        ops = new HashMap<Class<?>, ArrayOp<?>>();
        ops.put(byte.class, Bytes = new Bytes());
        ops.put(short.class, Shorts = new Shorts());
        ops.put(int.class, Ints = new Ints());
        ops.put(long.class, Longs = new Longs());
        ops.put(float.class, Floats = new Floats());
        ops.put(double.class, Doubles = new Doubles());
        ops.put(boolean.class, Booleans = new Booleans());
        ops.put(char.class, Chars = new Chars());
        ops.put(Object.class, Objects = new Objects<Object>(Object.class));
    }

    @SuppressWarnings("unchecked")
    public static <A> ArrayOp<A> get(Class<A> arrayType) {
        if (!arrayType.isArray())
            throw new IllegalArgumentException("not type of array: "
                    + arrayType);
        Class<?> componentType = arrayType.getComponentType();
        return (ArrayOp<A>) ops.get(componentType);
    }

}
