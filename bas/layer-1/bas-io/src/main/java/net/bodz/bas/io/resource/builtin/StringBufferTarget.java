package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.nio.charset.CharsetDecoder;

import net.bodz.bas.io.resource.AbstractStreamOutputTarget;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.nio.DecodedByteOut;

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
    public BCharOut newPrintOut()
            throws IOException {
        return new BCharOut(buffer);
    }

    @Override
    public IByteOut newByteOut()
            throws IOException {
        ICharOut charOut = newCharOut();
        CharsetDecoder decoder = getCharset().newDecoder();
        return new DecodedByteOut(charOut, decoder);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
