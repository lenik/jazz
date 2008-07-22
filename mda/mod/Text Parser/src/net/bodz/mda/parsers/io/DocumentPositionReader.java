package net.bodz.mda.parsers.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class DocumentPositionReader extends FilterReader implements
        DocumentPosition {

    private long pos;
    private int  line;
    private int  column;
    private long markPos;
    private int  markLine;
    private int  markColumn;

    public DocumentPositionReader(Reader proxy) {
        super(proxy);
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

    @Override
    public void mark(int readAheadLimit) throws IOException {
        super.mark(readAheadLimit);
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

    protected void accept(int c) {
        if (c == '\n') {
            line++;
            column = 0;
        } else {
            column++;
        }
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c != -1) {
            pos++;
            accept(c);
        }
        return c;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int cc = super.read(cbuf, off, len);
        if (cc != -1) {
            pos += cc;
            while (cc-- > 0)
                accept(cbuf[off++]);
        }
        return cc;
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        int cc = super.read(cbuf);
        if (cc != -1) {
            pos += cc;
            for (int i = 0; i < cc; i++)
                accept(cbuf[i]);
        }
        return cc;
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        int cc = super.read(target);
        if (cc != -1) {
            pos += cc;
            for (int i = 0; i < cc; i++)
                accept(target.get(i));
        }
        return cc;
    }

    @Override
    public long skip(long n) throws IOException {
        long cc = super.skip(n);
        if (cc != 0) {
            pos += cc;
            // ignore line info...
            throw new UnsupportedOperationException("position lost");
            // column += cc;
        }
        return cc;
    }

}
