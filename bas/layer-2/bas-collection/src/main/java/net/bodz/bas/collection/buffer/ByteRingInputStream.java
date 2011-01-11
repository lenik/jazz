package net.bodz.bas.collection.buffer;

import java.io.IOException;
import java.io.InputStream;

public class ByteRingInputStream extends InputStream {

    private ByteRing ring;

    public ByteRingInputStream(ByteRing ring) {
        if (ring == null)
            throw new NullPointerException("ring");
        this.ring = ring;
    }

    @Override
    public void close() throws IOException {
        ring = null;
    }

    void checkClosed() throws IOException {
        if (ring == null)
            throw new IOException("closed");
    }

    @Override
    public int available() throws IOException {
        checkClosed();
        return ring.size();
    }

    @Override
    public int read() throws IOException {
        checkClosed();
        if (ring.isEmpty())
            return -1;
        return ring.readByte() & 0xff;
    }

    @Override
    public long skip(long n) throws IOException {
        checkClosed();
        int min = (int) Math.min(ring.size(), n);
        ring.delete(0, min);
        return min;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        checkClosed();
        if (ring == null)
            throw new IOException("closed");
        int read = ring.read(b, off, len);
        return read;
    }

}
