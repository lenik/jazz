package net.bodz.bas.io.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICloseable;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.IStreamResource;

public class CroppedRafIn
        extends InputStream
        implements IByteIn, ISeekable, ICroppable, ICloseable {

    private final File file;
    private final String mode;
    private final long start;
    private final long end;

    private RandomAccessFile raf;
    private boolean closed;
    private long pos;

    private long markedPosition;

    public CroppedRafIn(File file, String mode, long start, long end)
            throws IOException {
        this.file = file;
        this.mode = mode;
        this.start = start;
        this.end = end;
        raf = new RandomAccessFile(file, mode);
        raf.seek(pos = start);
    }

    /** ⇱ Implementation Of {@link InputStream}. */
    ;

    @Override
    public int read()
            throws IOException {
        ensureOpen();
        if (pos >= end)
            return -1;

        int byt = raf.read();
        pos++;

        return byt;
    }

    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        ensureOpen();

        long remaining = end - pos;
        if (len > remaining)
            len = (int) remaining;

        int actual = raf.read(b, off, len);
        pos += actual;
        return actual;
    }

    @Override
    public int read(ByteBuffer buf)
            throws IOException {
        return IByteIn.fn.read(this, buf);
    }

    @Override
    public long skip(long n)
            throws IOException {
        long willEndAt = pos + n;
        if (willEndAt > end)
            n = end - pos;

        if (n <= Integer.MAX_VALUE) {
            int actual = raf.skipBytes((int) n);
            pos += actual;
            return actual;
        } else {
            long position = raf.getFilePointer() + n;
            raf.seek(position);
            pos += n;
            return n;
        }
    }

    @Override
    public int available()
            throws IOException {
        long position = raf.getFilePointer();
        long remaining = raf.length() - position;
        if (remaining > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else
            return (int) remaining;
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    @Override
    public void mark(int readlimit) {
        ensureOpen();
        markedPosition = tell();
    }

    @Override
    public void reset()
            throws IOException {
        ensureOpen();
        if (markedPosition == -1)
            throw new IOException("Not marked yet.");
        raf.seek(markedPosition);
    }

    /** ⇱ Implementation Of {@link ISeekable}. */
    ;

    @Override
    public long tell() {
        try {
            return raf.getFilePointer();
        } catch (IOException e) {
            return -1;
        }
    }

    @Override
    public void seek(long position)
            throws IOException {
        ensureOpen();

        long fPos = start + position;
        if (position < 0 || fPos > this.end)
            throw new IllegalArgumentException("position");

        raf.seek(fPos);
    }

    /** ⇱ Implementation Of {@link ICroppable}. */
    ;

    @Override
    public IStreamResource crop(long start, long end)
            throws IOException {
        final long fStart = this.start + start;
        final long fEnd = this.start + end;

        if (start < 0 || fStart > this.end)
            throw new IllegalArgumentException("start");
        if (end < 0 || fEnd > this.end)
            throw new IllegalArgumentException("end");

        return new CroppedRafResource(file, mode, fStart, fEnd);
    }

    /** ⇱ Implementation Of {@link ICloseable}. */
    ;

    private void ensureOpen() {
        if (closed)
            throw new IllegalStateException("closed");
    }

    @Override
    public void close()
            throws IOException {
        raf.close();
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

}
