package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

public class ByteBufferByteOut
        extends AbstractByteOut {

    private final ByteBuffer bb;

    public ByteBufferByteOut(ByteBuffer byteBuffer) {
        if (byteBuffer == null)
            throw new NullPointerException("byteBuffer");
        this.bb = byteBuffer;
    }

    @Override
    public void write(int b) {
        bb.put((byte) b);
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
        try {
            bb.put(buf, off, len);
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
            bb.put(buffer);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public int hashCode() {
        return 0xb499c40d + bb.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ByteBufferByteOut))
            return false;
        ByteBufferByteOut o = (ByteBufferByteOut) obj;
        return bb.equals(o.bb);
    }

    @Override
    public String toString() {
        return bb.toString();
    }

}
