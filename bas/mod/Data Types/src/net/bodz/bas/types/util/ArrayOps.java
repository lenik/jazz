package net.bodz.bas.types.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.bodz.bas.lang.err.IllegalArgumentTypeException;
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
        public A toArray(Iterator<?> iterator, int size) {
            A array = allocate(size);
            int index = 0;
            while (iterator.hasNext() && index < size) {
                Object value = iterator.next();
                set(array, index, value);
                index++;
            }
            return array;
        }

        @Override
        public A toArray(Iterable<?> iterable, int size) {
            return toArray(iterable.iterator(), size);
        }

        @Override
        public A toArray(List<?> list) {
            return toArray(list, list.size());
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
        public int indexOf(A array, Object key) {
            return indexOf(array, key, 0);
        }

        @Override
        public int indexOf(A array, Object key, int start) {
            return indexOf(array, 0, Array.getLength(array), key, start);
        }

        @Override
        public int lastIndexOf(A array, Object key) {
            return lastIndexOf(array, key, Array.getLength(array));
        }

        @Override
        public int lastIndexOf(A array, Object key, int start) {
            return lastIndexOf(array, 0, Array.getLength(array), key, start);
        }

        @Override
        public void sort(A array, Comparator<?> comparator) {
            throw new UnsupportedOperationException("comparator isn't used");
        }

        @Override
        public void sort(A array, int fromIndex, int toIndex,
                Comparator<?> comparator) {
            throw new UnsupportedOperationException("comparator isn't used");
        }

        @Override
        public void reverse(A array) {
            reverse(array, 0, Array.getLength(array));
        }

        public Object contents(A array, boolean deep) {
            return new ArrayContents<A, Object>(array, deep, this);
        }

        public Object contents(A array) {
            return new ArrayContents<A, Object>(array, false, this);
        }

        @Override
        public boolean equals(A a, int aoff, A b, int boff, int len) {
            assert a != null;
            assert b != null;
            assert aoff >= 0;
            assert boff >= 0;
            int alen = Array.getLength(a);
            int blen = Array.getLength(b);
            assert aoff + len <= alen;
            assert boff + len <= blen;
            if (aoff == 0 && boff == 0 && alen == len && blen == len)
                return equals(a, b);
            return _equals(a, aoff, b, boff, len);
        }

        protected boolean _equals(A a, int aoff, A b, int boff, int len) {
            while (--len >= 0) {
                Object u = Array.get(a, aoff++);
                Object v = Array.get(b, boff++);
                if (u == v)
                    continue;
                if (u == null || v == null)
                    return false;
                if (!u.equals(v))
                    return false;
            }
            return true;
        }

        @Override
        public boolean equalsWithWrap(A pattern, A array) {
            return equalsWithWrap(pattern, array, 0, Array.getLength(array));
        }

        @Override
        public boolean equalsWithWrap(A pattern, A array, int from, int to) {
            int plen = Array.getLength(pattern);
            int pindex = 0;
            while (from < to) { // optimize: call _equals(parts)
                Object p = Array.get(pattern, pindex);
                Object v = Array.get(array, from);
                if (p == v)
                    continue;
                if (p == null || v == null)
                    return false;
                if (!p.equals(v))
                    return false;
                pindex = (pindex + 1) % plen;
            }
            return true;
        }
    }

    public static class Bytes extends _ArrayOp<byte[]> {
        public Bytes() {
            super(byte.class);
        }

        @Override
        public Object get(byte[] array, int index) {
            return array[index];
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
        public int indexOf(byte[] array, int from, int to, Object key, int start) {
            if (start < from)
                start = from;
            byte v = (Byte) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(byte[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            byte v = (Byte) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(byte[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                byte mem = array[t];
                array[f] = mem;
                array[t] = mem;
                f++;
                t--;
            }
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
        public Object get(short[] array, int index) {
            return array[index];
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
        public int indexOf(short[] array, int from, int to, Object key,
                int start) {
            if (start < from)
                start = from;
            short v = (Short) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(short[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            short v = (Short) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(short[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                short mem = array[t];
                array[f] = mem;
                array[t] = mem;
                f++;
                t--;
            }
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
        public Object get(int[] array, int index) {
            return array[index];
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
        public int indexOf(int[] array, int from, int to, Object key, int start) {
            if (start < from)
                start = from;
            int v = (Integer) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(int[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            int v = (Integer) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(int[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                int mem = array[t];
                array[f] = mem;
                array[t] = mem;
                f++;
                t--;
            }
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
        public Object get(long[] array, int index) {
            return array[index];
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
        public int indexOf(long[] array, int from, int to, Object key, int start) {
            if (start < from)
                start = from;
            long v = (Long) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(long[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            long v = (Long) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(long[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                long mem = array[t];
                array[f] = mem;
                array[t] = mem;
                f++;
                t--;
            }
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
        public Object get(float[] array, int index) {
            return array[index];
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
        public int indexOf(float[] array, int from, int to, Object key,
                int start) {
            if (start < from)
                start = from;
            float v = (Float) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(float[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            float v = (Float) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(float[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                float mem = array[t];
                array[f] = mem;
                array[t] = mem;
                f++;
                t--;
            }
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
        public Object get(double[] array, int index) {
            return array[index];
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
        public int indexOf(double[] array, int from, int to, Object key,
                int start) {
            if (start < from)
                start = from;
            double v = (Double) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(double[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            double v = (Double) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(double[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                double mem = array[t];
                array[f] = mem;
                array[t] = mem;
                f++;
                t--;
            }
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
        public Object get(boolean[] array, int index) {
            return array[index];
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
        public int indexOf(boolean[] array, int from, int to, Object key,
                int start) {
            if (start < from)
                start = from;
            boolean v = (Boolean) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(boolean[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            boolean v = (Boolean) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(boolean[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                boolean mem = array[t];
                array[f] = mem;
                array[t] = mem;
                f++;
                t--;
            }
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
        public Object get(char[] array, int index) {
            return array[index];
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
        public int indexOf(char[] array, int from, int to, Object key, int start) {
            if (start < from)
                start = from;
            char v = (Character) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(char[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            char v = (Character) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(char[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                char mem = array[t];
                array[t] = array[f];
                array[f] = mem;
                f++;
                t--;
            }
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

        @Override
        public Object get(T[] array, int index) {
            return array[index];
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

        @SuppressWarnings("unchecked")
        @Override
        public int indexOf(T[] array, int from, int to, Object key, int start) {
            if (start < from)
                start = from;
            T v = (T) key;
            while (start < to) {
                if (array[start] == v)
                    return start;
                start++;
            }
            return -1;
        }

        @SuppressWarnings("unchecked")
        @Override
        public int lastIndexOf(T[] array, int from, int to, Object key,
                int start) {
            if (start >= to)
                start = to - 1;
            T v = (T) key;
            while (start >= from) {
                if (array[start] == v)
                    return start;
                start--;
            }
            return -1;
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
        public void reverse(T[] array, int fromIndex, int toIndex) {
            int f = fromIndex;
            int t = toIndex - 1;
            while (f < t) {
                T mem = array[t];
                array[f] = mem;
                array[t] = mem;
                f++;
                t--;
            }
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
            throw new IllegalArgumentTypeException(arrayType, "array");
        Class<?> componentType = arrayType.getComponentType();
        if (componentType.isPrimitive())
            return (ArrayOp<A>) ops.get(componentType);
        else
            return (ArrayOp<A>) Objects;
    }

    @SuppressWarnings("unchecked")
    public static <A> ArrayOp<A> get(A array) {
        assert array != null;
        Class<A> arrayType = (Class<A>) array.getClass();
        return get(arrayType);
    }

}
