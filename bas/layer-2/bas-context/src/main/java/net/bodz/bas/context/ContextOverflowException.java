package net.bodz.bas.context;

public class ContextOverflowException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ContextOverflowException() {
        super();
    }

    public ContextOverflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContextOverflowException(String message) {
        super(message);
    }

    public ContextOverflowException(Throwable cause) {
        super(cause);
    }

}
