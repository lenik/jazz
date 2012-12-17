package net.bodz.bas.io.resource.builtin;

import java.nio.charset.CharsetDecoder;

import net.bodz.bas.io.resource.AbstractStreamOutputTarget;
import net.bodz.bas.sio.BufCharOut;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.util.DecodedByteOut;

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

    public StringBuffer getBuffer() {
        return buffer;
    }

    @Override
    protected ICharOut _newCharOut() {
        return _newPrintOut();
    }

    @Override
    protected BufCharOut _newPrintOut() {
        return new BufCharOut(buffer);
    }

    @Override
    protected IByteOut _newByteOut() {
        ICharOut charOut = _newCharOut();
        CharsetDecoder decoder = getCharset().newDecoder();
        return new DecodedByteOut(charOut, decoder);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
