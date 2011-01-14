package net.bodz.bas.vfs.traverse;

import net.bodz.bas.type.parser.regex.GlobPatternParser;

public class GlobFilenameFilter
        extends RegexFilenameFilter {

    public GlobFilenameFilter(String pattern) {
        this(pattern, true, false);
    }

    public GlobFilenameFilter(String pattern, boolean whole, boolean fullpath) {
        super(GlobPatternParser._parse(pattern, 0), whole, fullpath);
    }

}
