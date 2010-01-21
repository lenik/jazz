package net.bodz.bas.exceptions;

public class IllegalUsageError
        extends Error {

    private static final long serialVersionUID = 1L;

    public IllegalUsageError() {
        super();
    }

    public IllegalUsageError(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUsageError(String message) {
        super(message);
    }

    public IllegalUsageError(Throwable cause) {
        super(cause);
    }

}
