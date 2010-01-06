package net.bodz.bas.io.fs.traverse;

import net.bodz.bas.io.util.GlobFilenameFilterTest;
import net.bodz.bas.type.parser.GlobPatternParser;

/**
 * @test {@link GlobFilenameFilterTest}
 */
public class GlobFilenameFilter
        extends RegexFilenameFilter {

    public GlobFilenameFilter(String pattern) {
        this(pattern, true, false);
    }

    public GlobFilenameFilter(String pattern, boolean whole, boolean fullpath) {
        super(GlobPatternParser._parse(pattern, 0), whole, fullpath);
    }

}
