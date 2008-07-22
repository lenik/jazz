package net.bodz.mda.parsers.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DocumentPositionInputStream extends FilterInputStream implements
        DocumentPosition {

    private long pos;
    private int  line;
    private int  column;
    private long markPos;
    private int  markLine;
    private int  markColumn;

    public DocumentPositionInputStream(InputStream proxy) {
        super(proxy);
    }

    @Override
    public void mark(int readlimit) {
        super.mark(readlimit);
        markPos = pos;
        markLine = line;
        markColumn = column;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        pos = markPos;
        line = markLine;
        column = markColumn;
    }

    @Override
    public long tell() throws IOException {
        return pos;
    }

    @Override
    public int line() {
        return line;
    }

    @Override
    public int column() {
        return column;
    }

    protected void accept(int b) {
        if (b == '\n') {
            line++;
            column = 0;
        } else {
            column++;
        }
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        if (b != -1) {
            pos++;
            accept(b);
        }
        return b;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int cb = super.read(b, off, len);
        if (cb != -1) {
            pos += cb;
            while (cb-- > 0)
                accept(b[off++]);
        }
        return cb;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int cb = super.read(b);
        if (cb != -1) {
            pos += cb;
            for (int i = 0; i < cb; i++)
                accept(b[i]);
        }
        return cb;
    }

    @Override
    public long skip(long n) throws IOException {
        long cb = super.skip(n);
        if (cb != 0) {
            pos += cb;
            // ignore line info...
            throw new UnsupportedOperationException("position lost");
            // column += cc;
        }
        return cb;
    }

}
