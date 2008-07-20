package net.bodz.mda.parsers.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import net.bodz.bas.proxy.java.io.ProxyReader;

public class TellableReader extends ProxyReader implements Tellable {

    private long pos     = 0L;
    private long markPos = 0L;

    public TellableReader(Reader proxy) {
        super(proxy);
    }

    @Override
    public long tell() throws IOException {
        return pos;
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        super.mark(readAheadLimit);
        markPos = pos;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        pos = markPos;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c != -1)
            pos++;
        return c;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int cc = super.read(cbuf, off, len);
        if (cc != -1)
            pos += cc;
        return cc;
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        int cc = super.read(cbuf);
        if (cc != -1)
            pos += cc;
        return cc;
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        int cc = super.read(target);
        if (cc != -1)
            pos += cc;
        return cc;
    }

    @Override
    public long skip(long n) throws IOException {
        long cc = super.skip(n);
        pos += cc;
        return cc;
    }

}
