package net.bodz.bas.err;

public class OverflowError
        extends Error {

    private static final long serialVersionUID = 1L;

    public OverflowError() {
        super();
    }

    public OverflowError(String message, Throwable cause) {
        super(message, cause);
    }

    public OverflowError(String message) {
        super(message);
    }

    public OverflowError(Throwable cause) {
        super(cause);
    }

}
