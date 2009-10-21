package net.bodz.bas.types.parsers;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class CharsetParser implements TypeParser {

    @Override
    public Charset parse(String name) throws ParseException {
        try {
            return Charset.forName(name);
        } catch (UnsupportedCharsetException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
