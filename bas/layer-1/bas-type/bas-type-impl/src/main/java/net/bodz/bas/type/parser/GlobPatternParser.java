package net.bodz.bas.type.parser;

import java.util.regex.Pattern;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.impl.AbstractParser;

public class GlobPatternParser
        extends AbstractParser<Pattern> {

    private final int flags;

    public GlobPatternParser() {
        this.flags = 0;
    }

    public GlobPatternParser(int flags) {
        this.flags = flags;
    }

    /**
     * @param globString
     *            may include wildcards(*)
     */
    @Override
    public Pattern parse(String globString)
            throws ParseException {
        return _parse(globString, flags);
    }

    public static Pattern _parse(String globString, int patternFlags) {
        String regex = Pattern.quote(globString);
        regex = regex.replace("*", "\\E.*\\Q");
        regex = regex.replace("?", "\\E.\\Q");
        return Pattern.compile(regex, patternFlags);
    }

}
