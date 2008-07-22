package net.bodz.mda.parsers.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.types.buf.Buffer.ByteBuffer;

public class PassMemInputStream extends FilterInputStream implements
        BytePassMem, FilePosition {

    private FilePosition positionInfo;
    private ByteBuffer   mem;
    private long         memOffset;

    public PassMemInputStream(InputStream proxy) {
        super(proxy instanceof FilePosition ? proxy
                : new FilePositionInputStream(proxy));
        positionInfo = (FilePosition) super.in;
        mem = new ByteBuffer();
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

    public String getMemString() {
        return mem.toString();
    }

    @Override
    public byte[] copyMem() {
        return mem.copyOf();
    }

    @Override
    public void copyMem(long off, byte[] buf, int boff, int len) {
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
    public void copyMostRecent(byte[] buf) {
        copyMostRecent(buf, 0, buf.length);
    }

    @Override
    public void copyMostRecent(byte[] buf, int boff, int len) {
        long memEnd = memOffset + mem.size();
        copyMem(memEnd - len, buf, boff, len);
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        if (b != -1)
            mem.appendValue((byte) b);
        return b;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int cb = super.read(b, off, len);
        if (cb != -1)
            mem.append(b, off, cb);
        return cb;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int cb = super.read(b);
        if (cb != -1)
            mem.append(b, 0, cb);
        return cb;
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
