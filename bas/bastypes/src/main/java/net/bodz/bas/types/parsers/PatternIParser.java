package net.bodz.bas.types.parsers;

import java.util.regex.Pattern;

public class PatternIParser extends PatternParser {

    public PatternIParser() {
        super(Pattern.CASE_INSENSITIVE);
    }

}
