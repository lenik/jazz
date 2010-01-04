package net.bodz.bas.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;

import net.bodz.bas.collection.buffer.ByteRing;

public class ByteRingOutputStream extends OutputStream {

    private ByteRing ring;

    public ByteRingOutputStream(ByteRing ring) {
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

    public int capacity() {
        return ring.capacity();
    }

    public int free() {
        return ring.free();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (len > ring.free())
            throw new IOException(String.format("Buffer[%d] to write is too large, free=%d", len, ring.free()));
        ring.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        try {
            ring.writeByte((byte) b);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

}
