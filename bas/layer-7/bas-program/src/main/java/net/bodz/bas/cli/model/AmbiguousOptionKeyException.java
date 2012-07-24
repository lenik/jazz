package net.bodz.bas.cli.model;

import net.bodz.bas.cli.skel.CLIException;

public class AmbiguousOptionKeyException
        extends CLIException {

    private static final long serialVersionUID = 1L;

    public AmbiguousOptionKeyException(String prefix, String suggestions) {
        super(prefix + "\nSuggestions: " + suggestions);
    }

}
