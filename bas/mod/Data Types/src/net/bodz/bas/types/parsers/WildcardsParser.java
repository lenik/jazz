package net.bodz.bas.types.parsers;

import java.util.regex.Pattern;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class WildcardsParser implements TypeParser {

    private final int flags;

    public WildcardsParser() {
        this.flags = 0;
    }

    public WildcardsParser(int flags) {
        this.flags = flags;
    }

    private static final Pattern QUOTE;
    static {
        QUOTE = Pattern.compile(//
                "([.+^$\\[\\](){}\\\\])");
    }

    @Override
    public Pattern parse(String wildcards) throws ParseException {
        String regexp = QUOTE.matcher(wildcards).replaceAll("\\$1");
        regexp = regexp.replace("*", ".*");
        regexp = regexp.replace("?", ".");
        return Pattern.compile(regexp, flags);
    }

}
