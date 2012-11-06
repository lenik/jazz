package net.bodz.bas.cli.model;

import net.bodz.bas.cli.skel.CLISyntaxException;

public class AmbiguousOptionKeyException
        extends CLISyntaxException {

    private static final long serialVersionUID = 1L;

    public AmbiguousOptionKeyException(String prefix, String suggestions) {
        super(prefix + "\nSuggestions: " + suggestions);
    }

}
