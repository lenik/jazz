package net.bodz.bas.err;

public class StackUnderflowException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StackUnderflowException() {
        super();
    }

    public StackUnderflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public StackUnderflowException(String message) {
        super(message);
    }

    public StackUnderflowException(Throwable cause) {
        super(cause);
    }

}
