package net.bodz.bas.fs.traverse;

import net.bodz.bas.type.parser.GlobPatternParser;

public class GlobFilenameFilter
        extends RegexFilenameFilter {

    public GlobFilenameFilter(String pattern) {
        this(pattern, true, false);
    }

    public GlobFilenameFilter(String pattern, boolean whole, boolean fullpath) {
        super(GlobPatternParser._parse(pattern, 0), whole, fullpath);
    }

}
