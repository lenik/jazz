package net.bodz.bas.type.parser;

import net.bodz.bas.type.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class CharArrayParser
        extends AbstractParser<char[]> {

    @Override
    public char[] parse(String text)
            throws ParseException {
        return text.toCharArray();
    }

}
