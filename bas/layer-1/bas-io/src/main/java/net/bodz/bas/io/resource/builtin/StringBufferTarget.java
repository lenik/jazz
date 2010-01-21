package net.bodz.bas.io.resource.builtin;

import java.io.IOException;

import net.bodz.bas.io.resource.AbstractStreamOutputTarget;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.ICharOut;

public class StringBufferTarget
        extends AbstractStreamOutputTarget {

    private final StringBuffer buffer;

    public StringBufferTarget() {
        this.buffer = new StringBuffer();
    }

    public StringBufferTarget(StringBuffer buffer) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
    }

    @Override
    public ICharOut newCharOut()
            throws IOException {
        return new BCharOut(buffer);
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
