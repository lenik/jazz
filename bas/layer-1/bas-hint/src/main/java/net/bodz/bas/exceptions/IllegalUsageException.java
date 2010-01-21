package net.bodz.bas.exceptions;

public class IllegalUsageException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalUsageException() {
        super();
    }

    public IllegalUsageException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUsageException(String message) {
        super(message);
    }

    public IllegalUsageException(Throwable cause) {
        super(cause);
    }

}
