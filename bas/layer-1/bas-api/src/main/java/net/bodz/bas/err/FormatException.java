package net.bodz.bas.err;

public class FormatException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FormatException() {
        super();
    }

    public FormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatException(String message) {
        super(message);
    }

    public FormatException(Throwable cause) {
        super(cause);
    }

}
