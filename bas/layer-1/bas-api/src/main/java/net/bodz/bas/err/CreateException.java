package net.bodz.bas.err;

public class CreateException
        extends RuntimeException {

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
