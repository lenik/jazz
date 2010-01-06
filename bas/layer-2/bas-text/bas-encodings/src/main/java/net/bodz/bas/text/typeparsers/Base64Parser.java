package net.bodz.bas.text.typeparsers;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.text.encodings.Encodings;
import net.bodz.bas.type.traits.impl.AbstractParser;

public class Base64Parser
        extends AbstractParser<byte[]> {

    @Override
    public byte[] parse(String hex)
            throws ParseException {
        return Encodings.BASE64.decode(hex);
    }

}
