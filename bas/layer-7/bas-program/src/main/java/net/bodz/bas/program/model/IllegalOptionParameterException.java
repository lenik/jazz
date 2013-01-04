package net.bodz.bas.program.model;

import net.bodz.bas.program.skel.CLISyntaxException;

public class IllegalOptionParameterException
        extends CLISyntaxException {

    private static final long serialVersionUID = 1L;

    public IllegalOptionParameterException() {
        super();
    }

    public IllegalOptionParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalOptionParameterException(String message) {
        super(message);
    }

    public IllegalOptionParameterException(Throwable cause) {
        super(cause);
    }

}
