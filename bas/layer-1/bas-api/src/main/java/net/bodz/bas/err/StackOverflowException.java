package net.bodz.bas.err;

public class StackOverflowException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StackOverflowException() {
        super();
    }

    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public StackOverflowException(String message) {
        super(message);
    }

    public StackOverflowException(Throwable cause) {
        super(cause);
    }

}
