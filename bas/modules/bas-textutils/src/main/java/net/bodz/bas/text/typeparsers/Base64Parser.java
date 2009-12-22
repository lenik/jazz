package net.bodz.bas.text.typeparsers;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.TypeParser;

public class Base64Parser extends TypeParser {

    @Override
    public byte[] parse(String hex) throws ParseException {
        return Encodings.BASE64.decode(hex);
    }

}
