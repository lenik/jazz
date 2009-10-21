package net.bodz.bas.io.util;

import java.util.regex.Pattern;

/**
 * @test {@link GlobFilterTest}
 */
public class GlobFilter extends RegexFilter {

    public GlobFilter(String pattern) {
        this(pattern, true, false);
    }

    public GlobFilter(String pattern, boolean whole, boolean fullpath) {
        super(compileGlob(pattern, 0), whole, fullpath);
    }

    public static Pattern compileGlob(String glob, int flags) {
        glob = Pattern.quote(glob);
        glob = glob.replace("*", "\\E.*\\Q");
        glob = glob.replace("?", "\\E.\\Q");
        return Pattern.compile(glob, flags);
    }

}
