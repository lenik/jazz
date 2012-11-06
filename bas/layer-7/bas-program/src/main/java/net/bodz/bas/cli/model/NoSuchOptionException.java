package net.bodz.bas.cli.model;

import net.bodz.bas.cli.skel.CLISyntaxException;

public class NoSuchOptionException
        extends CLISyntaxException {

    private static final long serialVersionUID = 1L;

    public NoSuchOptionException() {
        super();
    }

    public NoSuchOptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchOptionException(String message) {
        super(message);
    }

    public NoSuchOptionException(Throwable cause) {
        super(cause);
    }

}
