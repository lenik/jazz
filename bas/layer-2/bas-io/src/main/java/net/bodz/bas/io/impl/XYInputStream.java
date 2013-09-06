package net.bodz.bas.io.impl;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.io.IXYTellable;

public class XYInputStream
        extends FilterInputStream
        implements IXYTellable {

    private long offset;
    private int y;
    private int x;
    private long markOffset;
    private int markY;
    private int markX;

    public XYInputStream(InputStream proxy) {
        super(proxy);
    }

    @Override
    public void mark(int readlimit) {
        super.mark(readlimit);
        markOffset = offset;
        markY = y;
        markX = x;
    }

    @Override
    public void reset()
            throws IOException {
        super.reset();
        offset = markOffset;
        y = markY;
        x = markX;
    }

    @Override
    public long tell() {
        return offset;
    }

    @Override
    public int tellY() {
        return y;
    }

    @Override
    public int tellX() {
        return x;
    }

    protected void accept(int b) {
        if (b == '\n') {
            y++;
            x = 0;
        } else {
            x++;
        }
    }

    @Override
    public int read()
            throws IOException {
        int b = super.read();
        if (b != -1) {
            offset++;
            accept(b);
        }
        return b;
    }

    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        int cb = super.read(b, off, len);
        if (cb != -1) {
            offset += cb;
            while (cb-- > 0)
                accept(b[off++]);
        }
        return cb;
    }

    @Override
    public int read(byte[] b)
            throws IOException {
        int cb = super.read(b);
        if (cb != -1) {
            offset += cb;
            for (int i = 0; i < cb; i++)
                accept(b[i]);
        }
        return cb;
    }

    @Override
    public long skip(long n)
            throws IOException {
        long cb = super.skip(n);
        if (cb != 0) {
            offset += cb;
            // ignore line info...
            throw new UnsupportedOperationException("position lost");
            // column += cc;
        }
        return cb;
    }

}
