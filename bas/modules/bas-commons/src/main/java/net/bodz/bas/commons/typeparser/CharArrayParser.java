package net.bodz.bas.commons.typeparser;

import net.bodz.bas.api.exceptions.ParseException;

public class CharArrayParser extends Parser {

    @Override
    public char[] parse(String text) throws ParseException {
        return text.toCharArray();
    }

}
