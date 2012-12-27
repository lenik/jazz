package net.bodz.bas.program.model;

import net.bodz.bas.program.skel.CLISyntaxException;

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
