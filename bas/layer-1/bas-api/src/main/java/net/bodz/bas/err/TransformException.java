package net.bodz.bas.err;

public class TransformException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private TransformException() {
        super();
    }

    private TransformException(String message, Throwable cause) {
        super(message, cause);
    }

    private TransformException(String message) {
        super(message);
    }

    private TransformException(Throwable cause) {
        super(cause);
    }

}
