package net.bodz.bas.data.codec.builtin;

import java.nio.charset.Charset;

import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractParser;
import net.bodz.bas.rtx.IOptions;

public class HexParser
        extends AbstractParser<byte[]> {

    static final HexCodec hexCodec;
    static final Charset hexCharset;
    static {
        hexCodec = HexCodec.getInstance();
        hexCharset = hexCodec.getPreferredCharsetForEncodedContents();
    }

    @Override
    public byte[] parse(String hex, IOptions options)
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
