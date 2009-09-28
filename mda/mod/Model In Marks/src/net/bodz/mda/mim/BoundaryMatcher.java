package net.bodz.mda.mim;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.io.CharOut;

public interface BoundaryMatcher {

    /**
     * @param textIn
     *            plain text to be matched
     * @param rejectOut
     *            lookahead unmatched text must be saved in rejectOut to be
     *            reused, or re-matched .
     * @return the matched string if matched, or <code>null</code> means
     *         unmatched.
     */
    String match(Reader textIn, CharOut rejectOut) throws IOException;

}
