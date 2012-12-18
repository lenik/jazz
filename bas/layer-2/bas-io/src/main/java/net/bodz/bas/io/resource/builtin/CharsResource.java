package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.t.buffer.MovableCharBuffer;

public class CharsResource
        extends AbstractTextStreamResource {

    MovableCharBuffer buffer;

    public CharsResource() {
        this(null);
    }

    public CharsResource(MovableCharBuffer buffer) {
        this.buffer = buffer;
    }

    public boolean isAllocated() {
        return buffer != null;
    }

    public void unallocate() {
        buffer = null;
    }

    public MovableCharBuffer getBuffer() {
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
     * @return {@link Writer} with {@link Writer#close()} filtered out.
     */
    @Override
    protected Writer _newWriter(boolean append)
            throws IOException {
        if (buffer == null)
            buffer = new MovableCharBuffer();

        int pos;
        if (append) {
            pos = buffer.size();
        } else {
            buffer.resize(pos = 0);
        }

        return new MovableCharBufferWriter(buffer, pos);
    }

    @Override
    protected IPrintOut _newPrintOut(boolean append)
            throws IOException {
        if (buffer == null)
            buffer = new MovableCharBuffer();

        int pos;
        if (append) {
            pos = buffer.size();
        } else {
            buffer.resize(pos = 0);
        }

        return new MovableCharBufferPrintOut(buffer, pos);
    }

    @Override
    protected ICharOut _newCharOut(boolean append)
            throws IOException {
        return _newPrintOut(append);
    }

    @Override
    protected ICharIn _newCharIn()
            throws IOException {
        if (buffer == null)
            throw new IllegalStateException("Resource isn't created, yet");
        else
            return new MovableCharBufferCharIn(buffer);
    }

}
