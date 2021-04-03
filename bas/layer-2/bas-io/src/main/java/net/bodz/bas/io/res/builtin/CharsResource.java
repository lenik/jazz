package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.t.buffer.MovableCharBuffer;

public class CharsResource
        extends AbstractTextResource {

    private MovableCharBuffer buffer;

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
    public long getLength() {
        if (buffer == null)
            return 0L;
        else
            return buffer.size();
    }

    @Override
    public boolean isCharInPreferred() {
        return true;
    }

    /**
     * @return {@link Writer} with {@link Writer#close()} filtered out.
     */
    @Override
    protected Writer _newWriter(OpenOption... options)
            throws IOException {
        if (buffer == null)
            buffer = new MovableCharBuffer();

        boolean append = OpenOptions.isAppend(options);

        int pos;
        if (append) {
            pos = buffer.size();
        } else {
            buffer.resize(pos = 0);
        }

        return new MovableCharBufferWriter(buffer, pos);
    }

    @Override
    protected IPrintOut _newPrintOut(OpenOption... options)
            throws IOException {
        if (buffer == null)
            buffer = new MovableCharBuffer();

        boolean append = OpenOptions.isAppend(options);

        int pos;
        if (append) {
            pos = buffer.size();
        } else {
            buffer.resize(pos = 0);
        }

        return new MovableCharBufferPrintOut(buffer, pos);
    }

    @Override
    protected ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        return _newPrintOut(options);
    }

    @Override
    protected ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        if (buffer == null)
            throw new IllegalStateException("Resource isn't created, yet");
        else
            return new MovableCharBufferCharIn(buffer);
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

}
