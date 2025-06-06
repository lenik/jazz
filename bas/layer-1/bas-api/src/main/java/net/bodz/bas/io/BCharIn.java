package net.bodz.bas.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.meta.decl.NotNull;

public class BCharIn
        extends Reader
        implements ICharIn {

    private final char[] src;
    private final int start;
    private final int end;

    private int position;
    boolean closed;

    /**
     * @throws NullPointerException If <code>byteArray</code> is <code>null</code>.
     */
    public BCharIn(char[] buf) {
        if (buf == null)
            throw new NullPointerException("buf");
        this.src = buf;
        position = start = 0;
        end = buf.length;
    }

    /**
     * @throws NullPointerException      If <code>byteArray</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException If <code>start</code> or <code>end</code> is out of bound.
     */
    public BCharIn(char[] buf, int off, int len) {
        if (buf == null)
            throw new NullPointerException("buf");
        if (off < 0 || off > buf.length)
            throw new OutOfDomainException("off", off, buf.length);
        if (len < 0 || off + len > buf.length)
            throw new OutOfDomainException("len", len, buf.length - off);
        this.src = buf;
        this.start = off;
        this.end = off + len;
        this.position = off;
    }

    @Override
    public int read()
            throws IOException {
        if (position < end)
            return src[position++];
        return -1;
    }

    @Override
    public int read(@NotNull char[] dst, int dstOffset, int len)
            throws IOException {
        int cbRead = Math.min(len, end - position);
        if (cbRead <= 0)
            return -1;
        System.arraycopy(src, position, dst, dstOffset, cbRead);
        position += cbRead;
        return cbRead;
    }

    @Override
    public void close() {
    }

    @Override
    public int hashCode() {
        int hash = 0x7b8108a5;
        hash += Arrays.hashCode(src);
        hash += start * 3;
        hash += end * 53;
        hash += position * 113;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BCharIn))
            return false;
        BCharIn o = (BCharIn) obj;
        if (start != o.start || end != o.end || position != o.position)
            return false;
        if (!Arrays.equals(src, o.src))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String graph = new String(src, start, position - start) //
                + "|" + new String(src, position, end - position);
        return graph;
    }

}
