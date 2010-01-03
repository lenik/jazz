package net.bodz.bas.io.util;

/**
 * @test {@link GlobFilenameFilterTest}
 */
public class GlobFilenameFilter
        extends RegexFilenameFilter {

    public GlobFilenameFilter(String pattern) {
        this(pattern, true, false);
    }

    public GlobFilenameFilter(String pattern, boolean whole, boolean fullpath) {
        super(compileGlob(pattern, 0), whole, fullpath);
    }

}
