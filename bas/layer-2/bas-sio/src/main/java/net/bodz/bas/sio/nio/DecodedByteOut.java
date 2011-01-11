package net.bodz.bas.sio.nio;

import java.io.IOException;
import java.nio.charset.CharsetDecoder;

import net.bodz.bas.sio.AbstractByteOut;
import net.bodz.bas.sio.ICharOut;

public class DecodedByteOut
        extends AbstractByteOut {

    private final ICharOut charOut;
    private final CharsetDecoder decoder;

    public DecodedByteOut(ICharOut charOut, CharsetDecoder decoder) {
        if (charOut == null)
            throw new NullPointerException("charOut");
        if (decoder == null)
            throw new NullPointerException("decoder");
        this.charOut = charOut;
        this.decoder = decoder;
    }

    @Override
    public void write(int b)
            throws IOException {
    }

    @Override
    public void write(byte[] buf, int off, int len)
            throws IOException {
    }

}
