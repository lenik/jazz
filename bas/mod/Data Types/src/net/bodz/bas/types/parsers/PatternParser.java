package net.bodz.bas.types.parsers;

import java.util.regex.Pattern;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class PatternParser implements TypeParser {

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
