package net.bodz.bas.t.buffer;

import java.nio.CharBuffer;

import net.bodz.bas.meta.decl.NotNull;

public class CharArrayBuffer
        implements IBuffer<Character> {

    private char[] buf;
    private int start;
    private int end;
    private int length;

    public CharArrayBuffer(int initialCapacity) {
        buf = new char[initialCapacity];
        start = 0;
        end = buf.length;
        length = 0;
    }

    public CharArrayBuffer(@NotNull char[] buf) {
        this.buf = buf;
        this.start = 0;
        this.end = buf.length;
        this.length = buf.length;
    }

    public CharArrayBuffer(@NotNull char[] buf, int off, int len) {
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
    public char[] getBackedArray() {
        return buf;
    }

    public int getBackedArrayOffset() {
        return start;
    }

    @NotNull
    @Override
    public Class<Character> getComponentType() {
        return Character.class;
    }

    @Override
    public void append(Character element) {
        append(element.charValue());
    }

    public void append(char element) {
        ensureCapacity(length + 1);
        buf[start + length] = element;
        length++;
    }

    public void append(int element) {
        ensureCapacity(length + 1);
        buf[start + length] = (char) element;
        length++;
    }

    public void append(char[] buf) {
        append(buf, 0, buf.length);
    }

    public void append(char[] buf, int off, int len) {
        ensureCapacity(length + len);
        System.arraycopy(buf, off, this.buf, start + length, len);
        length += len;
    }

    public void append(CharBuffer buf) {
        int len = buf.remaining();
        ensureCapacity(length + len);
        buf.get(this.buf, start + length, len);
        length += len;
    }

    public void append(CharArrayBuffer buf) {
        append(buf, 0, buf.length);
    }

    public void append(CharArrayBuffer buf, int off, int len) {
        if (len == 0)
            return;
        ensureIndexValid(off);
        ensureIndexValid(off + len - 1);
        append(buf.buf, buf.start + off, len);
    }

    @Override
    public void clear() {
        length = 0;
    }

    private void ensureIndexValid(int index) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("" + index);
    }

    @Override
    public Character get(int i) {
        ensureIndexValid(i);
        return buf[start + i];
    }

    @Override
    public void set(int i, Character value) {
        ensureIndexValid(i);
        buf[start + i] = value;
    }

    public int get(int offset, char[] buf) {
        return get(offset, buf, 0, buf.length);
    }

    public int get(int offset, char[] buf, int off, int len) {
        int remain = length - offset;
        int min = Math.min(len, remain);
        System.arraycopy(this.buf, start + offset, buf, off, min);
        return min;
    }

    public int set(int offset, char[] buf) {
        return set(offset, buf, 0, buf.length);
    }

    public int set(int offset, char[] buf, int off, int len) {
        int remain = length - offset;
        int min = Math.min(len, remain);
        System.arraycopy(buf, off, this.buf, start + offset, min);
        return min;
    }

    public char getChar(int i) {
        ensureIndexValid(i);
        return buf[start + i];
    }

    public void setChar(int i, char value) {
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

    /**
     * compute the capacity aligned to 2^n.
     */
    int minPowerOf2GreaterThanOrEquals(int required) {
        int min = 1;
        while (min < required) {
            min <<= 1;
            if (min == 0x8000_0000)
                throw new IllegalArgumentException("Required too big capacity: " + required);
        }
        assert min >= required;
        return min;
    }

    @Override
    public void ensureCapacity(int required) {
        int capacity = end - start;
        if (capacity >= required)
            return;

        int newCap = minPowerOf2GreaterThanOrEquals(required);

        char[] newBuf = new char[newCap];
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

    public char[] toCharArray() {
        char[] array = new char[length];
        System.arraycopy(buf, start, array, 0, length);
        return array;
    }

    @Override
    public String toString() {
        return new String(buf, start, length);
    }

}
