package net.bodz.bas.types.parsers;

import java.util.regex.Pattern;

public class GlobiParser extends GlobParser {

    public GlobiParser() {
        super(Pattern.CASE_INSENSITIVE);
    }

}
