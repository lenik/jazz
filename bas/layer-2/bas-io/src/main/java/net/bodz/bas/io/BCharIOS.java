package net.bodz.bas.io;

import java.io.EOFException;
import java.io.IOException;

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

    private int ap;

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
        this.ap = off;
    }

    @Override
    public String toString() {
        String graph = new String(buf, start, ap - start) //
                + "|" + new String(buf, ap, end - ap);
        return graph;
    }

    /** ⇱ Implementation Of {@link ICharIn}. */
    ;

    @Override
    public int read()
            throws IOException {
        ensureOpen();
        if (ap < end)
            return buf[ap++] & 0xff;
        else
            return -1;
    }

    @Override
    public int read(char[] dst, int dstOffset, int len)
            throws IOException {
        ensureOpen();
        int cbRead = Math.min(len, end - ap);
        if (cbRead <= 0)
            return -1;
        System.arraycopy(buf, ap, dst, dstOffset, cbRead);
        ap += cbRead;
        return cbRead;
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    ;

    @Override
    public void write(int b)
            throws IOException {
        ensureOpen();
        if (ap >= end)
            throw new EOFException();
        buf[ap++] = (char) b;
    }

    @Override
    public void write(char[] buf, int off, int len)
            throws IOException {
        ensureOpen();
        int willEndAt = ap + len;
        if (willEndAt >= end)
            throw new EOFException();

        System.arraycopy(buf, off, this.buf, ap, len);

        ap += len;
    }

    /** ⇱ Implementation Of {@link ISeekable}. */
    ;

    @Override
    public long tell() {
        return ap - start;
    }

    @Override
    public void seek(long position)
            throws IOException {
        if (position < 0 || position > end - start)
            throw new IllegalArgumentException("Position out of range: " + position);
        this.ap = start + (int) position;
    }

    @Override
    public long length()
            throws IOException {
        return end - start;
    }

    /** ⇱ Implementation Of {@link ICroppable}. */
    ;

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
