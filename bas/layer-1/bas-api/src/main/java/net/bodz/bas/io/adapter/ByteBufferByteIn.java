package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.AbstractByteIn;

public class ByteBufferByteIn
        extends AbstractByteIn {

    private final ByteBuffer byteBuffer;

    public ByteBufferByteIn(ByteBuffer byteBuffer) {
        if (byteBuffer == null)
            throw new NullPointerException("byteBuffer");
        this.byteBuffer = byteBuffer;
    }

    @Override
    public long skip(long n)
            throws IOException {
        int min = (int) Math.min(n, byteBuffer.remaining());
        int position = byteBuffer.position();
        byteBuffer.position(position + min);
        return min;
    }

    @Override
    public int read()
            throws IOException {
        if (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            return b & 0xFF;
        }
        return -1;
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        int remaining = byteBuffer.remaining();
        if (remaining == 0)
            return -1;
        int cbRead = Math.min(len, byteBuffer.remaining());
        byteBuffer.get(buf, off, cbRead);
        return cbRead;
    }

    @Override
    public int hashCode() {
        return 0x6307a5fe + byteBuffer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ByteBufferByteIn))
            return false;
        ByteBufferByteIn o = (ByteBufferByteIn) obj;
        return byteBuffer.equals(o.byteBuffer);
    }

    @Override
    public String toString() {
        return byteBuffer.toString();
    }

}
