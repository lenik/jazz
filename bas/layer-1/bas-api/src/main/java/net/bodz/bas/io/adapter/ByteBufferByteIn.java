package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.AbstractByteIn;

public class ByteBufferByteIn
        extends AbstractByteIn {

    private final ByteBuffer buf;

    public ByteBufferByteIn(ByteBuffer buf) {
        if (buf == null)
            throw new NullPointerException("buf");
        this.buf = buf;
    }

    @Override
    public long skip(long n)
            throws IOException {
        int min = (int) Math.min(n, buf.remaining());
        int position = buf.position();
        buf.position(position + min);
        return min;
    }

    @Override
    public int read()
            throws IOException {
        if (buf.hasRemaining()) {
            byte b = buf.get();
            return b & 0xFF;
        }
        return -1;
    }

    @Override
    public int read(byte[] dst, int off, int len)
            throws IOException {
        int remaining = buf.remaining();
        if (remaining == 0)
            return -1;
        int cbRead = Math.min(len, buf.remaining());
        buf.get(dst, off, cbRead);
        return cbRead;
    }

    @Override
    public int hashCode() {
        return 0x6307a5fe + buf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ByteBufferByteIn))
            return false;
        ByteBufferByteIn o = (ByteBufferByteIn) obj;
        return buf.equals(o.buf);
    }

    @Override
    public String toString() {
        return buf.toString();
    }

}
