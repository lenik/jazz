package net.bodz.bas.util;

public class OperationException
        extends Exception {

    private static final long serialVersionUID = 5079299543033517264L;

    private OperationException() {
        super();
    }

    private OperationException(String message, Throwable cause) {
        super(message, cause);
    }

    private OperationException(String message) {
        super(message);
    }

    private OperationException(Throwable cause) {
        super(cause);
    }

}
