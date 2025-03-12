package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.t.buffer.MovableByteBuffer;

public class BytesResource
        extends AbstractBinaryResource<BytesResource> {

    private MovableByteBuffer buffer;

    public BytesResource() {
        this(null);
    }

    public BytesResource(MovableByteBuffer buffer) {
        this.buffer = buffer;
    }

    public boolean isAllocated() {
        return buffer != null;
    }

    public void unallocate() {
        buffer = null;
    }

    public MovableByteBuffer getBuffer() {
        return buffer;
    }

    @Override
    public Long getLength() {
        if (buffer == null)
            return 0L;
        else
            return (long) buffer.size();
    }

    /**
     * @return {@link OutputStream} with {@link OutputStream#close()} filtered out.
     */
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        if (buffer == null)
            buffer = new MovableByteBuffer();

        boolean append = OpenOptions.isAppend(options);

        int pos;
        if (append) {
            pos = buffer.size();
        } else {
            buffer.resize(pos = 0);
        }

        return new MovableByteBufferOutputStream(buffer, pos);
    }

    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {
        if (buffer == null)
            buffer = new MovableByteBuffer();

        boolean append = OpenOptions.isAppend(options);

        int pos;
        if (append) {
            pos = buffer.size();
        } else {
            buffer.resize(pos = 0);
        }

        return new MovableByteBufferByteOut(buffer, pos);
    }

    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        if (buffer == null)
            throw new IllegalStateException("Resource isn't created, yet.");
        else
            return new MovableByteBufferByteIn(buffer);
    }

    // XXX byte/char IOS, seeker, cropper

    @Override
    protected ISeekable getSeeker() {
        return null;
    }

    @Override
    protected ICroppable getCropper() {
        return null;
    }

    public byte[] toByteArray() {
        return buffer.copy();
    }

    public String toString(String charset)
            throws UnsupportedEncodingException {
        return buffer.toString(charset);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
