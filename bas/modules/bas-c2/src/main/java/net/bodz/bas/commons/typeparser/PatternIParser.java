package net.bodz.bas.commons.typeparser;

import java.util.regex.Pattern;

public class PatternIParser extends PatternParser {

    public PatternIParser() {
        super(Pattern.CASE_INSENSITIVE);
    }

}
