package net.bodz.bas.c.java.io;

import static net.bodz.bas.c.java.util.regex.PatternTraits.globTextformMode;
import static net.bodz.bas.c.java.util.regex.PatternTraits.textformMode;

import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.ListNegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.Option;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;

public class GlobFilenameFilter
        extends RegexFilenameFilter {

    public GlobFilenameFilter(String pattern) {
        this(pattern, true, false);
    }

    public GlobFilenameFilter(String pattern, boolean whole, boolean fullpath) {
        super(parseGlob(pattern), whole, fullpath);
    }

    static IParser<Pattern> patternParser;
    static INegotiation globNegotiation;
    static {
        patternParser = Traits.getTrait(Pattern.class, IParser.class);
        globNegotiation = new ListNegotiation(//
                new Option(textformMode, globTextformMode));
    }

    static Pattern parseGlob(String pattern) {
        try {
            return patternParser.parse(pattern, globNegotiation);
        } catch (ParseException e) {
            throw new UnexpectedException(e.getMessage(), e);
        } catch (NegotiationException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

}
