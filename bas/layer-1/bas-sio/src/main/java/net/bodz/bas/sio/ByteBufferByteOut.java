package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

public class ByteBufferByteOut
        extends ByteOut {

    private final ByteBuffer bb;

    public ByteBufferByteOut(ByteBuffer bb) {
        this.bb = bb;
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

}
