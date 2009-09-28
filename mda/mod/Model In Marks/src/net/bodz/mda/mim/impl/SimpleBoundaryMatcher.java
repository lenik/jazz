package net.bodz.mda.mim.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

import net.bodz.bas.io.CharOut;
import net.bodz.mda.mim.BoundaryMatcher;

public class SimpleBoundaryMatcher implements BoundaryMatcher {

    private final char[] pattern;

    public SimpleBoundaryMatcher(String pattern) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        this.pattern = pattern.toCharArray();
    }

    @Override
    public String match(Reader textIn, CharOut rejectOut) throws IOException {
        char[] readbuf = new char[pattern.length];
        int cc = textIn.read(readbuf);
        if (cc == -1)
            return null;
        if (!Arrays.equals(pattern, readbuf)) {
            rejectOut.write(readbuf, 0, cc);
            return null;
        }
        return new String(pattern);
    }

}
