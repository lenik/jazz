package net.bodz.bas.meta.build;

import net.bodz.bas.err.IllegalAnnotationException;

public class IllegalVersionException
        extends IllegalAnnotationException {

    private static final long serialVersionUID = 1L;

    public IllegalVersionException() {
        super();
    }

    public IllegalVersionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalVersionException(String message) {
        super(message);
    }

    public IllegalVersionException(Throwable cause) {
        super(cause);
    }

}
