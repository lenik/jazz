package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.AbstractByteOut;

public class ByteBufferByteOut
        extends AbstractByteOut {

    private final ByteBuffer byteBuffer;

    public ByteBufferByteOut(ByteBuffer byteBuffer) {
        if (byteBuffer == null)
            throw new NullPointerException("byteBuffer");
        this.byteBuffer = byteBuffer;
    }

    @Override
    public void write(int b) {
        byteBuffer.put((byte) b);
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
        try {
            byteBuffer.put(buf, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(ByteBuffer buffer)
            throws IOException {
        if (buffer == null)
            throw new NullPointerException("buffer");
        try {
            byteBuffer.put(buffer);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public int hashCode() {
        return 0xb499c40d + byteBuffer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ByteBufferByteOut))
            return false;
        ByteBufferByteOut o = (ByteBufferByteOut) obj;
        return byteBuffer.equals(o.byteBuffer);
    }

    @Override
    public String toString() {
        return byteBuffer.toString();
    }

}
