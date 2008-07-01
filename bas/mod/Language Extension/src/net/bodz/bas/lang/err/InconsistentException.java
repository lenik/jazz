package net.bodz.bas.lang.err;

public class InconsistentException extends RuntimeException {

    private static final long serialVersionUID = -7500685030093882350L;

    public InconsistentException() {
        super();
    }

    public InconsistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InconsistentException(String message) {
        super(message);
    }

    public InconsistentException(Throwable cause) {
        super(cause);
    }

}
