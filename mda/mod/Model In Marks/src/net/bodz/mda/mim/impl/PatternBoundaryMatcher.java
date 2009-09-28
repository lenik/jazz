package net.bodz.mda.mim.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.mda.mim.BoundaryMatcher;

public class PatternBoundaryMatcher implements BoundaryMatcher {

    private final int     lookaheadSize;
    private final Pattern pattern;

    /**
     * @param pattern
     *            must start with "<code>^(</code>" and the first capture group
     *            is used to return the matched text.
     */
    public PatternBoundaryMatcher(int lookaheadSize, String pattern) {
        if (lookaheadSize < 1)
            throw new OutOfDomainException("lookaheadSize", lookaheadSize, 1);
        if (pattern == null)
            throw new NullPointerException("pattern");
        if (!pattern.startsWith("^("))
            throw new IllegalArgumentException(
                    "Illegal pattern, must start with ^(: " + pattern);
        this.lookaheadSize = lookaheadSize;
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public String match(Reader textIn, CharOut rejectOut) throws IOException {
        char[] readbuf = new char[lookaheadSize];
        int cc = textIn.read(readbuf);
        if (cc == -1)
            return null;
        String readstr = new String(readbuf);
        Matcher matcher = pattern.matcher(readstr);
        if (matcher.matches()) {
            if (matcher.start(1) != 0)
                throw new IllegalUsageError(
                        "The pattern isn't matching from the begin: " + pattern);
            String text = matcher.group(1);
            int stop = matcher.end(1);
            rejectOut.write(readbuf, stop, readbuf.length - stop);
            return text;
        }
        rejectOut.write(readbuf, 0, cc);
        return null;
    }

}
