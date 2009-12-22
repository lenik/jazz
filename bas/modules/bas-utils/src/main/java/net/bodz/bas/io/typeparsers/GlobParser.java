package net.bodz.bas.io.typeparsers;

import java.util.regex.Pattern;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.api.types.TypeParser;

public class GlobParser extends TypeParser {

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
