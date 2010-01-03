package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.impl.AbstractParser;

public class StringParser
        extends AbstractParser<String> {

    @Override
    public String parse(String text)
            throws ParseException {
        return text;
    }

}
