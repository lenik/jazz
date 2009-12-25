package net.bodz.bas.api.exceptions;

public class ExpectedException extends RuntimeException {

    private static final long serialVersionUID = -2072559613976325483L;

    public ExpectedException() {
        super();
    }

    public ExpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpectedException(String message) {
        super(message);
    }

    public ExpectedException(Throwable cause) {
        super(cause);
    }

}
