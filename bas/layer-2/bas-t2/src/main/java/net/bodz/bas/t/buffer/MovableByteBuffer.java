package net.bodz.bas.t.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import net.bodz.bas.c.primitive.IntMath;
import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;

public class MovableByteBuffer
        implements IMovableBuffer {

    private byte[] buf;
    private int start;
    private int end;

    public MovableByteBuffer() {
        this(0, 32);
    }

    public MovableByteBuffer(int initialSize) {
        this(initialSize, getRecommendCapacity(initialSize));
    }

    public MovableByteBuffer(int initialSize, int capacity) {
        if (initialSize < 0)
            throw new IllegalArgumentException("negative size: " + initialSize);
        if (capacity < initialSize)
            capacity = initialSize;
        buf = new byte[capacity];
        start = 0;
        end = initialSize;
    }

    public MovableByteBuffer(byte[] buf) {
        this(buf, 0, buf.length);
    }

    public MovableByteBuffer(byte[] buf, int off, int len) {
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

    public byte[] getArray() {
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
            byte[] realloc = new byte[capacity];
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

    public synchronized byte byteAt(int index) {
        if (index < 0 || index >= end - start)
            throw new IndexOutOfBoundsException(String.valueOf(index));
        return buf[start + index];
    }

    public synchronized void byteAt(int index, byte newByte) {
        if (index < 0 || index >= end - start)
            throw new IndexOutOfBoundsException(String.valueOf(index));
        buf[start + index] = newByte;
    }

    public synchronized void copyFrom(MovableByteBuffer src, int srcPos, int destPos, int length) {
        copyFrom(src.buf, src.start + srcPos, destPos, length);
    }

    public synchronized void copyFrom(byte[] src, int srcPos, int destPos, int length) {
        if (destPos < 0 || length < 0)
            throw new IllegalArgumentException("Negative index or length");
        if (destPos + length > end - start)
            throw new IndexOutOfBoundsException("index out of range.");
        System.arraycopy(src, srcPos, buf, start + destPos, length);
    }

    public synchronized void copyTo(int srcPos, MovableByteBuffer dest, int destPos, int length) {
        copyTo(srcPos, dest.buf, dest.start + destPos, length);
    }

    public synchronized void copyTo(int srcPos, byte[] dest, int destPos, int length) {
        if (srcPos < 0 || length < 0)
            throw new IllegalArgumentException("Negative index or length");
        if (srcPos + length > end - start)
            throw new IndexOutOfBoundsException("index out of range.");
        System.arraycopy(buf, start + srcPos, dest, destPos, length);
    }

    public synchronized byte[] copy() {
        return copy(end - start);
    }

    public synchronized byte[] copy(int newSize) {
        byte[] copy = new byte[newSize];
        int copySize = Math.min(newSize, end - start);
        copyTo(0, copy, 0, copySize);
        return copy;
    }

    @Override
    public synchronized String toString() {
        return new String(buf, start, end - start);
    }

    public synchronized String toString(String charset)
            throws UnsupportedEncodingException {
        return new String(buf, start, end - start, charset);
    }

    public synchronized void writeTo(OutputStream out)
            throws IOException {
        out.write(buf, start, end - start);
    }

    public synchronized void writeTo(IByteOut out)
            throws IOException {
        out.write(buf, start, end - start);
    }

    public synchronized int readFrom(InputStream in)
            throws IOException {
        return in.read(buf, start, end - start);
    }

    public synchronized int readFrom(IByteIn in)
            throws IOException {
        return in.read(buf, start, end - start);
    }

}
