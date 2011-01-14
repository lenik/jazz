package net.bodz.bas.type.parser;

import java.util.regex.Pattern;

import net.bodz.bas.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class PatternParser
        extends AbstractParser<Pattern> {

    private final int flags;

    public PatternParser() {
        this.flags = 0;
    }

    public PatternParser(int flags) {
        this.flags = flags;
    }

    @Override
    public Pattern parse(String regexp)
            throws ParseException {
        return Pattern.compile(regexp, flags);
    }

}
