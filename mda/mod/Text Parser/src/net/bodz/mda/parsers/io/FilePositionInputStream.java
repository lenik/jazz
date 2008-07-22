package net.bodz.mda.parsers.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilePositionInputStream extends FilterInputStream implements
        FilePosition {

    private long pos;
    private long markPos;

    public FilePositionInputStream(InputStream proxy) {
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
    public long tell() throws IOException {
        return pos;
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        if (b != -1)
            pos++;
        return b;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int cb = super.read(b, off, len);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int cb = super.read(b);
        if (cb != -1)
            pos += cb;
        return cb;
    }

    @Override
    public long skip(long n) throws IOException {
        long cb = super.skip(n);
        pos += cb;
        return cb;
    }

}
