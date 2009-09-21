package net.bodz.bas.lop.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @test {@link TellableInputStreamTest}
 */
public class TellableInputStream extends FilterInputStream implements Tellable {

    private long pos;
    private long markPos;

    public TellableInputStream(InputStream proxy) {
        super(proxy);
    }

    @Override
    public void mark(int readlimit) {
        super.mark(readlimit);
        markPos = pos;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        pos = markPos;
    }

    @Override
    public long tell() {
        return pos;
    }

    @Override
    public int read() throws IOException {
        int b = in.read();
        if (b != -1)
            pos++;
        return b;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int cb = in.read(b, off, len);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int cb = in.read(b);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public long skip(long n) throws IOException {
        long cb = in.skip(n);
        pos += cb;
        return cb;
    }

}
