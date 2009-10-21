package net.bodz.bas.types.parsers;

import java.util.regex.Pattern;

import net.bodz.bas.io.util.GlobFilter;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;

public class GlobParser implements TypeParser {

    private final int flags;

    public GlobParser() {
        this.flags = 0;
    }

    public GlobParser(int flags) {
        this.flags = flags;
    }

    @Override
    public Pattern parse(String wildcards) throws ParseException {
        return GlobFilter.compileGlob(wildcards, flags);
    }

}
