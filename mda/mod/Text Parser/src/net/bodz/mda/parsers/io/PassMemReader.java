package net.bodz.mda.parsers.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.types.buf.Buffer.CharBuffer;

public class PassMemReader extends FilterReader implements CharPassMem,
        FilePosition {

    private FilePosition positionInfo;
    private CharBuffer   mem;
    private long         memOffset;

    public PassMemReader(Reader proxy) {
        super(proxy instanceof FilePosition ? proxy : new FilePositionReader(
                proxy));
        positionInfo = (FilePosition) super.in;
        mem = new CharBuffer();
    }

    @Override
    public long tell() throws IOException {
        return positionInfo.tell();
    }

    @Override
    public void drop() {
        memOffset += mem.size();
        mem.clear();
        // mem.compact();
    }

    @Override
    public void keepOnly(int keepMostRecentSize) {
        int size = mem.size();
        int dropSize = size - keepMostRecentSize;
        memOffset += dropSize;
        mem.delete(0, dropSize);
        mem.compact();
    }

    @Override
    public int getMemSize() {
        return mem.size();
    }

    @Override
    public char[] copyMem() {
        return mem.copyOf();
    }

    public String getMemString() {
        return mem.toString();
    }

    @Override
    public void copyMem(long off, char[] buf, int boff, int len) {
        if (len == 0)
            return;
        if (off < memOffset)
            throw new OutOfDomainException("off", off, memOffset);
        if (len < 0)
            throw new OutOfDomainException("len", len, 0);
        long memEnd = memOffset + mem.size();
        if (off + len > memEnd)
            throw new OutOfDomainException("_end", off + len, memEnd);
        assert boff >= 0 && boff + len <= buf.length;
        int _off = (int) (off - memOffset);
        mem.copyTo(_off, buf, boff, len);
    }

    @Override
    public void copyMostRecent(char[] buf) {
        copyMostRecent(buf, 0, buf.length);
    }

    @Override
    public void copyMostRecent(char[] buf, int boff, int len) {
        long memEnd = memOffset + mem.size();
        copyMem(memEnd - len, buf, boff, len);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c != -1)
            mem.appendValue((char) c);
        return c;
    }

    @Override
    public int read(char[] b, int off, int len) throws IOException {
        int cc = super.read(b, off, len);
        if (cc != -1)
            mem.append(b, off, cc);
        return cc;
    }

    @Override
    public int read(char[] b) throws IOException {
        int cc = super.read(b);
        if (cc != -1)
            mem.append(b, 0, cc);
        return cc;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        long newOffset = positionInfo.tell();
        if (newOffset < memOffset) {
            // some mem dropped before reset
            memOffset = newOffset;
            mem.clear();
        } else {
            long dist = newOffset - memOffset;
            int size = mem.size();
            if (size >= dist) {
                // normal case
                int extra = (int) (size - dist);
                mem.delete(size - extra, extra);
            } else {
                // skips??
                memOffset = newOffset;
                mem.clear();
            }
        }
    }

    @Override
    public long skip(long n) throws IOException {
        long cb = super.skip(n);
        if (cb != 0) {
            memOffset = positionInfo.tell();
            mem.clear();
        }
        return cb;
    }

}
