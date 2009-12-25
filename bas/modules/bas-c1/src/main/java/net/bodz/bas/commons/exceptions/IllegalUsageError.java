package net.bodz.bas.commons.exceptions;

public class IllegalUsageError extends Error {

    private static final long serialVersionUID = 7369516357447712401L;

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
