package net.bodz.bas.type.parser;

import java.util.regex.Pattern;

public class CaseInsensitivePatternParser extends PatternParser {

    public CaseInsensitivePatternParser() {
        super(Pattern.CASE_INSENSITIVE);
    }

}
