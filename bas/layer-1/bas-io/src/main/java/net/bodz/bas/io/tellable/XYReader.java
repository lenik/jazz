package net.bodz.bas.io.tellable;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import net.bodz.bas.sio.position.IXYTellable;

public class XYReader
        extends FilterReader
        implements IXYTellable {

    private long offset;
    private int y;
    private int x;

    private long markOffset;
    private int markY;
    private int markX;

    public XYReader(Reader proxy) {
        super(proxy);
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

    @Override
    public void mark(int readAheadLimit)
            throws IOException {
        super.mark(readAheadLimit);
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

    protected void accept(int c) {
        if (c == '\n') {
            y++;
            x = 0;
        } else {
            x++;
        }
    }

    @Override
    public int read()
            throws IOException {
        int c = super.read();
        if (c != -1) {
            offset++;
            accept(c);
        }
        return c;
    }

    @Override
    public int read(char[] cbuf, int off, int len)
            throws IOException {
        int cc = super.read(cbuf, off, len);
        if (cc != -1) {
            offset += cc;
            while (cc-- > 0)
                accept(cbuf[off++]);
        }
        return cc;
    }

    @Override
    public int read(char[] cbuf)
            throws IOException {
        int cc = super.read(cbuf);
        if (cc != -1) {
            offset += cc;
            for (int i = 0; i < cc; i++)
                accept(cbuf[i]);
        }
        return cc;
    }

    @Override
    public int read(CharBuffer target)
            throws IOException {
        int cc = super.read(target);
        if (cc != -1) {
            offset += cc;
            for (int i = 0; i < cc; i++)
                accept(target.get(i));
        }
        return cc;
    }

    @Override
    public long skip(long n)
            throws IOException {
        long cc = super.skip(n);
        if (cc != 0) {
            offset += cc;
            // ignore line info...
            throw new UnsupportedOperationException("position lost");
            // column += cc;
        }
        return cc;
    }

}
