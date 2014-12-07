package net.bodz.bas.err;

public class UnsupportedReflectionException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnsupportedReflectionException() {
        super();
    }

    public UnsupportedReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedReflectionException(String message) {
        super(message);
    }

    public UnsupportedReflectionException(Throwable cause) {
        super(cause);
    }

}
