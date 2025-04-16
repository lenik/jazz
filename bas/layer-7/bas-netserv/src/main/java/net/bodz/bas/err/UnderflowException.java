package net.bodz.bas.err;

public class UnderflowException
        extends RuntimeException {

    public UnderflowException() {
    }

    public UnderflowException(String message) {
        super(message);
    }

    public UnderflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnderflowException(Throwable cause) {
        super(cause);
    }

}
