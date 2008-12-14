package net.bodz.bas.types.parsers;

import java.util.regex.Pattern;

public class WildcardsIParser extends WildcardsParser {

    public WildcardsIParser() {
        super(Pattern.CASE_INSENSITIVE);
    }

}
