package net.bodz.bas.vfs.impl.filter;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class EncodedOutputStream
        extends FilterOutputStream {

    private final ISimpleCodec codec;
    private byte[] buffer;
    private int used;

    public EncodedOutputStream(OutputStream out, ISimpleCodec codec, int bufferSize) {
        super(out);
        if (codec == null)
            throw new NullPointerException("codec");
        this.codec = codec;
        this.buffer = new byte[bufferSize];
    }

    void push(byte b) {
        if (used == buffer.length)
            throw new BufferOverflowException();
        buffer[used++] = (byte) b;
    }

    int push(byte[] buf, int off, int len) {
        if (used + len > buffer.length)
            throw new BufferOverflowException();
        System.arraycopy(buf, off, buffer, used, len);
        used += len;
        return len;
    }

    int shift(int n) {
        System.arraycopy(buffer, n, buffer, 0, used - n);
        used -= n;
        return n;
    }

    @Override
    public synchronized void write(int b)
            throws IOException {
        if (used != 0) {
            write(new byte[] { (byte) b });
        } else {
            int encodedByte = codec.encode(b);
            if (encodedByte != ISimpleCodec.NaN) {
                super.write(encodedByte);
            } else {
                push((byte) b);
            }
        }
    }

    @Override
    public final void write(byte[] b)
            throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len)
            throws IOException {
        if (len < 0)
            throw new IllegalArgumentException("len is negative: " + len);

        int available = buffer.length - used;
        if (len != 0 && available == 0)
            throw new BufferOverflowException();

        while (len != 0 || used != 0) {
            int pushed = push(b, off, Math.min(available, len));
            off += pushed;
            len -= pushed;

            int encoded = codec.encode(buffer, 0, used);
            if (encoded == 0)
                throw new BufferUnderflowException();

            super.write(buffer, 0, encoded);
            available += shift(encoded);
        }
    }

}
