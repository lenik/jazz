package net.bodz.bas.c.java.io;

import static net.bodz.bas.c.java.util.regex.PatternTypers.globTextformMode;
import static net.bodz.bas.c.java.util.regex.PatternTypers.textformMode;

import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

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
        patternParser = Typers.getTyper(Pattern.class, IParser.class);
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
