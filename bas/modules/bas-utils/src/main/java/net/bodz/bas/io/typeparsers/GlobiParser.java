package net.bodz.bas.io.typeparsers;

import java.util.regex.Pattern;

public class GlobiParser extends GlobParser {

    public GlobiParser() {
        super(Pattern.CASE_INSENSITIVE);
    }

}
