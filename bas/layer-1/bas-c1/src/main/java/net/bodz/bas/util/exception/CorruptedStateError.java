package net.bodz.bas.util.exception;

public class CorruptedStateError
        extends Error {

    private static final long serialVersionUID = 1L;

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
