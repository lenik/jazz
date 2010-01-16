package net.bodz.bas.text.typeparsers;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.text.encodings.Encodings;
import net.bodz.bas.type.traits.AbstractParser;

public class HexParser
        extends AbstractParser<byte[]> {

    @Override
    public byte[] parse(String hex)
            throws ParseException {
        return Encodings.HEX.decode(hex);
    }

}
