package net.bodz.bas.program.model;

import net.bodz.bas.program.skel.CLISyntaxException;

public class NoSuchOptionException
        extends CLISyntaxException {

    private static final long serialVersionUID = 1L;

    public NoSuchOptionException(String optionName) {
        super("No such option: " + optionName);
    }

}
