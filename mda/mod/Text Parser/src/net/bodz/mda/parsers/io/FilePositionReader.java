package net.bodz.mda.parsers.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class FilePositionReader extends FilterReader implements FilePosition {

    private long pos;
    private long markPos;

    public FilePositionReader(Reader proxy) {
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
        int c = in.read();
        if (c != -1)
            pos++;
        return c;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int cc = in.read(cbuf, off, len);
        if (cc != -1)
            pos += cc;
        return cc;
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        int cc = in.read(cbuf);
        if (cc != -1)
            pos += cc;
        return cc;
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        int cc = in.read(target);
        if (cc != -1)
            pos += cc;
        return cc;
    }

    @Override
    public long skip(long n) throws IOException {
        long cc = in.skip(n);
        pos += cc;
        return cc;
    }

}
