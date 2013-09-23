package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.AbstractByteOut;

public class ByteBufferByteOut
        extends AbstractByteOut {

    private final ByteBuffer buf;

    public ByteBufferByteOut(ByteBuffer buf) {
        if (buf == null)
            throw new NullPointerException("buf");
        this.buf = buf;
    }

    @Override
    public void write(int b) {
        buf.put((byte) b);
    }

    @Override
    public void write(byte[] src, int off, int len)
            throws IOException {
        try {
            buf.put(src, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(ByteBuffer src)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        try {
            buf.put(src);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public int hashCode() {
        return 0xb499c40d + buf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ByteBufferByteOut))
            return false;
        ByteBufferByteOut o = (ByteBufferByteOut) obj;
        return buf.equals(o.buf);
    }

    @Override
    public String toString() {
        return buf.toString();
    }

}
