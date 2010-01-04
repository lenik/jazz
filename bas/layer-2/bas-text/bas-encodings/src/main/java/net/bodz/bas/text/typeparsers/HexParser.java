package net.bodz.bas.text.typeparsers;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.TypeParser;

public class HexParser extends TypeParser {

    @Override
    public byte[] parse(String hex) throws ParseException {
        return Encodings.HEX.decode(hex);
    }

}
