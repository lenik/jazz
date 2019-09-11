package net.bodz.bas.t.buffer;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

import net.bodz.bas.c.primitive.IntMath;
import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;

public class MovableCharBuffer
        implements IMovableBuffer {

    private char[] buf;
    private int start;
    private int end;

    public MovableCharBuffer() {
        this(0, 32);
    }

    public MovableCharBuffer(int initialSize) {
        this(initialSize, getRecommendCapacity(initialSize));
    }

    public MovableCharBuffer(int initialSize, int capacity) {
        if (initialSize < 0)
            throw new IllegalArgumentException("negative size: " + initialSize);
        if (capacity < initialSize)
            capacity = initialSize;
        buf = new char[capacity];
        start = 0;
        end = initialSize;
    }

    public MovableCharBuffer(char[] buf) {
        this(buf, 0, buf.length);
    }

    public MovableCharBuffer(char[] buf, int off, int len) {
        if (buf == null)
            throw new NullPointerException("buf");
        if (off < 0 || off > buf.length)
            throw new OutOfDomainException("off", off, buf.length);
        if (len < 0 || off + len > buf.length)
            throw new OutOfDomainException("len", len, buf.length - off);
        this.buf = buf;
        this.start = off;
        this.end = off + len;
    }

    static int getRecommendCapacity(int size) {
        if (size <= 0x40000000)
            return IntMath.min2eGreaterOrEqualsTo(size);
        else if (size <= SIZE_MAX)
            return SIZE_MAX;
        else
            return -1;
    }

    public char[] getArray() {
        return buf;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int size() {
        return end - start;
    }

    @Override
    public int capacity() {
        return buf.length;
    }

    @Override
    public synchronized void trimToSize() {
        if (start != 0 || end != buf.length)
            buf = Arrays.copyOfRange(buf, start, end);
    }

    private synchronized void ensureCapacity(int capacity) {
        capacity = getRecommendCapacity(capacity);
        if (capacity < 0)
            throw new OutOfMemoryError();

        if (capacity > buf.length) {
            char[] realloc = new char[capacity];
            System.arraycopy(buf, start, realloc, 0, end - start);
            buf = realloc;
            end -= start;
            start = 0;
        } else if (capacity > buf.length - start) {
            System.arraycopy(buf, start, buf, 0, end - start);
            end -= start;
            start = 0;
        }
    }

    @Override
    public synchronized void ensureSize(int size) {
        if (size > end - start)
            resize(size);
    }

    @Override
    public synchronized void resize(int size) {
        if (size < 0)
            throw new IllegalArgumentException("Size is negative: " + size);

        ensureCapacity(size);
        end = start + size;
    }

    public synchronized char charAt(int index) {
        if (index < 0 || index >= end - start)
            throw new IndexOutOfBoundsException(String.valueOf(index));
        return buf[start + index];
    }

    public synchronized void charAt(int index, char newByte) {
        if (index < 0 || index >= end - start)
            throw new IndexOutOfBoundsException(String.valueOf(index));
        buf[start + index] = newByte;
    }

    public synchronized void copyFrom(MovableCharBuffer src, int srcPos, int destPos, int length) {
        copyFrom(src.buf, src.start + srcPos, destPos, length);
    }

    public synchronized void copyFrom(char[] src, int srcPos, int destPos, int length) {
        if (destPos < 0 || length < 0)
            throw new IllegalArgumentException("Negative index or length");
        if (destPos + length > end - start)
            throw new IndexOutOfBoundsException("index out of range.");
        System.arraycopy(src, srcPos, buf, start + destPos, length);
    }

    public synchronized void copyTo(int srcPos, MovableCharBuffer dest, int destPos, int length) {
        copyTo(srcPos, dest.buf, dest.start + destPos, length);
    }

    public synchronized void copyTo(int srcPos, char[] dest, int destPos, int length) {
        if (srcPos < 0 || length < 0)
            throw new IllegalArgumentException("Negative index or length");
        if (srcPos + length > end - start)
            throw new IndexOutOfBoundsException("index out of range.");
        System.arraycopy(buf, start + srcPos, dest, destPos, length);
    }

    public synchronized char[] copy() {
        return copy(end - start);
    }

    public synchronized char[] copy(int newSize) {
        char[] copy = new char[newSize];
        int copySize = Math.min(newSize, end - start);
        copyTo(0, copy, 0, copySize);
        return copy;
    }

    @Override
    public synchronized String toString() {
        return new String(buf, start, end - start);
    }

    public synchronized void writeTo(Writer out)
            throws IOException {
        out.write(buf, start, end - start);
    }

    public synchronized void writeTo(ICharOut out)
            throws IOException {
        out.write(buf, start, end - start);
    }

    public synchronized int readFrom(Reader in)
            throws IOException {
        return in.read(buf, start, end - start);
    }

    public synchronized int readFrom(ICharIn in)
            throws IOException {
        return in.read(buf, start, end - start);
    }

}
