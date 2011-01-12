package net.bodz.bas.util.exception;

public class CreateException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public CreateException() {
        super();
    }

    public CreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateException(String message) {
        super(message);
    }

    public CreateException(Throwable cause) {
        super(cause);
    }

}
