package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICloseable;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.IStreamResource;

public class CroppedRafOutputStream
        extends OutputStream
        implements IByteOut, ISeekable, ICroppable, ICloseable {

    private final File file;
    private final String mode;
    private final long start;
    private final long end;

    private RandomAccessFile raf;
    private boolean closed;
    private long pos;

    public CroppedRafOutputStream(File file, String mode, long start, long end)
            throws IOException {
        this.file = file;
        this.mode = mode;
        this.start = start;
        this.end = end;
        raf = new RandomAccessFile(file, mode);
        raf.seek(pos = start);
    }

    /** ⇱ Implementation Of {@link OutputStream}. */
    ;

    @Override
    public void write(int b)
            throws IOException {
        ensureOpen();

        if (pos >= end)
            throw new IOException("Exceeds the EOF.");

        raf.write(b);
        pos++;
    }

    @Override
    public void write(byte[] b, int off, int len)
            throws IOException {
        ensureOpen();

        long remaining = end - pos;
        if (len > remaining)
            throw new IOException("Exceeds the EOF.");

        raf.write(b, off, len);
        pos += len;
    }

    @Override
    public void write(ByteBuffer buf)
            throws IOException {
        IByteOut.fn.write(this, buf);
    }

    @Override
    public void flush()
            throws IOException {
    }

    /** ⇱ Implementation Of {@link IByteOut}. */
    ;

    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    /** ⇱ Implementation Of {@link ISeekable}. */
    ;

    @Override
    public long tell() {
        try {
            return raf.getFilePointer();
        } catch (IOException e) {
            return -1L;
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
        pos = fPos;
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

        return new CroppedRafStreamResource(file, mode, fStart, fEnd);
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
