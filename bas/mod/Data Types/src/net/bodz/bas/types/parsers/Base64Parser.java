package net.bodz.bas.types.parsers;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.text.encodings.Encodings;
import net.bodz.bas.types.TypeParser;

public class Base64Parser implements TypeParser {

    @Override
    public byte[] parse(String hex) throws ParseException {
        return Encodings.BASE64.decode(hex);
    }

}
