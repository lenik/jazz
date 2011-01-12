package net.bodz.bas.util.exception;

public class UnexpectedException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnexpectedException() {
        super();
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

}
