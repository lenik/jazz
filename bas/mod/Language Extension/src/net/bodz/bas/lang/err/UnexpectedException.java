package net.bodz.bas.lang.err;

public class UnexpectedException extends RuntimeException {

    private static final long serialVersionUID = -893813012361065594L;

    public UnexpectedException() {
        super();
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

}
