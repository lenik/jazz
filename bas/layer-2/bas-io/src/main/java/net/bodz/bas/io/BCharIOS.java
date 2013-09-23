package net.bodz.bas.io;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.builtin.CharArrayResource;

/**
 * @see BCharIn
 * @see BCharOut
 */
public class BCharIOS
        extends AbstractCharIOS {

    private final char[] buf;
    private final int start;
    private final int end;

    private int position;

    /**
     * @throws NullPointerException
     *             If <code>charArray</code> is <code>null</code>.
     */
    public BCharIOS(char[] buf) {
        if (buf == null)
            throw new NullPointerException("buf");
        this.buf = buf;
        start = 0;
        end = buf.length;
    }

    /**
     * @throws NullPointerException
     *             If <code>charArray</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If <code>start</code> or <code>end</code> is out of bound.
     */
    public BCharIOS(char[] buf, int off, int len) {
        if (buf == null)
            throw new NullPointerException("buf");
        if (off < 0 || off > buf.length)
            throw new OutOfDomainException("off", off, buf.length);
        if (len < 0 || off + len > buf.length)
            throw new OutOfDomainException("len", len, buf.length - off);
        this.buf = buf;
        this.start = off;
        this.end = off + len;
        this.position = off;
    }

    @Override
    public int hashCode() {
        int hash = 0x93cfab31;
        hash += buf.hashCode();
        hash += start * 3;
        hash += end * 53;
        hash += position * 113;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BCharIOS))
            return false;
        BCharIOS o = (BCharIOS) obj;
        if (start != o.start || end != o.end || position != o.position)
            return false;
        if (!Arrays.equals(buf, o.buf))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String graph = new String(buf, start, position - start) //
                + "|" + new String(buf, position, end - position);
        return graph;
    }

    /** ⇱ Implementation Of {@link ICharIn}. */
    ;

    @Override
    public int read()
            throws IOException {
        ensureOpen();
        if (position < end)
            return buf[position++] & 0xff;
        else
            return -1;
    }

    @Override
    public int read(char[] dst, int dstOffset, int len)
            throws IOException {
        ensureOpen();
        int cbRead = Math.min(len, end - position);
        if (cbRead <= 0)
            return -1;
        System.arraycopy(buf, position, dst, dstOffset, cbRead);
        position += cbRead;
        return cbRead;
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    ;

    @Override
    public void write(int b)
            throws IOException {
        ensureOpen();
        if (position >= end)
            throw new EOFException();
        buf[position++] = (char) b;
    }

    @Override
    public void write(char[] buf, int off, int len)
            throws IOException {
        ensureOpen();
        int willEndAt = position + len;
        if (willEndAt >= end)
            throw new EOFException();

        System.arraycopy(buf, off, this.buf, position, len);

        position += len;
    }

    /** ⇱ Implementation Of {@link ISeekable}. */
    ;

    @Override
    public long tell() {
        return position;
    }

    @Override
    public void seek(long position)
            throws IOException {
        if (position < 0 || position > end - start)
            throw new IllegalArgumentException("Position out of range: " + position);
        this.position = start + (int) position;
    }

    @Override
    public IStreamResource crop(long start, long end)
            throws IOException {
        long fStart = this.start + start;
        long fEnd = this.end + end;

        if (start < 0 || fStart > this.end)
            throw new IllegalArgumentException("start");
        if (end < 0 || fEnd > this.end)
            throw new IllegalArgumentException("end");

        int _start = (int) fStart;
        int _end = (int) fEnd;
        CharArrayResource cropped = new CharArrayResource(buf, _start, _end - _start);
        return cropped;
    }

}
