package net.bodz.bas.io.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

import net.bodz.bas.io.ICharIOS;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.adapter.CharOutWriter;
import net.bodz.bas.io.res.IStreamResource;

public class MergedCharIOS
        implements ICharIOS {

    private ICharIn in;
    private ICharOut out;
    private ISeekable seeker;
    private ICroppable cropper;

    public MergedCharIOS(ICharIn in, ICharOut out, ISeekable seeker, ICroppable cropper) {
        if (in == null)
            throw new NullPointerException("in");
        if (out == null)
            throw new NullPointerException("out");
        if (seeker == null)
            throw new NullPointerException("seeker");
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
        return in.isClosed() && out.isClosed();
    }

    /** ⇱ Implementation Of {@link ICharIn}. */
    /* _____________________________ */static section.iface __IN__;

    @Override
    public int read()
            throws IOException {
        return in.read();
    }

    @Override
    public int read(char[] buf)
            throws IOException {
        return in.read(buf);
    }

    @Override
    public int read(char[] buf, int off, int len)
            throws IOException {
        return in.read(buf, off, len);
    }

    @Override
    public int read(CharBuffer buf)
            throws IOException {
        return in.read(buf);
    }

    @Override
    public String readString(int maxCharacters)
            throws IOException {
        return in.readString(maxCharacters);
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    public void write(int ch)
            throws IOException {
        out.write(ch);
    }

    @Override
    public void write(char[] buf)
            throws IOException {
        out.write(buf);
    }

    @Override
    public void write(char[] buf, int off, int len)
            throws IOException {
        out.write(buf, off, len);
    }

    @Override
    public void write(CharBuffer buf)
            throws IOException {
        out.write(buf);
    }

    @Override
    public void write(String s)
            throws IOException {
        out.write(s);
    }

    @Override
    public void write(String s, int off, int len)
            throws IOException {
        out.write(s, off, len);
    }

    @Override
    public void write(CharSequence chars)
            throws IOException {
        out.write(chars);
    }

    @Override
    public void write(CharSequence chars, int start, int end)
            throws IOException {
        out.write(chars, start, end);
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

    @Override
    public Writer toWriter() {
        return new CharOutWriter(this);
    }

    /** ⇱ Implementation Of {@link ISeekable}. */
    /* _____________________________ */static section.iface __SEEK__;

    @Override
    public void seek(long position)
            throws IOException {
        seeker.seek(position);
    }

    @Override
    public long tell() {
        return seeker.tell();
    }

    @Override
    public long length()
            throws IOException {
        return seeker.length();
    }

    /** ⇱ Implementation Of {@link ICroppable}. */
    /* _____________________________ */static section.iface __CROP__;

    @Override
    public IStreamResource crop(long start, long end)
            throws IOException {
        return cropper.crop(start, end);
    }

}
