package net.bodz.bas.io.res.builtin;

import java.nio.charset.CharsetDecoder;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.BufCharOut;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.impl.DecodedByteOut;
import net.bodz.bas.io.res.AbstractStreamOutputTarget;

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
    protected ICharOut _newCharOut(OpenOption... options) {
        return _newPrintOut(options);
    }

    @Override
    protected BufCharOut _newPrintOut(OpenOption... options) {
        boolean append = OpenOptions.isAppend(options);

        if (!append)
            buffer.setLength(0);
        return new BufCharOut(buffer);
    }

    @Override
    protected IByteOut _newByteOut(OpenOption... options) {
        ICharOut charOut = _newCharOut(options);
        CharsetDecoder decoder = getCharset().newDecoder();
        return new DecodedByteOut(charOut, decoder);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
