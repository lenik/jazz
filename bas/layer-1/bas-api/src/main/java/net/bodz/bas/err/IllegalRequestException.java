package net.bodz.bas.err;

public class IllegalRequestException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalRequestException() {
        super();
    }

    public IllegalRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestException(String message) {
        super(message);
    }

    public IllegalRequestException(Throwable cause) {
        super(cause);
    }

}
