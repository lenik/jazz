package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.t.buffer.MovableByteBuffer;

public class BytesResource
        extends AbstractBinaryStreamResource {

    MovableByteBuffer buffer;

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
            return null;
        else
            return (long) buffer.size();
    }

    /**
     * @return {@link OutputStream} with {@link OutputStream#close()} filtered out.
     */
    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
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
    protected IByteOut _newByteOut(OpenOption... options)
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
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        if (buffer == null)
            throw new IllegalStateException("Resource isn't created, yet.");
        else
            return new MovableByteBufferByteIn(buffer);
    }

}
