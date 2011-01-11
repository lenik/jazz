package net.bodz.bas.collection.buffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.charset.Charset;

import net.bodz.bas.collection.array.IArrayWrapper;
import net.bodz.bas.exceptions.OutOfDomainException;

/**
 * @see {@link IArrayWrapper}
 */
@Deprecated
public class ByteRing
        extends AbstractRingBuffer<byte[], Byte> {

    public ByteRing(byte[] array) {
        super(array);
    }

    public ByteRing(int capacity) {
        super(capacity);
    }

    @Override
    protected byte[] create(int capacity) {
        if (capacity < 0)
            throw new OutOfDomainException("capacity", capacity, 0);
        return new byte[capacity];
    }

    public String toString(int off, int len, Charset charset) {
        byte[] copy = copy(off, len);
        return new String(copy, charset);
    }

    public String toString(int off, int len, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return toString(off, len, charset);
    }

    public String toString(int off, int len) {
        return toString(off, len, Charset.defaultCharset());
    }

    @Override
    public String toString() {
        return toString(0, size());
    }

    /**
     * @see #_get(int)
     */
    public byte _getByte(int absoluteIndex) {
        return array[absoluteIndex];
    }

    /**
     * @see #_set(int, Object)
     */
    public void _setByte(int absoluteIndex, byte value) {
        array[absoluteIndex] = value;
    }

    /**
     * @see #get(int)
     */
    public byte getByte(int offsetToBegin) {
        return _getByte((begin + offsetToBegin) % capacity);
    }

    /**
     * @see #set(int, Object)
     */
    public void setByte(int offsetToBegin, byte value) {
        _setByte((begin + offsetToBegin) % capacity, value);
    }

    /**
     * @see #read()
     */
    public byte readByte()
            throws BufferUnderflowException {
        if (isEmpty())
            throw new BufferUnderflowException();
        byte x = _getByte(begin++);
        begin %= capacity;
        full = false;
        return x;
    }

    /**
     * @see #write(Object)
     */
    public void writeByte(byte value)
            throws BufferOverflowException {
        if (isFull())
            throw new BufferOverflowException();
        _setByte(end++, value);
        end %= capacity;
        if (end == begin)
            full = true;
    }

    public String readString(int len) {
        byte[] buf = new byte[len];
        len = read(buf, 0, len);
        return new String(buf, 0, len);
    }

    public String readString(int offsetToBegin, int len) {
        len = Math.min(size() - offsetToBegin, len);
        byte[] buf = new byte[len];
        read(offsetToBegin, buf, 0, len);
        return new String(buf, 0, len);
    }

}
