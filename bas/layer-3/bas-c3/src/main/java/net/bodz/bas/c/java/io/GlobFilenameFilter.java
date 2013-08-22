package net.bodz.bas.c.java.io;

import static net.bodz.bas.c.java.util.regex.PatternMdaFeatures.globTextformMode;
import static net.bodz.bas.c.java.util.regex.PatternMdaFeatures.textformMode;

import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.mf.MdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;

public class GlobFilenameFilter
        extends RegexFilenameFilter {

    public GlobFilenameFilter(String pattern) {
        this(pattern, true, false);
    }

    public GlobFilenameFilter(String pattern, boolean whole, boolean fullpath) {
        super(parseGlob(pattern), whole, fullpath);
    }

    static IParser<Pattern> patternParser;
    static IOptions globOptions;
    static {
        patternParser = MdaFeatures.getMdaFeature(Pattern.class, IParser.class);
        globOptions = new Options().addOption(textformMode, globTextformMode);
    }

    static Pattern parseGlob(String pattern) {
        try {
            return patternParser.parse(pattern, globOptions);
        } catch (ParseException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
