package net.bodz.bas.commons.typeparser;

import java.util.regex.Pattern;

import net.bodz.bas.api.exceptions.ParseException;

public class PatternParser extends Parser {

    private final int flags;

    public PatternParser() {
        this.flags = 0;
    }

    public PatternParser(int flags) {
        this.flags = flags;
    }

    @Override
    public Pattern parse(String regexp) throws ParseException {
        return Pattern.compile(regexp, flags);
    }

}
