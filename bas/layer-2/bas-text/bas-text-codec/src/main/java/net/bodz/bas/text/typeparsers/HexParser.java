package net.bodz.bas.text.typeparsers;

import java.nio.charset.Charset;

import net.bodz.bas.exceptions.DecodeException;
import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.text.codec.builtin.HexCodec;
import net.bodz.bas.type.traits.AbstractParser;

public class HexParser
        extends AbstractParser<byte[]> {

    static final HexCodec hexCodec;
    static final Charset hexCharset;
    static {
        hexCodec = HexCodec.getInstance();
        hexCharset = hexCodec.getPreferredCharsetForEncodedContents();
    }

    @Override
    public byte[] parse(String hex)
            throws ParseException {
        if (hex == null)
            throw new NullPointerException("hex");
        try {
            byte[] bytes = hexCodec.decode(hex);
            return bytes;
        } catch (DecodeException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
