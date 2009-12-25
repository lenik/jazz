package net.bodz.bas.commons.typeparser;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import net.bodz.bas.api.exceptions.ParseException;

public class CharsetParser extends Parser {

    @Override
    public Charset parse(String name) throws ParseException {
        try {
            return Charset.forName(name);
        } catch (UnsupportedCharsetException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
