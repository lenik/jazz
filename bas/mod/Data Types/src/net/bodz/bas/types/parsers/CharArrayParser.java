package net.bodz.bas.types.parsers;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class CharArrayParser implements TypeParser {

    @Override
    public char[] parse(String text) throws ParseException {
        return text.toCharArray();
    }

}
