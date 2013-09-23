package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.IStreamResource;

public class MergedByteIOS
        implements IByteIOS {

    private IByteIn in;
    private IByteOut out;
    private ISeekable seeker;
    private ICroppable cropper;

    public MergedByteIOS(IByteIn in, IByteOut out, ISeekable seeker, ICroppable cropper) {
        if (in == null)
            throw new NullPointerException("in");
        if (out == null)
            throw new NullPointerException("out");
        if (seeker == null)
            throw new NullPointerException("seekable");
        if (cropper == null)
            throw new NullPointerException("cropper");
        this.in = in;
        this.out = out;
        this.seeker = seeker;
        this.cropper = cropper;
    }

    @Override
    public void close()
            throws IOException {
        in.close();
        out.close();
    }

    @Override
    public boolean isClosed() {
        return in.isClosed();
    }

    /** ⇱ Implementation Of {@link IByteIn}. */
    ;

    @Override
    public int read()
            throws IOException {
        return in.read();
    }

    @Override
    public long skip(long n)
            throws IOException {
        return in.skip(n);
    }

    @Override
    public int read(byte[] buf)
            throws IOException {
        return in.read(buf);
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        return in.read(buf, off, len);
    }

    @Override
    public int read(ByteBuffer buf)
            throws IOException {
        return IByteIn.fn.read(this, buf);
    }

    /** ⇱ Implementation Of {@link IByteOut}. */
    ;

    @Override
    public void write(int b)
            throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] buf)
            throws IOException {
        out.write(buf);
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
        out.write(buf, off, len);
    }

    @Override
    public void write(ByteBuffer buf)
            throws IOException {
        IByteOut.fn.write(this, buf);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
        out.flush(strict);
    }

    @Override
    public void flush()
            throws IOException {
        out.flush();
    }

    /** ⇱ Implementation Of {@link ISeekable}. */
    ;

    @Override
    public long tell() {
        return seeker.tell();
    }

    @Override
    public void seek(long position)
            throws IOException {
        seeker.seek(position);
    }

    /** ⇱ Implementation Of {@link ICroppable}. */
    ;

    @Override
    public IStreamResource crop(long start, long end)
            throws IOException {
        return cropper.crop(start, end);
    }

}
