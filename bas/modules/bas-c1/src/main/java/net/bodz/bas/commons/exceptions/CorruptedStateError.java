package net.bodz.bas.commons.exceptions;

public class CorruptedStateError extends Error {

    private static final long serialVersionUID = 7052201877382131673L;

    public CorruptedStateError() {
        super();
    }

    public CorruptedStateError(String message, Throwable cause) {
        super(message, cause);
    }

    public CorruptedStateError(String message) {
        super(message);
    }

    public CorruptedStateError(Throwable cause) {
        super(cause);
    }

}
