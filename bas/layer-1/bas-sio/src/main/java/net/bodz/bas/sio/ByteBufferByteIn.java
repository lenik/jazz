package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferByteIn
        extends AbstractByteIn {

    private final ByteBuffer bb;

    public ByteBufferByteIn(ByteBuffer byteBuffer) {
        if (byteBuffer == null)
            throw new NullPointerException("byteBuffer");
        this.bb = byteBuffer;
    }

    @Override
    public int read()
            throws IOException {
        if (bb.hasRemaining())
            return bb.get();
        return -1;
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        int remaining = bb.remaining();
        if (remaining == 0)
            return -1;
        int cbRead = Math.min(len, bb.remaining());
        bb.get(buf, off, cbRead);
        return cbRead;
    }

    @Override
    public int hashCode() {
        return 0x6307a5fe + bb.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ByteBufferByteIn))
            return false;
        ByteBufferByteIn o = (ByteBufferByteIn) obj;
        return bb.equals(o.bb);
    }

    @Override
    public String toString() {
        return bb.toString();
    }

}
