package net.bodz.bas.sio.nio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import net.bodz.bas.sio.AbstractCharIn;
import net.bodz.bas.sio.IByteIn;

public class DecodedCharIn
        extends AbstractCharIn {

    private final IByteIn byteIn;
    private final CharsetDecoder decoder;

    /**
     * @def ~ctor(byteIn, charset.newDecoder())
     */
    public DecodedCharIn(IByteIn byteIn, Charset charset) {
        this(byteIn, charset.newDecoder());
    }

    public DecodedCharIn(IByteIn byteIn, CharsetDecoder decoder) {
        if (byteIn == null)
            throw new NullPointerException("byteIn");
        if (decoder == null)
            throw new NullPointerException("decoder");
        this.byteIn = byteIn;
        this.decoder = decoder;
    }

    @Override
    public int read()
            throws IOException {
        return 0;
    }

    @Override
    public int read(char[] chars, int off, int len)
            throws IOException {
        return 0;
    }

}
