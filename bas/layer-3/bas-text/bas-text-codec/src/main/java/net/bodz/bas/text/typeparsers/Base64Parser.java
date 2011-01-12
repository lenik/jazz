package net.bodz.bas.text.typeparsers;

import java.nio.charset.Charset;

import net.bodz.bas.text.codec.builtin.Base64Codec;
import net.bodz.bas.type.traits.AbstractParser;
import net.bodz.bas.util.exception.DecodeException;
import net.bodz.bas.util.exception.ParseException;

public class Base64Parser
        extends AbstractParser<byte[]> {

    static final Base64Codec base64Codec;
    static final Charset base64Charset;
    static {
        base64Codec = Base64Codec.getInstance();
        base64Charset = base64Codec.getPreferredCharsetForEncodedContents();
    }

    @Override
    public byte[] parse(String base64)
            throws ParseException {
        if (base64 == null)
            throw new NullPointerException("base64");
        try {
            byte[] bytes = base64Codec.decode(base64);
            return bytes;
        } catch (DecodeException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
