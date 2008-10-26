package net.bodz.graph;

import net.bodz.bas.lang.err.InconsistentException;

public class InconsistentGraphException extends InconsistentException {

    private static final long serialVersionUID = -5604719112644206080L;

    public InconsistentGraphException() {
        super();
    }

    public InconsistentGraphException(String message, Throwable cause) {
        super(message, cause);
    }

    public InconsistentGraphException(String message) {
        super(message);
    }

    public InconsistentGraphException(Throwable cause) {
        super(cause);
    }

}
