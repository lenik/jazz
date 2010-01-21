package net.bodz.bas.sio.nio;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;

import net.bodz.bas.sio.AbstractCharOut;
import net.bodz.bas.sio.IByteOut;

public class EncodedCharOut
        extends AbstractCharOut {

    private final IByteOut byteOut;
    private final CharsetEncoder encoder;

    public EncodedCharOut(IByteOut byteOut, CharsetEncoder encoder) {
        if (byteOut == null)
            throw new NullPointerException("byteOut");
        if (encoder == null)
            throw new NullPointerException("encoder");
        this.byteOut = byteOut;
        this.encoder = encoder;
    }

    @Override
    public void write(int ch)
            throws IOException {
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
    }

}
