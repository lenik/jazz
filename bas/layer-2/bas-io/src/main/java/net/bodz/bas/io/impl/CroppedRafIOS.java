package net.bodz.bas.io.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.meta.decl.NotNull;

public class CroppedRafIOS
        implements IByteIOS {

    private final File file;
    private final String mode;
    private final long start;
    private final long end;

    private RandomAccessFile raf;
    private long ap;

    private boolean closed;

    public CroppedRafIOS(File file, String mode, long start, long end)
            throws IOException {
        this.file = file;
        this.mode = mode;
        this.start = start;
        this.end = end;
        raf = new RandomAccessFile(file, mode);
        raf.seek(ap = start);
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public void close()
            throws IOException {
        raf.close();
        closed = true;
    }

    protected void ensureOpen() {
        if (closed)
            throw new IllegalStateException("closed");
    }

    /**
     * ⇱ Implementation Of {@link IByteIn}.
     */
    /* _____________________________ */static section.iface __IN__;

    @Override
    public int read()
            throws IOException {
        ensureOpen();
        if (ap >= end)
            return -1;

        int byt = raf.read();
        ap++;

        return byt;
    }

    @Override
    public int read(@NotNull byte[] buf, int off, int len)
            throws IOException {
        ensureOpen();

        long remaining = end - ap;
        if (len > remaining)
            len = (int) remaining;

        int actual = raf.read(buf, off, len);
        ap += actual;
        return actual;
    }

    @Override
    public long skip(long n)
            throws IOException {
        long willEndAt = ap + n;
        if (willEndAt > end)
            n = end - ap;

        if (n <= Integer.MAX_VALUE) {
            int actual = raf.skipBytes((int) n);
            ap += actual;
            return actual;
        } else {
            long position = raf.getFilePointer() + n;
            raf.seek(position);
            ap += n;
            return n;
        }
    }

    /**
     * ⇱ Implementation Of {@link IByteOut}.
     */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    public void write(int b)
            throws IOException {
        ensureOpen();

        if (ap >= end)
            throw new IOException("Exceeds the EOF.");

        raf.write(b);
        ap++;
    }

    @Override
    public void write(@NotNull byte[] buf, int off, int len)
            throws IOException {
        ensureOpen();

        long remaining = end - ap;
        if (len > remaining)
            throw new IOException("Exceeds the EOF.");

        raf.write(buf, off, len);
        ap += len;
    }

    /**
     * ⇱ Implementation Of {@link ISeekable}.
     */
    /* _____________________________ */static section.iface __SEEK__;

    @Override
    public long tell() {
        return ap - start;
    }

    @Override
    public void seek(long position)
            throws IOException {
        ensureOpen();

        long fPos = start + position;
        if (position < 0 || fPos > this.end)
            throw new IllegalArgumentException("position");

        raf.seek(fPos);
        ap = fPos;
    }

    @Override
    public long length()
            throws IOException {
        return raf.length();
    }

    /**
     * ⇱ Implementation Of {@link ICropapble}.
     */
    /* _____________________________ */static section.iface __CROP__;

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

}
