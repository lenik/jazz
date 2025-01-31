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
        extends AbstractStreamOutputTarget<StringBufferTarget> {

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
    public boolean isTextModePreferred() {
        return true;
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

    @Override
    public ICharOut newCharOut(OpenOption... options) {
        return newPrintOut(options);
    }

    @Override
    public BufCharOut newPrintOut(OpenOption... options) {
        boolean append = OpenOptions.isAppend(options);

        if (!append)
            buffer.setLength(0);
        return new BufCharOut(buffer);
    }

    @Override
    public IByteOut newByteOut(OpenOption... options) {
        ICharOut charOut = newCharOut(options);
        CharsetDecoder decoder = getCharset().newDecoder();
        return new DecodedByteOut(charOut, decoder);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
