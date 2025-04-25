package net.bodz.bas.t.buffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import net.bodz.bas.io.ILookBytesAhead;
import net.bodz.bas.meta.decl.NotNull;

public class ByteArrayBuffer
        implements IBuffer<Byte>,
                   IByteBuffer,
                   IBufferSelection<Byte>,
                   ILookBytesAhead {

    private byte[] buf;
    private int start;
    private int end;
    private int length;

    private int position;
    // private int limit; == length
    private int positionMarked = -1;

    public ByteArrayBuffer(int initialCapacity) {
        buf = new byte[initialCapacity];
        start = 0;
        end = buf.length;
        length = 0;
    }

    public ByteArrayBuffer(@NotNull byte[] buf) {
        this.buf = buf;
        this.start = 0;
        this.end = buf.length;
        this.length = buf.length;
    }

    public ByteArrayBuffer(@NotNull byte[] buf, int off, int len) {
        if (off < 0 || off > buf.length)
            throw new IllegalArgumentException("bad offset: " + off);
        if (len < 0 || len > buf.length)
            throw new IllegalArgumentException("bad len: " + len);
        this.buf = buf;
        this.start = off;
        this.end = off + len;
        this.length = len;
    }

    @NotNull
    @Override
    public byte[] getBackedArray() {
        return buf;
    }

    @Override
    public int getBackedArrayOffset() {
        return start;
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

    @Override
    public void clear() {
        length = 0;
        position = 0;
        positionMarked = -1;
    }

    private void ensureIndexValid(int index) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("" + index);
    }

    @Override
    public Byte get(int i) {
        ensureIndexValid(i);
        return buf[start + i];
    }

    @Override
    public void set(int i, Byte value) {
        ensureIndexValid(i);
        buf[start + i] = value;
    }

    @Override
    public int capacity() {
        return end - start;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public void ensureCapacity(int required) {
        int capacity = end - start;
        if (capacity >= required)
            return;

        int newCap = BinMath.minPowerOf2GreaterThanOrEquals(required);

        byte[] newBuf = new byte[newCap];
        System.arraycopy(buf, start, newBuf, 0, length);
        buf = newBuf;
        start = 0;
        end = newCap;
    }

    @Override
    public void delete(int index) {
        ensureIndexValid(index);
        int i_after = index + 1;
        int n_move = length - i_after;
        System.arraycopy(buf, start + i_after, buf, start + index, n_move);
        length--;
    }

    @Override
    public void delete(int from, int len) {
        if (len <= 0)
            return;
        ensureIndexValid(from);

        int to = from + len;
        if (to > length)
            to = length;

        int n_move = length - to;
        System.arraycopy(buf, start + to, buf, start + from, n_move);
        length -= len;
    }

    @Override
    public void truncate(int maxLength) {
        if (maxLength < 0)
            throw new IllegalArgumentException("negative length: " + maxLength);
        if (maxLength < length)
            length = maxLength;
    }

    // IByteBuffer

    public void append(byte element) {
        ensureCapacity(length + 1);
        buf[start + length] = element;
        length++;
    }

    public void append(byte[] buf, int off, int len) {
        ensureCapacity(length + len);
        System.arraycopy(buf, off, this.buf, start + length, len);
        length += len;
    }

    public void append(ByteBuffer buf) {
        int len = buf.remaining();
        ensureCapacity(length + len);
        buf.get(this.buf, start + length, len);
        length += len;
    }

    @Override
    public void append(IByteBuffer buf, int off, int len) {
        if (len == 0)
            return;
        ensureIndexValid(off);
        ensureIndexValid(off + len - 1);

        byte[] backedArray = buf.getBackedArray();
        int backedArrayOffset = buf.getBackedArrayOffset();
        append(backedArray, backedArrayOffset + off, len);
    }

    public void append(ByteArrayBuffer buf, int off, int len) {
        if (len == 0)
            return;
        ensureIndexValid(off);
        ensureIndexValid(off + len - 1);
        append(buf.buf, buf.start + off, len);
    }

    @Override
    public int get(int pos, byte[] buf, int off, int len) {
        if (len <= 0)
            return 0;
        ensureIndexValid(pos);
        int remain = length - pos;
        int min = Math.min(remain, len);
        System.arraycopy(this.buf, start + pos, buf, off, min);
        return min;
    }

    @Override
    public int get(int pos, ByteBuffer buf, int len) {
        if (len <= 0)
            return 0;
        ensureIndexValid(pos);
        int remain = length - pos;
        int min = Math.min(remain, len);
        buf.put(this.buf, start + pos, min);
        return min;
    }

    @Override
    public int set(int pos, byte[] buf, int off, int len) {
        int remain = length - pos;
        int min = Math.min(remain, len);
        System.arraycopy(buf, off, this.buf, start + pos, min);
        return min;
    }

    @Override
    public int set(int pos, ByteBuffer buf, int len) {
        if (len <= 0)
            return 0;
        ensureIndexValid(pos);
        int remain = length - pos;
        int min = Math.min(remain, len);
        buf.get(this.buf, start + pos, min);
        return min;
    }

    @Override
    public byte getByte(int i) {
        ensureIndexValid(i);
        return buf[start + i];
    }

    @Override
    public void setByte(int i, byte value) {
        ensureIndexValid(i);
        buf[start + i] = value;
    }

    @Override
    public byte[] toByteArray() {
        byte[] array = new byte[length];
        System.arraycopy(buf, start, array, 0, length);
        return array;
    }

    // IBufferSelection

    @Override
    public int limit() {
        return length;
    }

    @Override
    public void limit(int limit) {
        int capacity = end - start;
        if (limit < 0 || limit > capacity)
            throw new IllegalStateException("invalid limit: " + limit);
        this.length = limit;
    }

    @Override
    public int position() {
        return positionMarked;
    }

    @Override
    public void position(int position) {
        if (position < 0 || position > length)
            throw new IllegalArgumentException("invalid position: " + position);
        this.position = position;
    }

    @Override
    public int advance(int n) {
        int newPosition = position + n;
        if (newPosition < 0 || newPosition > length)
            throw new IllegalArgumentException("invalid after advance: " + n);
        int old = position;
        position = newPosition;
        return old;
    }

    @Override
    public int remaining() {
        return length - position;
    }

    @Override
    public void compact() {
        int remaining = remaining();
        System.arraycopy(buf, start + position, buf, start, remaining);
        position = 0;
        length = remaining;
    }

    @Override
    public void mark() {
        positionMarked = position;
    }

    @Override
    public void reset() {
        if (positionMarked > length)
            throw new IllegalStateException("marked position is invalid: " + positionMarked);
        position = positionMarked;
    }

    // ILookAhead

    @Override
    public int getLookCapacity() {
        return capacity();
    }

    @Override
    public int getLookLimit() {
        return length();
    }

    @Override
    public int lookByte()
            throws IOException {
        if (position < 0)
            throw new IllegalStateException();
        if (position >= length)
            return -1;
        return buf[start + position] & 0xFF;
    }

    @Override
    public int look(byte[] buf, int off, int len)
            throws IOException {
        if (position < 0)
            throw new IllegalStateException();
        int n = Math.min(length - position, len);
        System.arraycopy(this.buf, start + position, buf, off, n);
        return n;
    }

    public String toString(Charset charset) {
        return new String(buf, start, length, charset);
    }

}
