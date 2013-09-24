package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

import net.bodz.bas.io.AbstractCharIOS;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.builtin.CharArrayResource;

/**
 * @see CharBufferCharIn
 * @see CharBufferCharOut
 */
public class CharBufferCharIOS
        extends AbstractCharIOS {

    private final CharBuffer buf;

    public CharBufferCharIOS(CharBuffer buf) {
        if (buf == null)
            throw new NullPointerException("buf");
        this.buf = buf;
    }

    /** ⇱ Implementation Of {@link ICharIn}. */
    ;

    @Override
    public int read()
            throws IOException {
        if (buf.hasRemaining()) {
            char ch = buf.get();
            return ch;
        }
        return -1;
    }

    @Override
    public int read(char[] dst, int off, int len)
            throws IOException {
        int remaining = buf.remaining();
        if (remaining == 0)
            return -1;
        int ccRead = Math.min(len, buf.remaining());
        buf.get(dst, off, ccRead);
        return ccRead;
    }

    /** ⇱ Implementation Of {@link ICharOut}. */
    ;

    @Override
    public void write(int ch)
            throws IOException {
        buf.put((char) ch);
    }

    @Override
    public void write(char[] src, int off, int len)
            throws IOException {
        try {
            buf.put(src, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(CharBuffer src)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        try {
            buf.put(src);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    /** ⇱ Implementation Of {@link ISeekable}. */
    ;

    @Override
    public long tell() {
        return buf.position();
    }

    @Override
    public void seek(long position)
            throws IOException {
        buf.position((int) position);
    }

    @Override
    public long length()
            throws IOException {
        return buf.capacity();
    }

    /** ⇱ Implementation Of {@link ICroppable}. */
    ;

    @Override
    public IStreamResource crop(long _start, long _end)
            throws IOException {
        int start = (int) _start;
        int end = (int) _end;
        int len = end - start;

        char[] array = buf.array();
        int offset = buf.arrayOffset();
        int cap = buf.capacity();

        if (len > cap)
            throw new IllegalArgumentException("end is out of range: " + _end);

        return new CharArrayResource(array, offset + start, len);
    }

}
