package net.bodz.bas.type.parser;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.AbstractParser;

public class CharacterParser
        extends AbstractParser<Character> {

    @Override
    public Character parse(String text)
            throws ParseException {
        if (text == null)
            return null;
        if (text.isEmpty())
            return '\u0000';
        return text.charAt(0);
    }

}
