package net.bodz.bas.types.buf;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Comparator;

import net.bodz.bas.lang.IntMath;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.types.util.ArrayOp;
import net.bodz.bas.types.util.ArrayOps;

public class Buffer<A> {

    protected A   array;
    protected int start;
    protected int end;
    private int   alignment;

    private A     tmpValue;

    private static void _checkpow2(int n) {
        if (IntMath.ones(n) != 1)
            throw new OutOfDomainException("n=pow2(x)", n); //$NON-NLS-1$
    }

    private final ArrayOp<A> op;

    public Buffer(Class<?> arrayType) {
        this(arrayType, 16, 16);
    }

    public Buffer(Class<?> arrayType, int initialCapacity) {
        this(arrayType, initialCapacity, 16);
    }

    @SuppressWarnings("unchecked")
    public Buffer(Class<?> arrayType, int initialCapacity, int alignment) {
        this.op = (ArrayOp<A>) ArrayOps.get(arrayType);
        _checkpow2(alignment);
        this.alignment = alignment;
        realloc(initialCapacity);
        tmpValue = op.allocate(1);
    }

    public int capacity() {
        return Array.getLength(array);
    }

    public int available() {
        return Math.max(start, Array.getLength(array) - end);
    }

    protected int preferredCapacity(int size) {
        int mask = alignment - 1;
        int cap = (size + mask) & ~mask;
        assert cap >= size;
        return cap;
    }

    public int size() {
        return end - start;
    }

    public void resize(int newsize) {
        if (newsize < 0)
            throw new OutOfDomainException("newsize", newsize, 0); //$NON-NLS-1$
        int size = size();
        if (newsize <= size)
            end = start + size;
        else {
            int capacity = capacity();
            int capleft = start;
            int capright = capacity - end;
            int delta = newsize - size;
            if (delta <= capleft) {
                // extend at left
                start -= delta;
            } else if (delta <= capright) {
                // extend at right
                end += delta;
            } else {
                // realloc
                realloc(newsize);
            }
        }
    }

    protected void realloc(int newsize) {
        int size = size();
        realloc(newsize, 0, size);
    }

    protected void realloc(int newsize, int newstart, int newend) {
        int pcap = preferredCapacity(newsize);
        A newarray = op.allocate(pcap);
        if (array != null) {
            int size = size();
            assert newstart >= 0 && newstart <= newsize - size;
            op.copy(array, start, newarray, newstart, size);
        }
        start = newstart;
        end = newend;
        array = newarray;
    }

    public boolean isCompacted() {
        return start == 0 && end == Array.getLength(array);
    }

    public void compact() {
        if (isCompacted())
            return;
        int size = size();
        array = op.copyOfRange(array, start, end);
        start = 0;
        end = size;
    }

    protected void _check(int off) {
        if (off < 0)
            throw new OutOfDomainException("off", off, 0); //$NON-NLS-1$
        int size = size();
        if (off > size)
            throw new OutOfDomainException("off", off, 0); //$NON-NLS-1$
    }

    protected void _check(int off, int len) {
        if (off < 0)
            throw new OutOfDomainException("from", off, 0); //$NON-NLS-1$
        if (len < 0)
            throw new OutOfDomainException("len", len, 0); //$NON-NLS-1$
        int size = size();
        int _end = off + len;
        if (_end > size)
            throw new OutOfDomainException("to", _end, size); //$NON-NLS-1$
    }

    public void clear() {
        delete(0, size());
    }

    public void delete(int off, int len) {
        if (len == 0)
            return;
        _check(off, len);
        int _end = off + len;
        int size = size();
        if (off == 0)
            start += len;
        else if (_end == size)
            end -= len;
        else {
            int left = off;
            int right = size - _end;
            if (left > right) {
                // move right block
                int delstart = start + off;
                int delend = delstart + len;
                op.copy(array, delend, array, delstart, right);
                end -= len;
            } else {
                // move left block
                op.copy(array, start, array, start + len, left);
                start += len;
            }
        }
        if (start == end)
            start = end = 0;
    }

    public void prependValue(Object value) {
        op.set(tmpValue, 0, value);
        prepend(tmpValue);
    }

    public void prepend(A buf) {
        prepend(buf, 0, Array.getLength(buf));
    }

    public void prepend(A buf, int off, int len) {
        assert buf != null;
        insert(0, buf, off, len);
    }

    public void appendValue(Object value) {
        op.set(tmpValue, 0, value);
        append(tmpValue);
    }

    public void append(A buf) {
        append(buf, 0, Array.getLength(buf));
    }

    public void append(A buf, int off, int len) {
        assert buf != null;
        int size = size();
        insert(size, buf, off, len);
    }

    public void insertValue(int off, Object value) {
        op.set(tmpValue, 0, value);
        insert(off, tmpValue);
    }

    public void insert(int off, A buf) {
        insert(off, buf, 0, Array.getLength(buf));
    }

    public void insert(int off, A buf, int bufoff, int len) {
        assert len >= 0;
        if (len == 0)
            return;
        _check(off);
        assert buf != null;
        assert bufoff >= 0;
        assert (bufoff + len) <= Array.getLength(buf);
        int size = size();
        int capacity = capacity();
        int left = off;
        int right = size - off;
        if (len > start)
            left = size; // need realloc
        if (len > capacity - end)
            right = size; // need realloc
        if (left > right) {
            // move right block
            int insstart = start + off;
            int insend = insstart + len;
            op.copy(array, insstart, array, insend, right);
            end += len;
            op.copy(buf, bufoff, array, insstart, len);
        } else if (left == size) {
            // optimize:
            left = off;
            right = size - off;

            realloc(size + len);
            op.copy(array, 0 + off, array, 0 + off + len, right);
            op.copy(buf, bufoff, array, 0 + off, len);
            end += len;
        } else {
            assert left <= right;
            // move left block
            op.copy(array, start, array, start - len, left);
            start -= len;
            op.copy(buf, bufoff, array, start + off, len);
        }
    }

    public A copyOf(int newLength) {
        if (newLength < 0)
            throw new OutOfDomainException("newLength", newLength, 0); //$NON-NLS-1$
        A copy = op.allocate(newLength);
        int copylen = size();
        if (copylen > newLength)
            copylen = newLength;
        op.copy(array, start, copy, 0, copylen);
        return copy;
    }

    public A copyOf() {
        return op.copyOfRange(array, start, end);
    }

    public A copyOfRange(int from, int to) {
        _check(from, to - from);
        return op.copyOfRange(array, start + from, start + to);
    }

    public void copyFrom(A src, int srcoff, int off, int len) {
        _check(off, len);
        assert src != null;
        assert srcoff >= 0 && srcoff + len < Array.getLength(src);
        op.copy(src, srcoff, array, start + off, len);
    }

    public void copyTo(int off, A dst, int dstoff, int len) {
        _check(off, len);
        assert dst != null;
        assert dstoff >= 0 && dstoff + len < Array.getLength(dst);
        op.copy(array, start + off, dst, dstoff, len);
    }

    public void binarySearch(Object key) {
        op.binarySearch(array, start, end, key);
    }

    public void binarySearch(int from, int to, Object key) {
        _check(from, to - from);
        op.binarySearch(array, start + from, start + to, key);
    }

    public void sort() {
        op.sort(array, start, end);
    }

    public void sort(Comparator<?> comparator) {
        op.sort(array, start, end, comparator);
    }

    public void sort(int from, int to) {
        _check(from, to - from);
        op.sort(array, start + from, start + to);
    }

    public void sort(int from, int to, Comparator<?> comparator) {
        _check(from, to - from);
        op.sort(array, start + from, start + to, comparator);
    }

    public void fill(Object val) {
        op.fill(array, start, end, val);
    }

    public void fill(int from, int to, Object val) {
        _check(from, to - from);
        op.fill(array, start + from, start + to, val);
    }

    @Override
    public String toString() {
        if (isCompacted())
            return op.toString(array);
        return op.toString(copyOf());
    }

    public static class ByteBuffer extends Buffer<byte[]> {

        public ByteBuffer() {
            super(byte[].class);
        }

        public ByteBuffer(int initialCapacity) {
            super(byte[].class, initialCapacity);
        }

        public ByteBuffer(int initialCapacity, int alignment) {
            super(byte[].class, initialCapacity, alignment);
        }

        @Override
        public String toString() {
            return new String(array, start, end - start);
        }

        public String toString(String charsetName) throws UnsupportedEncodingException {
            return new String(array, start, end - start, charsetName);
        }

        public String toString(int from, int to, String charsetName)
                throws UnsupportedEncodingException {
            _check(from, to);
            return new String(array, start + from, start + to, charsetName);
        }
    }

    public static class ShortBuffer extends Buffer<short[]> {

        public ShortBuffer() {
            super(short[].class);
        }

        public ShortBuffer(int initialCapacity) {
            super(short[].class, initialCapacity);
        }

        public ShortBuffer(int initialCapacity, int alignment) {
            super(short[].class, initialCapacity, alignment);
        }

    }

    public static class IntBuffer extends Buffer<int[]> {

        public IntBuffer() {
            super(int[].class);
        }

        public IntBuffer(int initialCapacity) {
            super(int[].class, initialCapacity);
        }

        public IntBuffer(int initialCapacity, int alignment) {
            super(int[].class, initialCapacity, alignment);
        }

    }

    public static class LongBuffer extends Buffer<long[]> {

        public LongBuffer() {
            super(long[].class);
        }

        public LongBuffer(int initialCapacity) {
            super(long[].class, initialCapacity);
        }

        public LongBuffer(int initialCapacity, int alignment) {
            super(long[].class, initialCapacity, alignment);
        }

    }

    public static class FloatBuffer extends Buffer<float[]> {

        public FloatBuffer() {
            super(float[].class);
        }

        public FloatBuffer(int initialCapacity) {
            super(float[].class, initialCapacity);
        }

        public FloatBuffer(int initialCapacity, int alignment) {
            super(float[].class, initialCapacity, alignment);
        }

    }

    public static class DoubleBuffer extends Buffer<double[]> {

        public DoubleBuffer() {
            super(double[].class);
        }

        public DoubleBuffer(int initialCapacity) {
            super(double[].class, initialCapacity);
        }

        public DoubleBuffer(int initialCapacity, int alignment) {
            super(double[].class, initialCapacity, alignment);
        }

    }

    public static class BooleanBuffer extends Buffer<boolean[]> {

        public BooleanBuffer() {
            super(boolean[].class);
        }

        public BooleanBuffer(int initialCapacity) {
            super(boolean[].class, initialCapacity);
        }

        public BooleanBuffer(int initialCapacity, int alignment) {
            super(boolean[].class, initialCapacity, alignment);
        }

    }

    public static class CharBuffer extends Buffer<char[]> {

        public CharBuffer() {
            super(char[].class);
        }

        public CharBuffer(int initialCapacity) {
            super(char[].class, initialCapacity);
        }

        public CharBuffer(int initialCapacity, int alignment) {
            super(char[].class, initialCapacity, alignment);
        }

        public void prepend(String s) {
            super.prepend(s.toCharArray());
        }

        public void append(String s) {
            super.append(s.toCharArray());
        }

        public void insert(int off, String s) {
            super.insert(off, s.toCharArray());
        }

        @Override
        public String toString() {
            return new String(array, start, end - start);
        }

        public String toString(int from, int to) {
            _check(from, to - from);
            return new String(array, start + from, start + to);
        }
    }

    public static class ObjectBuffer extends Buffer<Object[]> {

        public ObjectBuffer() {
            super(Object[].class);
        }

        public ObjectBuffer(int initialCapacity) {
            super(Object[].class, initialCapacity);
        }

        public ObjectBuffer(int initialCapacity, int alignment) {
            super(Object[].class, initialCapacity, alignment);
        }

    }

}
