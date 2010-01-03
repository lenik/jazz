package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.impl.AbstractParser;

public class CharArrayParser
        extends AbstractParser<char[]> {

    @Override
    public char[] parse(String text)
            throws ParseException {
        return text.toCharArray();
    }

}
