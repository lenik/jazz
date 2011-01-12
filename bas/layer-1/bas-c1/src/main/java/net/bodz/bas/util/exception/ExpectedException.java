package net.bodz.bas.util.exception;

public class ExpectedException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

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
