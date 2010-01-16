package net.bodz.bas.text.charsets;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class HexCharset
        extends Charset {

    public HexCharset() {
        super("hex", new String[] { "hex", });
    }

    /**
     * hex can hold everything
     */
    @Override
    public boolean contains(Charset cs) {
        return true;
    }

    @Override
    public CharsetDecoder newDecoder() {
        return new HexCharsetDecoder(this);
    }

    @Override
    public CharsetEncoder newEncoder() {
        return new HexCharsetEncoder(this);
    }

}
