package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ITellable;

public class TellableByteIn
        implements IByteIn, ITellable {

    private final IByteIn in;
    private long pos;

    public TellableByteIn(IByteIn in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
    }

    @Override
    public void close()
            throws IOException {
        in.close();
    }

    @Override
    public boolean isClosed() {
        return in.isClosed();
    }

    @Override
    public int read()
            throws IOException {
        int b = in.read();
        if (b != -1)
            pos++;
        return b;
    }

    @Override
    public long skip(long n)
            throws IOException {
        long actual = in.skip(n);
        pos += actual;
        return actual;
    }

    @Override
    public int read(byte[] buf)
            throws IOException {
        int cb = in.read(buf);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        int cb = in.read(buf, off, len);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public int read(ByteBuffer buf)
            throws IOException {
        int cb = in.read(buf);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public long tell() {
        return pos;
    }

}
