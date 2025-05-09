package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.meta.decl.NotNull;

public class MergedByteIOS
        implements IByteIOS {

    private final IByteIn in;
    private final IByteOut out;
    private final ISeekable seeker;
    private final ICroppable cropper;

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

    /**
     * ⇱ Implementation Of {@link IByteIn}.
     */
    /* _____________________________ */static section.iface __IN__;

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
    public int read(@NotNull byte[] buf, int off, int len)
            throws IOException {
        return in.read(buf, off, len);
    }

    /**
     * ⇱ Implementation Of {@link IByteOut}.
     */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    public void writeByte(int b)
            throws IOException {
        out.writeByte(b);
    }

    @Override
    public void write(@NotNull byte[] buf)
            throws IOException {
        out.write(buf);
    }

    @Override
    public void write(@NotNull byte[] buf, int off, int len)
            throws IOException {
        out.write(buf, off, len);
    }

    @Override
    public void write(ByteBuffer buf)
            throws IOException {
        out.write(buf);
    }

    @Override
    public void flush()
            throws IOException {
        out.flush();
    }

    /**
     * ⇱ Implementation Of {@link ISeekable}.
     */
    /* _____________________________ */static section.iface __SEEK__;

    @Override
    public long tell() {
        return seeker.tell();
    }

    @Override
    public void seek(long position)
            throws IOException {
        seeker.seek(position);
    }

    @Override
    public long length()
            throws IOException {
        return seeker.length();
    }

    /**
     * ⇱ Implementation Of {@link ICroppable}.
     */
    /* _____________________________ */static section.iface __CROP__;

    @Override
    public IStreamResource crop(long start, long end)
            throws IOException {
        return cropper.crop(start, end);
    }

}
