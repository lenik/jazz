package net.bodz.bas.program.model;

import net.bodz.bas.program.skel.CLISyntaxException;

public class AmbiguousOptionKeyException
        extends CLISyntaxException {

    private static final long serialVersionUID = 1L;

    public AmbiguousOptionKeyException(String prefix, String suggestions) {
        super(prefix + "\nSuggestions: " + suggestions);
    }

}
