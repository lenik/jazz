package net.bodz.bas.type.parser;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.AbstractParser;

public class CharsetParser
        extends AbstractParser<Charset> {

    @Override
    public Charset parse(String name)
            throws ParseException {
        try {
            return Charset.forName(name);
        } catch (UnsupportedCharsetException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
