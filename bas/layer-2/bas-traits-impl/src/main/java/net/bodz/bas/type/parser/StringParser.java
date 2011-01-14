package net.bodz.bas.type.parser;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class StringParser
        extends AbstractParser<String> {

    @Override
    public String parse(String text)
            throws ParseException {
        return text;
    }

}
