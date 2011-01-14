package net.bodz.bas.type.parser;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class DoubleParser
        extends AbstractParser<Double> {

    @Override
    public Double parse(String text)
            throws ParseException {
        try {
            return Double.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
