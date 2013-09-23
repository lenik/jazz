package net.bodz.bas.io;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.io.res.IStreamResource;

public class CharIOImpl
        implements ICharIOS {

    private ICharIn in;
    private ICharOut out;
    private ISeekable seeker;
    private ICroppable cropper;

    public CharIOImpl(ICharIn in, ICharOut out, ISeekable seeker, ICroppable cropper) {
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
        return in.isClosed();
    }

    /** ⇱ Implementation Of {@link ICharIn}. */
    ;

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
    ;

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
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        out.write(chars, off, len);
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
    public void seek(long position)
            throws IOException {
        seeker.seek(position);
    }

    @Override
    public long tell() {
        return seeker.tell();
    }

    /** ⇱ Implementation Of {@link ICroppable}. */
    ;

    @Override
    public IStreamResource crop(long start, long end)
            throws IOException {
        return cropper.crop(start, end);
    }

}