package net.bodz.bas.sio.nio;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;

import net.bodz.bas.sio.AbstractByteIn;
import net.bodz.bas.sio.ICharIn;

public class EncodedByteIn
        extends AbstractByteIn {

    private final ICharIn charIn;
    private final CharsetEncoder encoder;

    public EncodedByteIn(ICharIn charIn, CharsetEncoder encoder) {
        if (charIn == null)
            throw new NullPointerException("charIn");
        if (encoder == null)
            throw new NullPointerException("encoder");
        this.charIn = charIn;
        this.encoder = encoder;
    }

    @Override
    public int read()
            throws IOException {
        return 0;
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        return 0;
    }

}
