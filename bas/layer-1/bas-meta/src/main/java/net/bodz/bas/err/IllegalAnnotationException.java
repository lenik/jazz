package net.bodz.bas.err;

public class IllegalAnnotationException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalAnnotationException() {
        super();
    }

    public IllegalAnnotationException(String message) {
        super(message);
    }

    public IllegalAnnotationException(Throwable cause) {
        super(cause);
    }

    public IllegalAnnotationException(String message, Throwable cause) {
        super(message, cause);
    }

}
