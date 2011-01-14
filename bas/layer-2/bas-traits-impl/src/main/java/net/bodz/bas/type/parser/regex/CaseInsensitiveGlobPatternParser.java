package net.bodz.bas.type.parser.regex;

import java.util.regex.Pattern;

public class CaseInsensitiveGlobPatternParser extends GlobPatternParser {

    public CaseInsensitiveGlobPatternParser() {
        super(Pattern.CASE_INSENSITIVE);
    }

}
