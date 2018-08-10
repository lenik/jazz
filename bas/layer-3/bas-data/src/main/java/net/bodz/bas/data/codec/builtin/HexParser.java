package net.bodz.bas.data.codec.builtin;

import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractParser;

public class HexParser
        extends AbstractParser<byte[]> {

    private HexCodec hexCodec;

    public HexParser() {
        this(new HexCodec());
    }

    public HexParser(HexCodec hexCodec) {
        if (hexCodec == null)
            throw new NullPointerException("hexCodec");
        this.hexCodec = hexCodec;
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
