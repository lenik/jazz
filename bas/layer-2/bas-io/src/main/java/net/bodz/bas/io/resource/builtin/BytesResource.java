package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.OutputStream;

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

    public int getLength() {
        if (buffer == null)
            return 0;
        else
            return buffer.size();
    }

    /**
     * @return {@link OutputStream} with {@link OutputStream#close()} filtered out.
     */
    @Override
    protected OutputStream _newOutputStream(boolean append)
            throws IOException {
        if (buffer == null)
            buffer = new MovableByteBuffer();

        int pos;
        if (append) {
            pos = buffer.size();
        } else {
            buffer.resize(pos = 0);
        }

        return new MovableByteBufferOutputStream(buffer, pos);
    }

    @Override
    protected IByteOut _newByteOut(boolean append)
            throws IOException {
        if (buffer == null)
            buffer = new MovableByteBuffer();

        int pos;
        if (append) {
            pos = buffer.size();
        } else {
            buffer.resize(pos = 0);
        }

        return new MovableByteBufferByteOut(buffer, pos);
    }

    @Override
    protected IByteIn _newByteIn()
            throws IOException {
        if (buffer == null)
            throw new IllegalStateException("Resource isn't created, yet.");
        else
            return new MovableByteBufferByteIn(buffer);
    }

}
