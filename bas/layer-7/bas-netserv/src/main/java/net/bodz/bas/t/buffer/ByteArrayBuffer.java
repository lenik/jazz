package net.bodz.bas.t.buffer;

import java.lang.reflect.Array;

import net.bodz.bas.meta.decl.NotNull;

public class ByteArrayBuffer
        implements IBuffer<Byte> {

    private byte[] buf;
    private int off;
    private int length;

    public ByteArrayBuffer(int initialCapacity) {
        buf = new byte[initialCapacity];
        off = 0;
        length = 0;
    }

    public ByteArrayBuffer(@NotNull byte[] buf) {
        this.buf = buf;
        this.off = 0;
        this.length = buf.length;
    }

    public ByteArrayBuffer(@NotNull byte[] buf, int off, int len) {
        if (off < 0 || off > buf.length)
            throw new IllegalArgumentException("bad offset: " + off);
        if (len < 0 || len > buf.length)
            throw new IllegalArgumentException("bad len: " + len);
        this.buf = buf;
        this.off = off;
        this.length = len;
    }

    @NotNull
    public byte[] getBackedArray() {
        return buf;
    }

    public int getBackedArrayOffset() {
        return off;
    }

    @NotNull
    @Override
    public Class<Byte> getComponentType() {
        return Byte.class;
    }

    @Override
    public void append(Byte element) {
        append(element.byteValue());
    }

    public void append(byte element) {
        ensureCapacity(length + 1);
        buf[off + length++] = element;
    }

    public void append(int element) {
        ensureCapacity(length + 1);
        buf[off + length++] = (byte) element;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public Byte get(int i) {
        return buf[off + i];
    }

    public byte getByte(int i) {
        return buf[off + i];
    }

    @Override
    public void set(int i, Byte value) {
        buf[off + i] = value;
    }

    public void setByte(int i, byte value) {
        buf[off + i] = value;
    }

    @Override
    public int capacity() {
        return buf.length;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public void ensureCapacity(int required) {
        if (buf.length >= required)
            return;

        // compute the capacity aligned to 2^n.
        int newCap = 1;
        while (newCap < required) {
            newCap <<= 1;
            if (newCap == 0x8000_0000)
                throw new IllegalArgumentException("Required too big capacity: " + required);
        }
        assert newCap >= required;

        byte[] newBuf = new byte[newCap];
        System.arraycopy(buf, off, newBuf, 0, length);
        buf = newBuf;
        off = 0;
    }

    @Override
    public void delete(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("" + index);
        if (index >= length)
            return;
        int i_after = index + 1;
        int n_move = length - i_after;
        System.arraycopy(buf, off + i_after, buf, off + index, n_move);
        length--;
    }

    @Override
    public void delete(int from, int len) {
        if (from < 0)
            throw new IndexOutOfBoundsException("" + from);
        if (len <= 0)
            return;
        int to = from + len;
        if (to > length)
            to = length;
        int n_move = length - to;
        System.arraycopy(buf, off + to, buf, off + from, n_move);
        length -= len;
    }

    @Override
    public void truncate(int maxLength) {
        if (maxLength < 0)
            throw new IllegalArgumentException("negative length: " + maxLength);
        if (maxLength < length)
            length = maxLength;
    }

    public byte[] toByteArray() {
        int n = length();
        byte[] array = new byte[n];
        System.arraycopy(buf, off, array, 0, n);
        return array;
    }

}
