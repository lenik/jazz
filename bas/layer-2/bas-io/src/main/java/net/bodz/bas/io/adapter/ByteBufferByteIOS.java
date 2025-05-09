package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.builtin.ByteArrayResource;
import net.bodz.bas.meta.decl.NotNull;

/**
 * @see ByteBufferByteIn
 * @see ByteBufferByteOut
 */
public class ByteBufferByteIOS
        implements IByteIOS {

    private final ByteBuffer buf;

    public ByteBufferByteIOS(ByteBuffer buf) {
        if (buf == null)
            throw new NullPointerException("buf");
        this.buf = buf;
    }

    /**
     * ⇱ Implementation Of {@link IByteIn}.
     */
    /* _____________________________ */static section.iface __IN__;

    @Override
    public int read()
            throws IOException {
        if (buf.hasRemaining()) {
            byte b = buf.get();
            return b & 0xFF;
        }
        return -1;
    }

    @Override
    public int read(@NotNull byte[] dst, int off, int len)
            throws IOException {
        int remaining = buf.remaining();
        if (remaining == 0)
            return -1;
        int cbRead = Math.min(len, buf.remaining());
        buf.get(dst, off, cbRead);
        return cbRead;
    }

    @Override
    public long skip(long n)
            throws IOException {
        int min = (int) Math.min(n, buf.remaining());
        int position = buf.position();
        buf.position(position + min);
        return min;
    }

    /**
     * ⇱ Implementation Of {@link IByteOut}.
     */
    /* _____________________________ */static section.iface __OUT__;

    @Override
    public void writeByte(int b)
            throws IOException {
        buf.put((byte) b);
    }

    @Override
    public void write(@NotNull byte[] src, int off, int len)
            throws IOException {
        try {
            buf.put(src, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(ByteBuffer src)
            throws IOException {
        if (src == null)
            throw new NullPointerException("src");
        try {
            buf.put(src);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    /**
     * ⇱ Implementation Of {@link ISeekable}.
     */
    /* _____________________________ */static section.iface __SEEK__;

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

    /**
     * ⇱ Implementation Of {@link ICroppable}.
     */
    /* _____________________________ */static section.iface __CROP__;

    @Override
    public IStreamResource crop(long _start, long _end)
            throws IOException {
        int start = (int) _start;
        int end = (int) _end;
        int len = end - start;

        byte[] array = buf.array();
        int offset = buf.arrayOffset();
        int cap = buf.capacity();

        if (len > cap)
            throw new IllegalArgumentException("end is out of range: " + _end);

        return new ByteArrayResource(array, offset + start, len);
    }

}
