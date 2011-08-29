package net.bodz.bas.err;

public class CancelException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CancelException() {
        super();
    }

    public CancelException(String message, Throwable cause) {
        super(message, cause);
    }

    public CancelException(String message) {
        super(message);
    }

    public CancelException(Throwable cause) {
        super(cause);
    }

}
