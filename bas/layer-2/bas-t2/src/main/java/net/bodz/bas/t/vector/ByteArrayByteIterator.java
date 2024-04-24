package net.bodz.bas.t.vector;

import java.util.NoSuchElementException;

public class ByteArrayByteIterator
        implements
            ByteIterator {

    private final byte[] bytes;
    private final int end;
    private int pos;

    public ByteArrayByteIterator(byte[] bytes, int start, int end) {
        if (bytes == null)
            throw new NullPointerException("bytes");
        this.bytes = bytes;
        this.end = end;
        this.pos = start;
    }

    @Override
    public boolean hasNext() {
        return pos < end;
    }

    @Override
    public Byte next() {
        return nextByte();
    }

    @Override
    public byte nextByte() {
        if (pos >= end)
            throw new NoSuchElementException();
        byte b = bytes[pos++];
        return b;
    }

}
